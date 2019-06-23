package ftn.xmlws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.dto.MessageDTO;
import ftn.xmlws.model.Message;
import ftn.xmlws.model.User;
import ftn.xmlws.repository.AccommodationRepository;
import ftn.xmlws.repository.MessageRepository;
import ftn.xmlws.repository.ReservationRepository;
import ftn.xmlws.repository.UserRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccommodationRepository accommodationRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	
	public MessageDTO getMessage(Long id) {
		MessageDTO retVal = new MessageDTO(this.messageRepository.getOne(id));
		return retVal;
	}
	
	public List<MessageDTO> getMessagesByReservation(Long id) {
		List<Message> messages = this.messageRepository.findAllByReservation_id(id);
		List<MessageDTO> retVal = new ArrayList<MessageDTO>();
		for(Message m : messages) {
			retVal.add(new MessageDTO(m));
		}
		
		return retVal;
	}

	public void sendMessage(MessageDTO message) {
		Message m = new Message(message);
		m.setSender(userRepository.findByUsername(message.getSender().getUsername()));
		m.setReservation(reservationRepository.getOne(message.getReservation().getId()));
		m.setAccommodation(accommodationRepository.getOne(message.getAccommodation().getId()));
		this.messageRepository.save(m);
	}
	
	public void respondToMessage(Long respondId, MessageDTO message) {
		System.out.println(respondId);
		System.out.println(message.getMessageContent());
		System.out.println(message.getSender().getUsername());
		System.out.println(message.getReservation().getId());
		Message msg = this.messageRepository.getOne(respondId);
		User agent = this.userRepository.findByUsername(message.getSender().getUsername());
		msg.setReciever(agent);
		msg.setSeen(true);
		msg.setAccommodation(null);
		this.messageRepository.save(msg);
		
		message.setSeen(false);
		Message m = new Message(message);
		m.setSender(agent);
		m.setReciever(msg.getSender());
		m.setReservation(reservationRepository.getOne(message.getReservation().getId()));
		m.setAccommodation(null);

		this.messageRepository.save(m);
	}

	public void removeMessage(Long id) {
		Message message = this.messageRepository.getOne(id);
		message.setDeleted(true);
		this.messageRepository.save(message);
	}


	
}
