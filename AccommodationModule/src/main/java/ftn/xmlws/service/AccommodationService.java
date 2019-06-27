package ftn.xmlws.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ftn.xmlws.dto.AccommodationDTO;
import ftn.xmlws.dto.AccommodationSearchDTO;
import ftn.xmlws.dto.AccommodationUnitDTO;
import ftn.xmlws.dto.ServiceDTO;
import ftn.xmlws.dto.UserDTO;
import ftn.xmlws.enums.Role;
import ftn.xmlws.model.Accommodation;
import ftn.xmlws.model.Address;
import ftn.xmlws.repository.AccommodationRepository;

@Service
public class AccommodationService {

	@Autowired
	private AccommodationRepository accommodationRepository;
	
	@Autowired
	private AccommodationUnitService accommodationUnitService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
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
	
	public AccommodationDTO getAccommodationByUser(String username) {
		UserDTO userDTO = restTemplate.getForObject("http://localhost:9006/users/" + username, UserDTO.class);
		if(userDTO.getRole().equals(Role.AGENT)) {
			return userDTO.getAccommodation();
		}
		return null;
	}
	
	//kako uraditi ako je neko polje za pretragu nepopunjeno?
	public List<AccommodationDTO> getAccommodationWithFreeUnits(AccommodationSearchDTO asDTO) {
		List<AccommodationDTO> accommodationsDTO = this.getAllAccommodations();
		List<AccommodationDTO> freeAccommodations = new ArrayList<>();
		for(AccommodationDTO aDTO : accommodationsDTO) {
			List<AccommodationUnitDTO> unitsDTO = accommodationUnitService.getFreeAccommodationUnits(asDTO.getStartDate(), asDTO.getEndDate(), aDTO.getId());
			Accommodation a = accommodationRepository.getOne(aDTO.getId());
			if(!unitsDTO.isEmpty()) {
				int numberOfBeds = 0;
				for(AccommodationUnitDTO unitDTO: unitsDTO) {
					numberOfBeds += unitDTO.getNumberOfBeds();
				}
				if(numberOfBeds > asDTO.getNumberOfGuests()) {
					if(asDTO.getCategories().contains(aDTO.getCategory()) || asDTO.getCategories().isEmpty()) {
						if(asDTO.getAccommodationTypes().contains(aDTO.getAccommodationType()) || asDTO.getAccommodationTypes().isEmpty()) {
							int numberOfServices = 0;
							for(ServiceDTO sDTO : asDTO.getServices()) {
								for(ftn.xmlws.model.Service s : a.getServices()) {
									if(sDTO.getName().equals(s.getName())) {
										numberOfServices ++;
									}
								}
							}
							if(numberOfServices >= asDTO.getServices().size()) 
								freeAccommodations.add(aDTO);
						}
					}
				}					
			}
		}
		return freeAccommodations;
	}
	
	public Accommodation findAccommodation(Long id) {
		Accommodation a = null; 
		try {
			a = accommodationRepository.getOne(id);
			if(a.isDeleted())
				return null;
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
