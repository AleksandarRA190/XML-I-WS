package ftn.xmlws.dto;

import java.util.ArrayList;
import java.util.List;

public class ReservationsDTO {
	
	private List<ReservationDTO> reservations;
	
	public ReservationsDTO() {
		this.reservations = new ArrayList<>();
	}

	public List<ReservationDTO> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationDTO> reservations) {
		this.reservations = reservations;
	}
}
