package ftn.xmlws.dto;

import ftn.xmlws.model.AccommodationUnit;

public class AccommodationUnitDTO {
	
    private Long id;	
    private int floor;
    private String number;
    private int numberOfBeds;
    private Double defaultPrice;
    //protected Accommodation accommodation;
    
    public AccommodationUnitDTO() {}
    
    public AccommodationUnitDTO(AccommodationUnit unit) {
    	this.id = unit.getId();
    	this.floor = unit.getFloor();
    	this.number = unit.getNumber();
    	this.numberOfBeds = unit.getNumberOfBeds();
    	this.defaultPrice = unit.getDefaultPrice();
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
    
    
    

}
