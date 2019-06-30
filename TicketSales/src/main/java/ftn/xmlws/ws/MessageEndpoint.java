package ftn.xmlws.ws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.projectxml.message.GetMessagesByReservationRequest;
import com.projectxml.message.GetMessagesByReservationResponse;
import com.projectxml.message.Message;
import com.projectxml.message.MessageToSend;
import com.projectxml.message.RespondRequest;
import com.projectxml.message.RespondResponse;

import ftn.xmlws.dto.MessageDTO;
import ftn.xmlws.dto.Messages;
import ftn.xmlws.dto.ReservationDTO;
import ftn.xmlws.dto.UserDTO;

@Endpoint
public class MessageEndpoint {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PayloadRoot(namespace = "http://www.projectXml.com/message", localPart = "getMessagesByReservationRequest")
	@ResponsePayload
	public GetMessagesByReservationResponse processGetMessagesByReservationRequest(@RequestPayload GetMessagesByReservationRequest request) {
		GetMessagesByReservationResponse response = new GetMessagesByReservationResponse();
		Messages messages = restTemplate.getForObject("http://message-service/message/byReservation/"+request.getId(), Messages.class);

		List<Message> toAdd = new ArrayList<Message>();
		for(MessageDTO msg : messages.getMessages()) {
			Message m = new Message(msg);
			toAdd.add(m);
	    }
	    
		response.getMessage().addAll(toAdd);
		return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/message", localPart = "respondRequest")
	@ResponsePayload
	public RespondResponse processRespondRequest(@RequestPayload RespondRequest request) {
		
		RespondResponse response = new RespondResponse();
		MessageToSend msg = request.getMessage();
		Long reservationId = request.getRespondId();
		
		MessageDTO m = new MessageDTO();
		m.setMessageContent(msg.getMessageContent());
		
		UserDTO sender = new UserDTO();
		sender.setUsername(msg.getSenderUsername());
		m.setSender(sender);
		
		ReservationDTO r = new ReservationDTO();
		r.setId(msg.getReservationId());
	    m.setReservation(r);
	    System.out.println("!");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<MessageDTO> httpRequest = new HttpEntity<>(m,headers);
		
		restTemplate.put("http://message-service/message/respond/"+reservationId, httpRequest);
		
		response.setSuccess(true);
	  	return response;
	 }

}
