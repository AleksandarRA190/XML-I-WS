package ftn.xmlws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ftn.xmlws.model.Address;
import ftn.xmlws.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public List<Address> getAllAddresses() {
		List<Address> list = new ArrayList<Address>();
		this.addressRepository.findAll().forEach(list::add);
		
		return list;
	}

	public Address getAddress(Long id) {
		return this.addressRepository.getOne(id);
	}

	public void addAddress(Address address) {
		Address a = new Address(address);
		this.addressRepository.save(a);
	}

	public void removeAddress(Long id) {
		Address address = this.addressRepository.getOne(id);
		address.setDeleted(true);
		this.addressRepository.save(address);
	}

	public Address updateAddress(Address address) {
		return this.addressRepository.save(address);
	}
	
}
