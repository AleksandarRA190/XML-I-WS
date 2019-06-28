package ftn.xmlws.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ftn.xmlws.enums.AccommodationType;

public class AccommodationSearchDTO {

	private LocalDate startDate;
	private LocalDate endDate;
	private int numberOfGuests;
	private List<AccommodationType> accommodationTypes;
	private List<ServiceDTO> services;
	
	public AccommodationSearchDTO() {
		this.accommodationTypes = new ArrayList<>();
		this.services = new ArrayList<>();
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

	public List<AccommodationType> getAccommodationTypes() {
		return accommodationTypes;
	}

	public void setAccommodationTypes(List<AccommodationType> accommodationTypes) {
		this.accommodationTypes = accommodationTypes;
	}

	public List<ServiceDTO> getServices() {
		return services;
	}

	public void setServices(List<ServiceDTO> services) {
		this.services = services;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
}
