package ftn.xmlws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.dto.PeriodPriceDTO;
import ftn.xmlws.dto.PeriodPriceDatesDTO;
import ftn.xmlws.model.AccommodationUnit;
import ftn.xmlws.model.PeriodPrice;
import ftn.xmlws.repository.PeriodPriceRepository;

@Service
public class PeriodPriceService {

	@Autowired
	private PeriodPriceRepository periodPriceRepository;
	
	@Autowired
	private AccommodationUnitService accommodationUnitService;
	
	public List<PeriodPriceDTO> getAllPeriodPrices(Long auId) {
		List<PeriodPrice> list = accommodationUnitService.findAccommodationUnit(auId).getPeriodPrices();
		List<PeriodPriceDTO> listDTO = new ArrayList<>();
		for(PeriodPrice pp: list) {
			if(!pp.isDeleted())
				listDTO.add(new PeriodPriceDTO(pp));
		}
		return listDTO;
	}
	
	public PeriodPrice findPeriodPrice(Long id) {
		PeriodPrice pp = periodPriceRepository.getOne(id);
		if(pp == null || pp.isDeleted())
			return null;
		return pp;
	}
	
	public PeriodPrice savePeriodPrice(PeriodPriceDTO ppDTO, Long auId) {
		AccommodationUnit au = accommodationUnitService.findAccommodationUnit(auId);
		if(au == null)
			return null;
		PeriodPrice pp = new PeriodPrice();
		pp.setAccommodationUnit(au);
		pp.setPrice(ppDTO.getPrice());
		pp.setFromDate(ppDTO.getFromDate());
		pp.setToDate(ppDTO.getToDate());
		return periodPriceRepository.save(pp);
	}
	
	public PeriodPrice updatePeriodPrice(Long ppId, PeriodPriceDTO ppDTO) {
		PeriodPrice pp = this.findPeriodPrice(ppId);
		if(pp == null)
			return null;
		pp.setAccommodationUnit(accommodationUnitService.findAccommodationUnit(ppDTO.getAccommodationUnit().getId()));
		pp.setFromDate(ppDTO.getFromDate());
		pp.setToDate(ppDTO.getToDate());
		pp.setPrice(ppDTO.getPrice());
		return periodPriceRepository.save(pp);
	}
	
	public boolean removePeriodPrice(Long ppId) {
		PeriodPrice periodPrice = this.findPeriodPrice(ppId);
		if(periodPrice == null)
			return false;
		periodPrice.setDeleted(true);
		periodPriceRepository.save(periodPrice);
		return true;
	}
	
	public Double getPeriodPriceForMonth(Long auId, PeriodPriceDatesDTO ppdDTO) {
		AccommodationUnit au = accommodationUnitService.findAccommodationUnit(auId);
		if(au == null) 
			return null;
		List<PeriodPrice> prices = au.getPeriodPrices();
		for(PeriodPrice pp: prices) {
			if(pp.getFromDate().getMonth() == ppdDTO.getFromDate().MONTH) {
				return pp.getPrice();				
			} 
		}
		return au.getDefaultPrice();
	}
}
