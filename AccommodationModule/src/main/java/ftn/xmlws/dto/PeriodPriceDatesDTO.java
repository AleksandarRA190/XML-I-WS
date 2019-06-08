package ftn.xmlws.dto;

import java.util.GregorianCalendar;

public class PeriodPriceDatesDTO {

	private GregorianCalendar fromDate;
	private GregorianCalendar toDate;
	
	public PeriodPriceDatesDTO() {
		
	}
	
	public PeriodPriceDatesDTO(GregorianCalendar fromDate, GregorianCalendar toDate) {
		this.fromDate = fromDate;
		this.toDate = toDate;
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
}
