package ftn.xmlws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ftn.xmlws.dto.AccommodationDTO;
import ftn.xmlws.model.Accommodation;
import ftn.xmlws.model.Address;
import ftn.xmlws.repository.AccommodationRepository;

@Service
public class AccommodationService {

	@Autowired
	private AccommodationRepository accommodationRepository;
	
//	@Autowired
//	private RestTemplate restTemplate;
//	
	
	public List<AccommodationDTO> getAllAccommodations() {
		List<Accommodation> list = accommodationRepository.findAll();
		if(list == null)
			return null;
		List<AccommodationDTO> listDTO = new ArrayList<>();
		for(Accommodation a: list) {
			if(!a.isDeleted()) 
				listDTO.add(new AccommodationDTO(a));
		}
		return listDTO;
	}
	
	public Accommodation findAccommodation(Long id) {
		Accommodation a = null; 
		try {
			a = accommodationRepository.getOne(id);
		} catch(EntityNotFoundException e) {
			return null;
		}
		return a;
	}
	
	public Accommodation saveAccommodation(AccommodationDTO aDTO) {
		Accommodation a = new Accommodation();
		a.setDescription(aDTO.getDescription());
		a.setName(aDTO.getName());
		a.setCategory(aDTO.getCategory());
		a.setAccommodationType(aDTO.getAccommodationType());
		Address address = new Address(aDTO.getAddress());
		a.setAddress(address);
		return accommodationRepository.save(a);
	}
	
	public Accommodation updateAccommodation(AccommodationDTO aDTO) {
		Accommodation a = accommodationRepository.getOne(aDTO.getId());
		a.setDescription(aDTO.getDescription());
		a.setName(aDTO.getName());
		a.setCategory(aDTO.getCategory());
		a.setAccommodationType(aDTO.getAccommodationType());
		return accommodationRepository.save(a);
	}
	
	public boolean removeAccommodation(Long aId) {
		Accommodation accommodation = this.findAccommodation(aId);
		if(accommodation == null)
			return false;
		accommodation.setDeleted(true);
		accommodationRepository.save(accommodation);
		return true;
	}
}
