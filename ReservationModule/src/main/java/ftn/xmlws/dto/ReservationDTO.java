package ftn.xmlws.dto;

import java.time.LocalDate;

import ftn.xmlws.miscellaneous.MyTypeConverter;
import ftn.xmlws.model.Reservation;

public class ReservationDTO {
	
	private Long id;
	private LocalDate fromDate;
	private LocalDate toDate;
	private boolean confirmed;
    private UserDTO guest;
    private AccommodationUnitDTO accommodationUnit;
    private boolean deleted;
    
	public ReservationDTO() {
		
	}
	
	public ReservationDTO(Reservation res) {
		this.id = res.getId();
		this.confirmed = res.isConfirmed();
		//this.guest = new UserDTO(res.getGuest());
		//this.fromDate = MyTypeConverter.xmlCalendarToLocalDate(res.getFromDate());
		//this.toDate = MyTypeConverter.xmlCalendarToLocalDate(res.getToDate());
		//this.accommodationUnit = new AccommodationUnitDTO(res.getAccommodationUnit());
		this.deleted = res.isDeleted();
		
		//kad se odrade pozivi drugim mikroservisima onda skini ovo i otkomentarisi ono gore
		this.guest = null;
		this.accommodationUnit = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
    


}
