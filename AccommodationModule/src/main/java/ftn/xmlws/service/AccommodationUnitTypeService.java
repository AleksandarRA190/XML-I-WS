package ftn.xmlws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.dto.AccommodationUnitTypeDTO;
import ftn.xmlws.model.AccommodationUnitType;
import ftn.xmlws.repository.AccommodationUnitTypeRepository;

@Service
public class AccommodationUnitTypeService {

	@Autowired
	public AccommodationUnitTypeRepository accommodationUnitTypeRepository;
	
	public List<AccommodationUnitTypeDTO> getAllAccommodationUnitTypes() {
		List<AccommodationUnitType> list = accommodationUnitTypeRepository.findAll();
		List<AccommodationUnitTypeDTO> listDTO = new ArrayList<>();
		for(AccommodationUnitType aut: list) {
			if(!aut.isDeleted()) 
				listDTO.add(new AccommodationUnitTypeDTO(aut));
		}
		return listDTO;
	}
	
	public AccommodationUnitType findAccommodationUnitType(Long id) {
		AccommodationUnitType aut = null;
		try {
			aut  = 	accommodationUnitTypeRepository.getOne(id);
		} catch (EntityNotFoundException e) {
			return null;
		}	
		return aut;
	}
	
	public AccommodationUnitType saveAccommodationUnitType(AccommodationUnitTypeDTO autDTO) {
		AccommodationUnitType aut = new AccommodationUnitType();
		aut.setName(autDTO.getName());
		return accommodationUnitTypeRepository.save(aut);
	}
	
	public AccommodationUnitType updateAccommodationUnitType(AccommodationUnitType aut) {
		return accommodationUnitTypeRepository.save(aut);
	}
	
	public boolean removeAccommodationUnitType(Long autId) {
		AccommodationUnitType accommodationUnitType = this.findAccommodationUnitType(autId);
		if(accommodationUnitType == null)
			return false;
		accommodationUnitType.setDeleted(true);
		accommodationUnitTypeRepository.save(accommodationUnitType);
		return true;
	}
}
