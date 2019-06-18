package ftn.xmlws.dto;

import java.util.ArrayList;
import java.util.List;

public class AccommodationUnitTypesDTO {

	private List<AccommodationUnitTypeDTO> accommodationUnitTypes;
	
	public AccommodationUnitTypesDTO() {
		this.accommodationUnitTypes = new ArrayList<>();
	}

	public List<AccommodationUnitTypeDTO> getAccommodationUnitTypes() {
		return accommodationUnitTypes;
	}

	public void setAccommodationUnitTypes(List<AccommodationUnitTypeDTO> accommodationUnitTypes) {
		this.accommodationUnitTypes = accommodationUnitTypes;
	}
}
