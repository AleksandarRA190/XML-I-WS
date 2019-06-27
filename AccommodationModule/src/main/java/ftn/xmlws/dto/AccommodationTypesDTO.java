package ftn.xmlws.dto;

import java.util.ArrayList;
import java.util.List;

public class AccommodationTypesDTO {

	private List<String> accommodationTypes;
	
	public AccommodationTypesDTO() {
		this.accommodationTypes = new ArrayList<>();
	}

	public List<String> getAccommodationTypes() {
		return accommodationTypes;
	}

	public void setAccommodationTypes(List<String> accommodationTypes) {
		this.accommodationTypes = accommodationTypes;
	}
}
