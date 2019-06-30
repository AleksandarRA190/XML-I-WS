package ftn.xmlws.dto;

import java.time.LocalDate;

public class AccommodationUnitSearchDTO {

	private LocalDate startDate;
	private LocalDate endDate;
	
	public AccommodationUnitSearchDTO() {
		
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
