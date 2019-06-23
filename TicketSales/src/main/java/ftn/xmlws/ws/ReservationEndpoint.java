package ftn.xmlws.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.projectxml.reservation.ConfirmCommentRequest;
import com.projectxml.reservation.ConfirmCommentResponse;
import com.projectxml.reservation.ConfirmReservationRequest;
import com.projectxml.reservation.ConfirmReservationResponse;
import com.projectxml.reservation.DeleteCommentRequest;
import com.projectxml.reservation.DeleteCommentResponse;
import com.projectxml.reservation.GetReservationRequest;
import com.projectxml.reservation.GetReservationResponse;
import com.projectxml.reservation.GetReservationsByUnitRequest;
import com.projectxml.reservation.GetReservationsByUnitResponse;
import com.projectxml.reservation.Reservation;

@Endpoint
public class ReservationEndpoint {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PayloadRoot(namespace = "http://www.projectXml.com/reservation", localPart = "deleteCommentRequest")
	@ResponsePayload
	public DeleteCommentResponse processDeleteCommentRequest( @RequestPayload DeleteCommentRequest request) {
		
		DeleteCommentResponse response = new DeleteCommentResponse();
		
		Long reservationId = request.getIdReservation();
		System.out.println(reservationId);
		
		restTemplate.delete("http://localhost:9008/reservation/comment/"+reservationId);
		response.setSuccess(true);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/reservation", localPart = "getReservationsByUnitRequest")
	@ResponsePayload
	public GetReservationsByUnitResponse processGetReservationsByUnitRequest(
			@RequestPayload GetReservationsByUnitRequest request) {
		GetReservationsByUnitResponse response = new GetReservationsByUnitResponse();
		
		// To be implemented
	  
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/reservation", localPart = "getReservationRequest")
	@ResponsePayload
	public GetReservationResponse processGetReservationRequest( @RequestPayload GetReservationRequest request) {
		GetReservationResponse response = new GetReservationResponse();
		
		Long id = request.getId();
		Reservation retVal = restTemplate.getForObject("http://localhost:9008/reservation/"+id, Reservation.class);
		
		response.setReservation(retVal);
	  
	  	return response;
	}
	
	@PayloadRoot(namespace = "http://www.projectXml.com/reservation", localPart = "confirmReservationRequest")
	@ResponsePayload
	public ConfirmReservationResponse processConfirmReservationRequest(
			@RequestPayload ConfirmReservationRequest request) {
		
		ConfirmReservationResponse response = new ConfirmReservationResponse();
		
		Long reservationId = request.getIdReservation();
		
		boolean success = restTemplate.getForObject("http://localhost:9008/reservation/agentConfirm/"+reservationId, boolean.class);
		
		response.setSuccess(success);
		
	  	return response;
	}
	
	@PayloadRoot(namespace = "http://www.projectXml.com/reservation", localPart = "confirmCommentRequest")
	@ResponsePayload
	public ConfirmCommentResponse processConfirmCommentRequest( @RequestPayload ConfirmCommentRequest request) {
		
		ConfirmCommentResponse response = new ConfirmCommentResponse();
		
		Long idReservation = request.getIdReservation();
		
		boolean success = restTemplate.getForObject("http://localhost:9008/reservation/confirmComment/"+idReservation, boolean.class);
		response.setSuccess(success);
		
	  	return response;
	}
	


}
