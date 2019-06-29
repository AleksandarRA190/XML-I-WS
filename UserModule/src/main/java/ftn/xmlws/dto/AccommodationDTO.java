package ftn.xmlws.dto;

import ftn.xmlws.enums.AccommodationType;
import ftn.xmlws.model.Accommodation;

public class AccommodationDTO {
	
	private Long id;
	private String name;
	private String description;
	private String category;
	private AccommodationType accommodationType;
	
	public AccommodationDTO() {
		
	}

	public AccommodationDTO(Long id, String name, String description, String category,
			AccommodationType accommodationType) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.accommodationType = accommodationType;
	}
	
	public AccommodationDTO(Accommodation a) {
		super();
		this.id = a.getId();
		this.name = a.getName();
		this.description = a.getDescription();
		this.category = a.getCategory();
		this.accommodationType = a.getAccommodationType();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public AccommodationType getAccommodationType() {
		return accommodationType;
	}

	public void setAccommodationType(AccommodationType accommodationType) {
		this.accommodationType = accommodationType;
	}
	
	

}
