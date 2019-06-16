package ftn.xmlws.dto;

import java.util.ArrayList;
import java.util.List;

public class UserReservationsDTO {
	
	private List<ReservationDTO> reservations = new ArrayList<>();
	
	public UserReservationsDTO() {}

	public List<ReservationDTO> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationDTO> reservations) {
		this.reservations = reservations;
	}
	
	
	

}
