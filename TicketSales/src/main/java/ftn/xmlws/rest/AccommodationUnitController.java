package ftn.xmlws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ftn.xmlws.dto.AccommodationUnitDTO;
import ftn.xmlws.dto.PeriodPriceDTO;
import ftn.xmlws.dto.PeriodPriceDatesDTO;
import ftn.xmlws.dto.PeriodPricesDTO;

@RestController
@RequestMapping(value = "/accommodation/units")
public class AccommodationUnitController {

	@Autowired
	private RestTemplate restTemplate;

	private static final String uri = "http://accommodation-service/accommodation/units";

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitDTO> getAccommodationUnit(@PathVariable("id") Long unitId) {
		AccommodationUnitDTO unit = restTemplate.getForObject(uri + "/" + unitId, AccommodationUnitDTO.class);
		return new ResponseEntity<>(unit, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> updateAccommodationUnit(@PathVariable("id") Long unitId,
			@RequestBody AccommodationUnitDTO unitDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AccommodationUnitDTO> request = new HttpEntity<AccommodationUnitDTO>(headers);
		restTemplate.put(uri + "/" + unitId, request);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeAccommodationUnit(@PathVariable("id") Long unitId) {
		restTemplate.delete(uri + "/" + unitId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id}/prices", method = RequestMethod.GET)
	public ResponseEntity<PeriodPricesDTO> getPrices(@PathVariable("id") Long unitId) {
		PeriodPricesDTO prices = restTemplate.getForObject(uri + "/" + unitId + "/prices", PeriodPricesDTO.class);
		return new ResponseEntity<>(prices, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/prices", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PeriodPriceDTO> addPeriodPrice(@PathVariable("id") Long unitId,
			@RequestBody PeriodPriceDTO periodPriceDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PeriodPriceDTO> request = new HttpEntity<PeriodPriceDTO>(headers);
		PeriodPriceDTO price = restTemplate.postForObject(uri + "/" + unitId + "/prices", request,
				PeriodPriceDTO.class);
		return new ResponseEntity<>(price, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/priceForPeriod", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Double> getPriceForMonth(@PathVariable("id") Long unitId,
			@RequestBody PeriodPriceDatesDTO periodPriceDatesDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Double> request = new HttpEntity<Double>(headers);
		ResponseEntity<Double> price = restTemplate.postForEntity(uri + "/" + unitId + "/priceForPeriod", periodPriceDatesDTO,
				Double.class);
		return new ResponseEntity<>(price.getBody(), HttpStatus.OK);
	}

	@RequestMapping(value = "/prices/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> updatePeriodPrice(@PathVariable("id") Long priceId,
			@RequestBody PeriodPriceDTO periodPriceDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PeriodPriceDTO> request = new HttpEntity<PeriodPriceDTO>(headers);
		restTemplate.put(uri + "/prices/" + priceId, request);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/prices/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removePeriodPrice(@PathVariable("id") Long priceId) {
		restTemplate.delete(uri + "/prices/" + priceId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
