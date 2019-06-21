package ftn.xmlws.dto;

import ftn.xmlws.model.AccommodationUnit;
import ftn.xmlws.model.AccommodationUnitType;

public class AccommodationUnitDTO {
	
    private Long id;	
    private int floor;
    private String number;
    private int numberOfBeds;
    private Double defaultPrice;
    private AccommodationDTO accommodation;
    private AccommodationUnitTypeDTO accommodationUnitType;
    
    public AccommodationUnitDTO() {}
    
    public AccommodationUnitDTO(AccommodationUnit unit) {
    	this.id = unit.getId();
    	this.floor = unit.getFloor();
    	this.number = unit.getNumber();
    	this.numberOfBeds = unit.getNumberOfBeds();
    	this.defaultPrice = unit.getDefaultPrice();
    	this.accommodation = new AccommodationDTO(unit.getAccommodation());
    	this.accommodationUnitType = new AccommodationUnitTypeDTO(unit.getAccommodationUnitType());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public Double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(Double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public AccommodationDTO getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(AccommodationDTO accommodation) {
		this.accommodation = accommodation;
	}

	public AccommodationUnitTypeDTO getAccommodationUnitType() {
		return accommodationUnitType;
	}

	public void setAccommodationUnitType(AccommodationUnitTypeDTO accommodationUnitType) {
		this.accommodationUnitType = accommodationUnitType;
	}
}
