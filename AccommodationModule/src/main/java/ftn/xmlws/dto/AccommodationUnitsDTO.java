package ftn.xmlws.dto;

import java.util.ArrayList;
import java.util.List;

public class AccommodationUnitsDTO {

	private List<AccommodationUnitDTO> accommodationUnits;
	
	public AccommodationUnitsDTO() {
		this.accommodationUnits = new ArrayList<>();
	}

	public List<AccommodationUnitDTO> getAccommodationUnits() {
		return accommodationUnits;
	}

	public void setAccommodationUnits(List<AccommodationUnitDTO> accommodationUnits) {
		this.accommodationUnits = accommodationUnits;
	}
}
