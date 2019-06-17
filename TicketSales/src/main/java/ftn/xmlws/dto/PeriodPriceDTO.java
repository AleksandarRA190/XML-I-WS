package ftn.xmlws.dto;

import java.util.GregorianCalendar;

import ftn.xmlws.model.PeriodPrice;

public class PeriodPriceDTO {

	private GregorianCalendar fromDate;
	private GregorianCalendar toDate;
	private double price;
	private Long id;
	private AccommodationUnitDTO accommodationUnit;
	
	public PeriodPriceDTO() {
		
	}
	
	public PeriodPriceDTO(PeriodPrice pp) {
		fromDate = pp.getFromDate().toGregorianCalendar();
		toDate = pp.getToDate().toGregorianCalendar();
		price = pp.getPrice();
		id = pp.getId();
		accommodationUnit = new AccommodationUnitDTO(pp.getAccommodationUnit());
	}

	public GregorianCalendar getFromDate() {
		return fromDate;
	}

	public void setFromDate(GregorianCalendar fromDate) {
		this.fromDate = fromDate;
	}

	public GregorianCalendar getToDate() {
		return toDate;
	}

	public void setToDate(GregorianCalendar toDate) {
		this.toDate = toDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccommodationUnitDTO getAccommodationUnit() {
		return accommodationUnit;
	}

	public void setAccommodationUnit(AccommodationUnitDTO accommodationUnit) {
		this.accommodationUnit = accommodationUnit;
	}
}
