package ftn.xmlws.dto;

import ftn.xmlws.enums.AccommodationType;
import ftn.xmlws.model.Accommodation;
import ftn.xmlws.model.Address;

public class AccommodationDTO {

	private String description;
	private String name;
	private String category;
	private Address address;
	private AccommodationType accommodationType;
	private Long id;
	
	public AccommodationDTO() {
		
	}
	
	public AccommodationDTO(Accommodation a) {
		description = a.getDescription();
		name = a.getName();
		category = a.getCategory();
		address = a.getAddress();
		accommodationType = a.getAccommodationType();
		id = a.getId();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public AccommodationType getAccommodationType() {
		return accommodationType;
	}

	public void setAccommodationType(AccommodationType accommodationType) {
		this.accommodationType = accommodationType;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
