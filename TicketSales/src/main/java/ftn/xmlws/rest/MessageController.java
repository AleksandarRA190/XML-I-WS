package ftn.xmlws.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ftn.xmlws.dto.MessageDTO;
import ftn.xmlws.dto.Messages;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/byReservation/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<MessageDTO>> getMessagesByReservation(@PathVariable("id") Long id) {
		
		Messages retVal = restTemplate.getForObject("http://localhost:9010/message/byReservation/"+id, Messages.class);
		
		return new ResponseEntity<>(retVal.getMessages(),HttpStatus.OK);

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<MessageDTO> getMessage(@PathVariable("id") Long id) {
		MessageDTO message = restTemplate.getForObject("http://localhost:9010/message/"+id, MessageDTO.class);
		if (message == null) {
			return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(message, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/send", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> sendMessage(@RequestBody MessageDTO message) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<MessageDTO> request = new HttpEntity<>(message,headers);
		
		restTemplate.put("http://localhost:9010/message/send", request);

		return new ResponseEntity<>(HttpStatus.OK);		
	}

	@RequestMapping(value = "/respond/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> respondMessage(@PathVariable("id") Long id, @RequestBody MessageDTO message) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<MessageDTO> request = new HttpEntity<>(message,headers);
		
		restTemplate.put("http://localhost:9010/message/respond/"+id, request);

		return new ResponseEntity<>(HttpStatus.OK);		
	}

}
