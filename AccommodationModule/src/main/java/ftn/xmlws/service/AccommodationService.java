package ftn.xmlws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.dto.AccommodationDTO;
import ftn.xmlws.model.Accommodation;
import ftn.xmlws.repository.AccommodationRepository;

@Service
public class AccommodationService {

	@Autowired
	private AccommodationRepository accommodationRepository;
	
	
	public List<AccommodationDTO> getAllAccommodations() {
		List<Accommodation> list = accommodationRepository.findAll();
		if(list == null)
			return null;
		List<AccommodationDTO> listDTO = new ArrayList<>();
		list.stream().filter(item -> !item.isDeleted()).forEach(list::add);
		list.forEach(item -> {
			listDTO.add(new AccommodationDTO(item));
		});
		return listDTO;
	}
	
	public Accommodation findAccommodation(Long id) {
		Accommodation a = accommodationRepository.getOne(id);
		if(a == null || a.isDeleted())
			return null;
		return a;
	}
	
	public Accommodation saveAccommodation(AccommodationDTO aDTO) {
		Accommodation a = new Accommodation();
		a.setDescription(aDTO.getDescription());
		a.setName(aDTO.getName());
		a.setCategory(aDTO.getCategory());
		a.setAccommodationType(aDTO.getAccommodationType());
		//a.setAddress(aDTO.getAddress());
		//TREBA JOS SETOVATI ADRESU
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
