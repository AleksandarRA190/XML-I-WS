package ftn.xmlws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ftn.xmlws.dto.AccommodationDTO;
import ftn.xmlws.dto.AccommodationSearchDTO;
import ftn.xmlws.dto.AccommodationsDTO;

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
}
