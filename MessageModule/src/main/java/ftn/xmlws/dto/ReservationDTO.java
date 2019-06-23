package ftn.xmlws.dto;

import java.time.LocalDateTime;

import ftn.xmlws.model.Reservation;

public class ReservationDTO {
	
	private Long id;
	private boolean confirmed;
    private UserDTO guest;
    private AccommodationUnitDTO accommodationUnit;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private boolean agentConfirmed;
	private CommentDTO commentDTO;
    
	public ReservationDTO() {
		
	}
	
	public ReservationDTO(Reservation res) {
		this.id = res.getId();
		this.confirmed = res.isConfirmed();
		this.guest = new UserDTO(res.getGuest());
		this.fromDateTime = res.getFromDateTime();
		this.toDateTime = res.getToDateTime();
		this.accommodationUnit = new AccommodationUnitDTO(res.getAccommodationUnit());
		this.agentConfirmed = res.isAgentConfirmed();
		
		if(res.getCommentRate() != null) {
			this.commentDTO = new CommentDTO();
			commentDTO.setApprovedComment(res.getCommentRate().isApprovedComment());
			commentDTO.setContentOfComment(res.getCommentRate().getContentOfComment());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	public boolean isAgentConfirmed() {
		return agentConfirmed;
	}

	public void setAgentConfirmed(boolean confirmed) {
		this.agentConfirmed = confirmed;
	}

	public UserDTO getGuest() {
		return guest;
	}

	public void setGuest(UserDTO guest) {
		this.guest = guest;
	}

	public AccommodationUnitDTO getAccommodationUnit() {
		return accommodationUnit;
	}

	public void setAccommodationUnit(AccommodationUnitDTO accommodationUnit) {
		this.accommodationUnit = accommodationUnit;
	}

	public LocalDateTime getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(LocalDateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public LocalDateTime getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(LocalDateTime toDateTime) {
		this.toDateTime = toDateTime;
	}

	public CommentDTO getCommentDTO() {
		return commentDTO;
	}

	public void setCommentDTO(CommentDTO commentDTO) {
		this.commentDTO = commentDTO;
	}

	
    


}
