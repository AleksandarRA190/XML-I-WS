package ftn.xmlws.dto;

import java.util.ArrayList;
import java.util.List;

import ftn.xmlws.model.PeriodPrice;

public class PeriodPricesDTO {
	
	private List<PeriodPriceDTO> periodPrices;
	
	public PeriodPricesDTO() {
		this.periodPrices = new ArrayList<>();
	}

	public List<PeriodPriceDTO> getPeriodPrices() {
		return periodPrices;
	}

	public void setPeriodPrices(List<PeriodPriceDTO> periodPrices) {
		this.periodPrices = periodPrices;
	}
}
