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

import ftn.xmlws.dto.AccommodationUnitTypeDTO;
import ftn.xmlws.dto.AccommodationUnitTypesDTO;

@RestController
@RequestMapping("/accommodationUnitType")
public class AccommodationUnitTypeController {

	@Autowired
	private RestTemplate restTemplate;

	private static final String uri = "http://accommodation-service/accommodation/accommodationUnitType";

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitTypesDTO> getAllAccommodationUnitTypes() {
		AccommodationUnitTypesDTO unitTypes = restTemplate.getForObject(uri, AccommodationUnitTypesDTO.class);
		return new ResponseEntity<>(unitTypes, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitTypeDTO> getAccommmodationUnitType(@PathVariable("id") Long id) {

		AccommodationUnitTypeDTO unitType = restTemplate.getForObject(uri + "/" + id, AccommodationUnitTypeDTO.class);
		return new ResponseEntity<>(unitType, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AccommodationUnitTypeDTO> addAccommodationUnitType(
			@RequestBody AccommodationUnitTypeDTO autDTO) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AccommodationUnitTypeDTO> request = new HttpEntity<>(autDTO, headers);

		AccommodationUnitTypeDTO type = restTemplate.postForObject(uri, request, AccommodationUnitTypeDTO.class);
		return new ResponseEntity<>(type, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> updateAccommodationUnitType(@PathVariable("id") Long id,
			@RequestBody AccommodationUnitTypeDTO autDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AccommodationUnitTypeDTO> request = new HttpEntity<AccommodationUnitTypeDTO>(headers);
		restTemplate.put(uri + "/" + id, request);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeAccommodationUnitType(@PathVariable("id") Long id) {
		restTemplate.delete(uri + "/" + id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}