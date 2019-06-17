package ftn.xmlws.dto;

import java.util.ArrayList;
import java.util.List;

public class AccommodationsDTO {

	private List<AccommodationDTO> accommodations;
	
	public AccommodationsDTO() {
		this.accommodations = new ArrayList<>();
	}

	public List<AccommodationDTO> getAccommodations() {
		return accommodations;
	}

	public void setAccommodations(List<AccommodationDTO> accommodations) {
		this.accommodations = accommodations;
	}
}
