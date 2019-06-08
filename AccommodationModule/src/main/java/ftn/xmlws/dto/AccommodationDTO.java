package ftn.xmlws.dto;

import ftn.xmlws.enums.AccommodationType;
import ftn.xmlws.model.Accommodation;

public class AccommodationDTO {

	private String description;
	private String name;
	private String category;
	private AddressDTO addressDTO;
	private AccommodationType accommodationType;
	private Long id;
	
	public AccommodationDTO() {
		
	}
	
	public AccommodationDTO(Accommodation a) {
		description = a.getDescription();
		name = a.getName();
		category = a.getCategory();
		addressDTO = new AddressDTO(a.getAddress());
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

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	public AccommodationType getAccommodationType() {
		return accommodationType;
	}

	public void setAccommodationTypeDTO(AccommodationType accommodationType) {
		this.accommodationType = accommodationType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
