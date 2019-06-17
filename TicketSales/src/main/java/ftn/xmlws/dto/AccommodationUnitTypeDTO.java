package ftn.xmlws.dto;

import ftn.xmlws.model.AccommodationUnitType;

public class AccommodationUnitTypeDTO {

	private String name;
	private Long id;
	
	public AccommodationUnitTypeDTO() {
		
	}
	
	public AccommodationUnitTypeDTO(AccommodationUnitType aut) {
		name = aut.getName();
		id = aut.getId();
	}
	
//	public AccommodationUnitTypeDTO(com.projectxml.accommodationunit.AccommodationUnitType aut) {
//		name = aut.getName();
//		id = aut.getId();
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
