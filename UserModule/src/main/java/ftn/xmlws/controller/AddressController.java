package ftn.xmlws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.xmlws.model.Address;
import ftn.xmlws.service.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Address> getAddress(@PathVariable("id") Long id) {
		Address address = addressService.getAddress(id);
		if (address == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(address, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> addAddress(@RequestBody Address address) {

		addressService.addAddress(address);
		
		return new ResponseEntity<>(HttpStatus.CREATED);	
	}


	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address) {

		Address updatedAddress = addressService.updateAddress(address);

		return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {

		addressService.removeAddress(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
