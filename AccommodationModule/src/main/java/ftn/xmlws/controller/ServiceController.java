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

import ftn.xmlws.dto.ServiceDTO;
import ftn.xmlws.model.Service;
import ftn.xmlws.service.ServiceService;

@RestController
@RequestMapping(value = "/services")
public class ServiceController {

	@Autowired
	private ServiceService serviceService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ServiceDTO>> getAllServices() {
		List<ServiceDTO> servicesDTO = serviceService.getAllServices();
		return new ResponseEntity<>(servicesDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public ResponseEntity<ServiceDTO> getService(@PathVariable("id") Long serviceId) {
		Service service = serviceService.findService(serviceId);
		if(service == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new ServiceDTO(service), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ServiceDTO> addService(@RequestBody ServiceDTO serviceDTO) {
		Service service = serviceService.saveService(serviceDTO);
		if(service == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new ServiceDTO(service), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ServiceDTO> updateService(@PathVariable("id") Long serviceId, @RequestBody ServiceDTO serviceDTO) {
		Service service = serviceService.updateService(serviceDTO, serviceId);
		if(service == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(new ServiceDTO(service), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeService(@PathVariable("id") Long serviceId) {
		boolean success = serviceService.removeService(serviceId);
		if(!success)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
