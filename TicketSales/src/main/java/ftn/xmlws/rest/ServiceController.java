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

import ftn.xmlws.dto.ServiceDTO;
import ftn.xmlws.dto.ServicesDTO;

@RestController
@RequestMapping(value = "/accommodation/services")
public class ServiceController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ServicesDTO> getAllServices() {
		ServicesDTO servicesDTO = restTemplate.getForObject("http://accommodation-service/accommodation/services",
				ServicesDTO.class);

		return new ResponseEntity<>(servicesDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ServiceDTO> getService(@PathVariable("id") Long serviceId) {
		ServiceDTO serviceDTO = restTemplate
				.getForObject("http://accommodation-service/accommodation/services/" + serviceId, ServiceDTO.class);

		return new ResponseEntity<>(serviceDTO, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ServiceDTO> addService(@RequestBody ServiceDTO serviceDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ServiceDTO> request = new HttpEntity<ServiceDTO>(serviceDTO, headers);

		ServiceDTO service = restTemplate.postForObject("http://accommodation-service/accommodation/services", request,
				ServiceDTO.class);
		return new ResponseEntity<>(service, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> updateService(@PathVariable("id") Long serviceId, @RequestBody ServiceDTO serviceDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ServiceDTO> request = new HttpEntity<ServiceDTO>(serviceDTO, headers);
		restTemplate.put("http://accommodation-service/accommodation/services/" + serviceId, request);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeService(@PathVariable("id") Long serviceId) {
		restTemplate.delete("http://accommodation-service/accommodation/services/" + serviceId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}