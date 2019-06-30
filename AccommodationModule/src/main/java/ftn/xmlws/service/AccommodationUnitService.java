package ftn.xmlws.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ftn.xmlws.dto.AccommodationUnitDTO;
import ftn.xmlws.dto.ReservationDTO;
import ftn.xmlws.dto.ReservationsDTO;
import ftn.xmlws.model.Accommodation;
import ftn.xmlws.model.AccommodationUnit;
import ftn.xmlws.model.AccommodationUnitType;
import ftn.xmlws.repository.AccommodationUnitRepository;

@Service
public class AccommodationUnitService {

	@Autowired
	private AccommodationUnitRepository accommodationUnitRepository;
	
	@Autowired
	private AccommodationUnitTypeService accommodationUnitTypeService;
	
	@Autowired
	private AccommodationService accommodationService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<AccommodationUnitDTO> getAllAccommodationUnits() {
		List<AccommodationUnit> list = accommodationUnitRepository.findAll(); 
		List<AccommodationUnitDTO> listDTO = new ArrayList<>();
		for(AccommodationUnit au : list) {
			if(!au.isDeleted())
				listDTO.add(new AccommodationUnitDTO(au));
		}
		return listDTO;
	}
	
	public AccommodationUnit findAccommodationUnit(Long id) {
		AccommodationUnit au = accommodationUnitRepository.getOne(id);
		if(au == null || au.isDeleted())
			return null;
		return au;
	}
	
	public AccommodationUnit saveAccommodationUnit(AccommodationUnitDTO auDTO, Long accommodationId) {
		Accommodation a = accommodationService.findAccommodation(accommodationId);
		if(a == null) 
			return null;
		AccommodationUnit au = new AccommodationUnit();
		au.setFloor(auDTO.getFloor());
		au.setNumber(auDTO.getNumber());
		au.setNumberOfBeds(auDTO.getNumberOfBeds());
		au.setDefaultPrice(auDTO.getDefaultPrice());
		AccommodationUnitType aut = accommodationUnitTypeService.findAccommodationUnitType(auDTO.getAccommodationUnitType().getId());
		au.setAccommodationUnitType(aut);			
		au.setAccommodation(a);
		return accommodationUnitRepository.save(au);
	}
	
	public AccommodationUnit updateAccommodationUnit(Long auId, AccommodationUnitDTO auDTO) {
		AccommodationUnit au = this.findAccommodationUnit(auId);
		if(au == null)
			return null;
		au.setFloor(auDTO.getFloor());
		au.setNumber(auDTO.getNumber());
		au.setNumberOfBeds(auDTO.getNumberOfBeds());
		au.setDefaultPrice(auDTO.getDefaultPrice());
		au.setAccommodationUnitType(accommodationUnitTypeService.findAccommodationUnitType(auDTO.getAccommodationUnitType().getId()));
		Accommodation a = accommodationService.findAccommodation(auDTO.getAccommodation().getId());	
		au.setAccommodation(a);
		return accommodationUnitRepository.save(au);
	}
	
	public boolean removeAccommodationUnit(Long auId) {
		AccommodationUnit accommodationUnit = this.findAccommodationUnit(auId);
		if(accommodationUnit == null)
			return false;
		accommodationUnit.setDeleted(true);
		accommodationUnitRepository.save(accommodationUnit);
		return true;
	}
	
	public List<AccommodationUnitDTO> getAllUnitsOfAccommodation(Long accommodationId) {
		Accommodation a = accommodationService.findAccommodation(accommodationId);
		if(a == null)
			return null;
		List<AccommodationUnit> aus = a.getAccommodationUnits();
		List<AccommodationUnitDTO> ausDTO = new ArrayList<>();
		for(AccommodationUnit au: aus) {
			if(!au.isDeleted()) {
				AccommodationUnitDTO auDTO = new AccommodationUnitDTO(au);
				ausDTO.add(auDTO);
			}
		}
		return ausDTO;
	}
	
	public List<AccommodationUnitDTO> getFreeAccommodationUnits(LocalDate startDate, LocalDate endDate, Long aId) {
		List<AccommodationUnitDTO> units = this.getAllUnitsOfAccommodation(aId);
		List<AccommodationUnitDTO> freeUnits = new ArrayList<>();
		for(AccommodationUnitDTO unit : units) {
			boolean isReserved = false;
			ReservationsDTO reservationsDTO = null;
			try {
				reservationsDTO = restTemplate.getForObject("http://reservation-service/reservation/unit/" + unit.getId(), ReservationsDTO.class);
				for(ReservationDTO reservationDTO : reservationsDTO.getReservations()) {
					if(!(endDate.isBefore(reservationDTO.getFromDateTime().toLocalDate()) || startDate.isAfter(reservationDTO.getToDateTime().toLocalDate()))) {
						isReserved = true;
					}
				}
				if(!isReserved)
					freeUnits.add(unit);				
			} catch(HttpClientErrorException e) {
				freeUnits.add(unit);
			}
		}
		return freeUnits;
	}
}
