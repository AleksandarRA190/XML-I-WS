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

import ftn.xmlws.model.Address;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Address> getAddress(@PathVariable("id") Long id) {
		
		Address address = restTemplate.getForObject("http://user-service/address/"+id, Address.class);
		
		if (address == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(address, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> addAddress(@RequestBody Address address) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Address> request = new HttpEntity<>(address,headers);
		
		restTemplate.put("http://user-service/address/add", request);
		return new ResponseEntity<>(HttpStatus.OK);	
	}


	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Address> request = new HttpEntity<>(address,headers);
		
		Address updatedAddress = restTemplate.postForObject("http://user-service/address/update",request,Address.class);
		
		return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {

		String url = "http://user-service/address/"+id;
		restTemplate.delete(url);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
