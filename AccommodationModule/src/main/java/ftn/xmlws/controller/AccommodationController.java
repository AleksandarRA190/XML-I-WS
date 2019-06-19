package ftn.xmlws.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.xmlws.dto.AccommodationDTO;
import ftn.xmlws.dto.AccommodationUnitDTO;
import ftn.xmlws.dto.AccommodationUnitsDTO;
import ftn.xmlws.dto.AccommodationsDTO;
import ftn.xmlws.dto.ServiceDTO;
import ftn.xmlws.dto.ServicesDTO;
import ftn.xmlws.model.Accommodation;
import ftn.xmlws.model.AccommodationUnit;
import ftn.xmlws.service.AccommodationService;
import ftn.xmlws.service.AccommodationUnitService;
import ftn.xmlws.service.ServiceService;

@RestController
@RequestMapping(value = "/accommodation")
public class AccommodationController {

	@Autowired
	private AccommodationService accommodationService;
	
	@Autowired 
	private AccommodationUnitService accommodationUnitService;
	
	@Autowired
	private ServiceService serviceService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AccommodationsDTO> getAccommodations() {
		AccommodationsDTO accommodationsDTO = new AccommodationsDTO();
		accommodationsDTO.setAccommodations(accommodationService.getAllAccommodations());
		if(accommodationsDTO.getAccommodations().isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(accommodationsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("id") Long id) {
		Accommodation accommodation = accommodationService.findAccommodation(id);
		if(accommodation == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new AccommodationDTO(accommodation), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AccommodationDTO> addAccommodation(@RequestBody AccommodationDTO accommodationDTO) {
		Accommodation accommodation = accommodationService.saveAccommodation(accommodationDTO);
		if(accommodation == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new AccommodationDTO(accommodation), HttpStatus.CREATED);
	}
		
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<AccommodationDTO> updateAccommodation(@RequestBody AccommodationDTO accommodationDTO) {
		Accommodation accommodation = accommodationService.updateAccommodation(accommodationDTO);
		if(accommodation == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new AccommodationDTO(accommodation), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeAccommodation(@PathVariable("id") Long id) {
		boolean success = accommodationService.removeAccommodation(id);
		if(!success)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	//***************** ACCOMMODATION UNITS******************************
	
	
	@RequestMapping(value="/{id}/units", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitsDTO> getAccommodationUnits(@PathVariable("id") Long accommodationId) {
		AccommodationUnitsDTO unitsDTO = new AccommodationUnitsDTO();
		unitsDTO.setAccommodationUnits(accommodationUnitService.getAllUnitsOfAccommodation(accommodationId));
		if(unitsDTO.getAccommodationUnits().isEmpty()) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(unitsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="{id}/units", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AccommodationUnitDTO> addAccommodationUnit(@PathVariable("id") Long id, @RequestBody AccommodationUnitDTO unitDTO) {
		AccommodationUnit unit = accommodationUnitService.saveAccommodationUnit(unitDTO, id);
		if(unit == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new AccommodationUnitDTO(unit), HttpStatus.CREATED);
	}
	
	//*********************** ACCOMMODATION SERVICES ***********************
	@RequestMapping(value="{id}/services", method = RequestMethod.GET)
	public ResponseEntity<ServicesDTO> getAccommodationServices(@PathVariable("id") Long accommodationId) {
		ServicesDTO servicesDTO = new  ServicesDTO();
		servicesDTO.setServices(serviceService.getAccommodationServices(accommodationId));
		if(servicesDTO.getServices().isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(servicesDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="{id}/services", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AccommodationDTO> addServiceToAccommodation(@PathVariable("id") Long accommodationId, @RequestBody ServiceDTO serviceDTO) {
		Accommodation accommodation = serviceService.addServiceToAccommodation(accommodationId, serviceDTO);
		if(accommodation == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new AccommodationDTO(accommodation), HttpStatus.OK);
	}

	@RequestMapping(value="{aId}/services/{sId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeServiceFromAccomodation(@PathVariable("aId") Long accommodationId, @PathVariable("sId") Long serviceId) {
		boolean success = serviceService.removeServiceFromAccommodation(accommodationId, serviceId);
		if(!success)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
}
