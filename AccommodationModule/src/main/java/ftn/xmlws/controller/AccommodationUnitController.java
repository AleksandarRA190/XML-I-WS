package ftn.xmlws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.xmlws.dto.AccommodationUnitDTO;
import ftn.xmlws.dto.PeriodPriceDTO;
import ftn.xmlws.dto.PeriodPriceDatesDTO;
import ftn.xmlws.model.AccommodationUnit;
import ftn.xmlws.model.PeriodPrice;
import ftn.xmlws.service.AccommodationUnitService;
import ftn.xmlws.service.PeriodPriceService;

@RestController
@RequestMapping(value = "/accommodation/units")
public class AccommodationUnitController {
	
	@Autowired
	private AccommodationUnitService accommodationUnitService;
	
	@Autowired
	private PeriodPriceService periodPriceService;

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitDTO> getAccommodationUnit(@PathVariable("id") Long unitId) {
		AccommodationUnit unit = accommodationUnitService.findAccommodationUnit(unitId);
		if(unit == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new AccommodationUnitDTO(unit), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<AccommodationUnitDTO> updateAccommodationUnit(@PathVariable("id") Long unitId, @RequestBody AccommodationUnitDTO unitDTO) {
		AccommodationUnit unit = accommodationUnitService.updateAccommodationUnit(unitId, unitDTO);
		if(unit == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new AccommodationUnitDTO(unit), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeAccommodationUnit(@PathVariable("id") Long unitId) {
		boolean success = accommodationUnitService.removeAccommodationUnit(unitId);
		if(!success)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="{id}/prices", method = RequestMethod.GET)
	public ResponseEntity<List<PeriodPriceDTO>> getPrices(@PathVariable("id") Long unitId) {
		List<PeriodPriceDTO> pricesDTO = periodPriceService.getAllPeriodPrices(unitId);
		if(pricesDTO == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(pricesDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/prices", method = RequestMethod.POST, consumes = "application/json") 
	public ResponseEntity<PeriodPriceDTO> addPeriodPrice(@PathVariable("id") Long unitId, @RequestBody PeriodPriceDTO periodPriceDTO) {
		PeriodPrice periodPrice = periodPriceService.savePeriodPrice(periodPriceDTO, unitId);
		if(periodPrice == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new PeriodPriceDTO(periodPrice), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}/priceForPeriod", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Double> getPriceForMonth(@PathVariable("id") Long unitId, @RequestBody PeriodPriceDatesDTO periodPricesDatesDTO) {
		Double price = periodPriceService.getPeriodPriceForMonth(unitId, periodPricesDatesDTO);
		if(price == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(price, HttpStatus.OK);
	}
	
	@RequestMapping(value="/prices/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<PeriodPriceDTO> updatePeriodPrice(@PathVariable("id") Long priceId, @RequestBody PeriodPriceDTO periodPriceDTO) {
		PeriodPrice periodPrice = periodPriceService.updatePeriodPrice(priceId, periodPriceDTO);
		if(periodPrice == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new PeriodPriceDTO(periodPrice), HttpStatus.OK);
	}
	
	@RequestMapping(value="/prices/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Void> removePeriodPrice(@PathVariable("id") Long priceId) {
		boolean success = periodPriceService.removePeriodPrice(priceId);
		if(!success)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
}
