package ftn.xmlws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.xmlws.dto.MessageDTO;
import ftn.xmlws.dto.Messages;
import ftn.xmlws.service.MessageService;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<MessageDTO> getMessage(@PathVariable("id") Long id) {
		MessageDTO message = messageService.getMessage(id);
		if (message == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(message, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/byReservation/{id}", method = RequestMethod.GET)
	public ResponseEntity<Messages> getMessagesByReservation(@PathVariable("id") Long id) {
		List<MessageDTO> messages = messageService.getMessagesByReservation(id);
		Messages retVal = new Messages();
		retVal.setMessages(messages);
		System.out.println(retVal.getMessages().size());
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "/send", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> sendMessage(@RequestBody MessageDTO message) {

		messageService.sendMessage(message);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/respond/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> respondMessage(@PathVariable("id") Long id, @RequestBody MessageDTO message) {
		
		System.out.println("u url " + id);
		messageService.respondToMessage(id,message);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
