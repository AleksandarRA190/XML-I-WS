package ftn.xmlws.dto;

import ftn.xmlws.model.Address;

public class AddressDTO {

	private String country;
	private String city;
	private int postalCode;
	private String street;
	private String number;
	private String apartmentNumber;
	private double longitude;
	private double latitude;
	private Long id;
	
	public AddressDTO() {
		
	}
	
	public AddressDTO(Address address) {
		country = address.getCountry();
		city = address.getCity();
		postalCode = address.getPostalCode();
		street = address.getStreet();
		number = address.getNumber();
		apartmentNumber = address.getApartmentNumber();
		longitude = address.getLongitude();
		latitude = address.getLatitude();
		id = address.getId();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
