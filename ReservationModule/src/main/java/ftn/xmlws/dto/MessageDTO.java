package ftn.xmlws.dto;

import java.time.LocalDateTime;

import ftn.xmlws.model.Message;

public class MessageDTO {
	
	protected Long id;
    protected String messageContent;
    protected boolean seen;
    protected UserDTO sender;
    protected UserDTO reciever;
    protected boolean deleted;
    protected ReservationDTO reservation;
    protected AccommodationDTO accommodation;
    protected LocalDateTime dateTime;
    
    public MessageDTO() {
    	
    }
    
    public MessageDTO(Message m) {
    	this.id = m.getId();
    	this.messageContent = m.getMessageContent();
    	this.seen = m.isSeen();
    	this.deleted = m.isDeleted();
    	this.sender = new UserDTO(m.getSender());
    	if(m.getReciever()!=null)
    		this.reciever = new UserDTO(m.getReciever());
    	this.reservation = new ReservationDTO(m.getReservation());
    	this.accommodation = new AccommodationDTO(m.getAccommodation());
    	this.dateTime = m.getDateTime();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public UserDTO getSender() {
		return sender;
	}

	public void setSender(UserDTO sender) {
		this.sender = sender;
	}

	public UserDTO getReciever() {
		return reciever;
	}

	public void setReciever(UserDTO reciever) {
		this.reciever = reciever;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public ReservationDTO getReservation() {
		return reservation;
	}

	public void setReservation(ReservationDTO reservation) {
		this.reservation = reservation;
	}

	public AccommodationDTO getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(AccommodationDTO accommodation) {
		this.accommodation = accommodation;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
    
    

}
