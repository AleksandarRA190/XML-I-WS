package ftn.xmlws.dto;

import java.time.LocalDate;
import java.util.GregorianCalendar;

public class PeriodPriceDatesDTO {

	private LocalDate fromDate;
	private LocalDate toDate;
	
	public PeriodPriceDatesDTO() {
		
	}
	
	public PeriodPriceDatesDTO(LocalDate fromDate, LocalDate toDate) {
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
}
