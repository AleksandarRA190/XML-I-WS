package ftn.xmlws.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

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
import ftn.xmlws.enums.AccommodationType;
import ftn.xmlws.model.Accommodation;
import ftn.xmlws.model.AccommodationUnit;
import ftn.xmlws.service.AccommodationService;
import ftn.xmlws.service.AccommodationUnitService;
import ftn.xmlws.service.ServiceService;
import ftn.xmlws.service.UploadService;

@RestController
@RequestMapping(value = "/accommodation")
public class AccommodationController {

	@Autowired
	private AccommodationService accommodationService;
	
	@Autowired 
	private AccommodationUnitService accommodationUnitService;
	
	@Autowired
	private ServiceService serviceService;
	
	 @Autowired
	 private UploadService storageService;
	 
	 private List<String> files = new ArrayList<String>();
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AccommodationsDTO> getAccommodations() {
		AccommodationsDTO accommodationsDTO = new AccommodationsDTO();
		accommodationsDTO.setAccommodations(accommodationService.getAllAccommodations());
		if(accommodationsDTO.getAccommodations().isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(accommodationsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/byUser/{username}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("username") String username) {
		AccommodationDTO accommodationDTO = accommodationService.getAccommodationByUser(username);
		if(accommodationDTO == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(accommodationDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("id") Long id) {
		Accommodation accommodation = accommodationService.findAccommodation(id);
		if(accommodation == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new AccommodationDTO(accommodation), HttpStatus.OK);
	}
	
	@RequestMapping(value="/withFreeUnits", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<AccommodationsDTO> getAccommodationsWithFreeUnits(@RequestBody AccommodationSearchDTO asDTO) {
		AccommodationsDTO accommodationsDTO = new AccommodationsDTO();
		accommodationsDTO.setAccommodations(accommodationService.getAccommodationWithFreeUnits(asDTO));
		if(accommodationsDTO.getAccommodations().isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(accommodationsDTO, HttpStatus.OK);
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
	
	
//	********************* ACCOMMODATION TYPE****************************
	@RequestMapping(value="/accommodationTypes", method = RequestMethod.GET)
	public ResponseEntity<AccommodationTypesDTO> getAllAccommodationTypes() {
		AccommodationTypesDTO accommodationTypesDTO = new AccommodationTypesDTO();
		List<String> retVal = new ArrayList<>();
		
		AccommodationType[] types = AccommodationType.values();
		for(int i=0; i<types.length; i++) {
			retVal.add(types[i].toString());
		}
		accommodationTypesDTO.setAccommodationTypes(retVal);
		return new ResponseEntity<>(accommodationTypesDTO, HttpStatus.OK);
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
	
	@RequestMapping(value="/{id}/freeUnits", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<AccommodationUnitsDTO> getFreeAccommodationUnits(@PathVariable("id") Long accommodationId,@RequestBody AccommodationUnitSearchDTO ausDTO) {
		AccommodationUnitsDTO unitsDTO = new AccommodationUnitsDTO();
		unitsDTO.setAccommodationUnits(accommodationUnitService.getFreeAccommodationUnits(ausDTO.getStartDate(), ausDTO.getEndDate(), accommodationId));
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
	@RequestMapping(value="/{id}/services", method = RequestMethod.GET)
	public ResponseEntity<ServicesDTO> getAccommodationServices(@PathVariable("id") Long accommodationId) {
		ServicesDTO servicesDTO = new  ServicesDTO();
		servicesDTO.setServices(serviceService.getAccommodationServices(accommodationId));
		if(servicesDTO.getServices().isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(servicesDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/services", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AccommodationDTO> addServiceToAccommodation(@PathVariable("id") Long accommodationId, @RequestBody ServiceDTO serviceDTO) {
		Accommodation accommodation = serviceService.addServiceToAccommodation(accommodationId, serviceDTO);
		if(accommodation == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new AccommodationDTO(accommodation), HttpStatus.OK);
	}

	@RequestMapping(value="/{aId}/services/{sId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeServiceFromAccomodation(@PathVariable("aId") Long accommodationId, @PathVariable("sId") Long serviceId) {
		boolean success = serviceService.removeServiceFromAccommodation(accommodationId, serviceId);
		if(!success)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/getAvgRating/{accommodationId}", method = RequestMethod.GET)
	public ResponseEntity<Double> getAvgRating(@PathVariable("accommodationId") Long id) {
		Double avg = this.accommodationService.getAvgRating(id);
		
		return new ResponseEntity<>(avg,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/comments", method = RequestMethod.GET)
	public ResponseEntity<AccommodationCommentsDTO> getAllComments(@PathVariable("id") Long accommodationId) {
		AccommodationCommentsDTO retVal = accommodationService.getAllComments(accommodationId);
		System.out.println(retVal.getComments().size());
		
		return new ResponseEntity<>(retVal,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/image", method = RequestMethod.GET)
	public ResponseEntity<List<Byte>> getImage(@PathVariable("id") Long accommodationId) {
		List<Byte> retVal = new ArrayList<Byte>();
		try {
			retVal = accommodationService.getImages(accommodationId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(retVal,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getallfiles", method = RequestMethod.GET)
	  public ResponseEntity<List<String>> getListFiles(Model model) {
		storageService.init();
		List<String> fileNames = files
	        .stream().map(fileName -> MvcUriComponentsBuilder
	            .fromMethodName(AccommodationController.class, "getFile", fileName).build().toString())
	        .collect(Collectors.toList());
	 
	    return ResponseEntity.ok().body(fileNames);
	  }
	 
	  @GetMapping("/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		  storageService.init();
		  Resource file = storageService.loadFile(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
	        .body(file);
	  }
	
	
	
	
	
	
	
	
	
	
	
}
