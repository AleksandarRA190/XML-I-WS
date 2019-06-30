package ftn.xmlws.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ftn.xmlws.dto.AccommodationCommentsDTO;
import ftn.xmlws.dto.AccommodationDTO;
import ftn.xmlws.dto.AccommodationSearchDTO;
import ftn.xmlws.dto.AccommodationTypesDTO;
import ftn.xmlws.dto.AccommodationUnitDTO;
import ftn.xmlws.dto.AccommodationUnitSearchDTO;
import ftn.xmlws.dto.AccommodationUnitsDTO;
import ftn.xmlws.dto.AccommodationsDTO;
import ftn.xmlws.dto.ServiceDTO;
import ftn.xmlws.dto.ServicesDTO;
import ftn.xmlws.model.AccommodationUnit;

@RestController
@RequestMapping(value = "/accommodation")
public class AccommodationController {

	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AccommodationsDTO> getAccommodations() {
		AccommodationsDTO as = restTemplate.getForObject("http://accommodation-service/accommodation", AccommodationsDTO.class);
		
		return new ResponseEntity<>(as, HttpStatus.OK);
	}
	
	@RequestMapping(value="/byUser/{username}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("username") String username) {
		AccommodationDTO a = restTemplate.getForObject("http://accommodation-service/accommodation/byUser/" + username, AccommodationDTO.class);
		return new ResponseEntity<>(a, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("id") Long id) {
		AccommodationDTO a = restTemplate.getForObject("http://accommodation-service/accommodation/" + id, AccommodationDTO.class);
		return new ResponseEntity<>(a, HttpStatus.OK);
	}
	
	@RequestMapping(value="/withFreeUnits", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<AccommodationsDTO> getAccommodationsWithFreeUnits(@RequestBody AccommodationSearchDTO asDTO) {
		AccommodationsDTO as = restTemplate.postForObject("http://accommodation-service/accommodation/withFreeUnits", asDTO ,AccommodationsDTO.class);
		return new ResponseEntity<>(as, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AccommodationDTO> addAccommodation(@RequestBody AccommodationDTO accommodationDTO) {
		AccommodationDTO a = restTemplate.postForObject("http://accommodation-service/accommodation", accommodationDTO, AccommodationDTO.class);
		return new ResponseEntity<>(a, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> updateAccommodation(@PathVariable("id") Long id, @RequestBody AccommodationDTO accommodationDTO) {
		restTemplate.put("http://accommodation-service/accommodation/" + id, accommodationDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeAccommodation(@PathVariable("id") Long id) {
		restTemplate.delete("http://accommodation-service/accommodation/" + id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/accommodationTypes", method = RequestMethod.GET)
	public ResponseEntity<AccommodationTypesDTO> getAllAccommodationTypes() {
		AccommodationTypesDTO at = restTemplate.getForObject("http://accommodation-service/accommodation/accommodationTypes", AccommodationTypesDTO.class);
		return new ResponseEntity<>(at,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{id}/units", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitsDTO> getAccommodationUnits(@PathVariable("id") Long accommodationId) {
		AccommodationUnitsDTO au = restTemplate.getForObject("http://accommodation-service/accommodation/" + accommodationId + "/units", AccommodationUnitsDTO.class);
		return new ResponseEntity<>(au,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/freeUnits", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<AccommodationUnitsDTO> getFreeAccommodationUnits(@PathVariable("id") Long accommodationId,@RequestBody AccommodationUnitSearchDTO ausDTO) {
		AccommodationUnitsDTO au = restTemplate.postForObject("http://accommodation-service/accommodation/" + accommodationId + "/freeUnits", ausDTO, AccommodationUnitsDTO.class);
		return new ResponseEntity<>(au, HttpStatus.OK);
	}
	
	@RequestMapping(value="{id}/units", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AccommodationUnitDTO> addAccommodationUnit(@PathVariable("id") Long id, @RequestBody AccommodationUnitDTO unitDTO) {
		AccommodationUnitDTO unit = restTemplate.postForObject("http://accommodation-service/accommodation/units/" + id, unitDTO, AccommodationUnitDTO.class);
		return new ResponseEntity<>(unit, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}/services", method = RequestMethod.GET)
	public ResponseEntity<ServicesDTO> getAccommodationServices(@PathVariable("id") Long accommodationId) {
		ServicesDTO s = restTemplate.getForObject("http://accommodation-service/accommodation/" + accommodationId + "/services", ServicesDTO.class);
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/services", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AccommodationDTO> addServiceToAccommodation(@PathVariable("id") Long accommodationId, @RequestBody ServiceDTO serviceDTO) {
		AccommodationDTO a = restTemplate.postForObject("http://accommodation-service/accommodation/" + accommodationId + "/services" , serviceDTO, AccommodationDTO.class);
		return new ResponseEntity<>(a,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{aId}/services/{sId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeServiceFromAccomodation(@PathVariable("aId") Long accommodationId, @PathVariable("sId") Long serviceId) {
		restTemplate.delete("http://accommodation-service/accommodation/" + accommodationId + "/services/" + serviceId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAvgRating/{accommodationId}", method = RequestMethod.GET)
	public ResponseEntity<Double> getAvgRating(@PathVariable("accommodationId") Long id) {
		Double rating = restTemplate.getForObject("http://accommodation-service/accommodation/getAvgRating/" + id, Double.class);
		return new ResponseEntity<>(rating, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/comments", method = RequestMethod.GET)
	public ResponseEntity<AccommodationCommentsDTO> getAllComments(@PathVariable("id") Long accommodationId) {
		AccommodationCommentsDTO ac = restTemplate.getForObject("http://accommodation-service/accommodation/" + accommodationId  + "/comments", AccommodationCommentsDTO.class);
		return new ResponseEntity<>(ac, HttpStatus.OK);
	}
	
//	 ******************* IMAGES **************************
	
	
}
