package ftn.xmlws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.xmlws.dto.AccommodationUnitTypeDTO;
import ftn.xmlws.dto.AccommodationUnitTypesDTO;
import ftn.xmlws.model.AccommodationUnitType;
import ftn.xmlws.service.AccommodationUnitTypeService;

@RestController
@RequestMapping("/accommodationUnitType")
public class AccommodationUnitTypeController {

	@Autowired
	private AccommodationUnitTypeService accommodationUnitTypeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitTypesDTO> getAllAccommodationUnitTypes() {
		AccommodationUnitTypesDTO autDTO = new AccommodationUnitTypesDTO();
		autDTO.setAccommodationUnitTypes(accommodationUnitTypeService.getAllAccommodationUnitTypes());
		if(autDTO.getAccommodationUnitTypes().isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(autDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitTypeDTO> getAccommmodationUnitType(@PathVariable("id") Long id) {
		AccommodationUnitType aut = accommodationUnitTypeService.findAccommodationUnitType(id);
		if(aut == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new AccommodationUnitTypeDTO(aut), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AccommodationUnitTypeDTO> addAccommodationUnitType(@RequestBody AccommodationUnitTypeDTO autDTO) {
		AccommodationUnitType aut = accommodationUnitTypeService.saveAccommodationUnitType(autDTO);
		if(aut == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new AccommodationUnitTypeDTO(aut), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<AccommodationUnitTypeDTO> updateAccommodationUnitType(@PathVariable("id") Long id, @RequestBody AccommodationUnitTypeDTO autDTO) {
		AccommodationUnitType aut = accommodationUnitTypeService.updateAccommodationUnitType(id, autDTO);
		if(aut == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new AccommodationUnitTypeDTO(aut), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeAccommodationUnitType(@PathVariable("id") Long id) {
		boolean success = accommodationUnitTypeService.removeAccommodationUnitType(id);
		if(!success)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
