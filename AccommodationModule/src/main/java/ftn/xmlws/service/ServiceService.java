package ftn.xmlws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.dto.ServiceDTO;
import ftn.xmlws.model.Accommodation;
import ftn.xmlws.repository.AccommodationRepository;
import ftn.xmlws.repository.ServiceRepository;

@Service
public class ServiceService {

	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired 
	private AccommodationRepository accommodationRepository;
	
	@Autowired 
	private AccommodationService accommodationService;
	
	public List<ServiceDTO> getAllServices() {
		List<ftn.xmlws.model.Service> list = new ArrayList<>();
		if(list == null)
			return null;
		List<ServiceDTO> listDTO = new ArrayList<>();
		list = serviceRepository.findAll();
		for(ftn.xmlws.model.Service s: list) {
			if(!s.isDeleted()) 
				listDTO.add(new ServiceDTO(s));
		}
		return listDTO;
	}
	
	public ftn.xmlws.model.Service findService(Long id) {
		ftn.xmlws.model.Service s = serviceRepository.getOne(id);
		if(s == null || s.isDeleted())
			return null;
		return s;
	}
	
	public ftn.xmlws.model.Service saveService(ServiceDTO sDTO) {
		ftn.xmlws.model.Service s = new ftn.xmlws.model.Service();
		if(sDTO == null)
			return null;
		s.setName(sDTO.getName());
		s.setDescription(sDTO.getDescription());
		return serviceRepository.save(s);
	}
	
	public ftn.xmlws.model.Service updateService(ServiceDTO sDTO, Long sId) {
		ftn.xmlws.model.Service s = this.findService(sId);
		if(s == null)
			return null;
		s.setDescription(sDTO.getDescription());
		s.setName(sDTO.getName());
		
		return serviceRepository.save(s);
	}
	
	public boolean removeService(Long sId) {
		ftn.xmlws.model.Service service = this.findService(sId);
		if(service == null)
			return false;
		service.setDeleted(true);
		serviceRepository.save(service);
		return true;
	}
	
	public List<ServiceDTO> getAccommodationServices(Long aId) {
		Accommodation a = accommodationService.findAccommodation(aId);
		if(a == null)
			return null;
		List<ServiceDTO> servicesDTO = new ArrayList<>();
		a.getServices().forEach(item -> {
			servicesDTO.add(new ServiceDTO(item));
		});
		return servicesDTO;
	}
	
	public Accommodation addServiceToAccommodation(Long aId, ServiceDTO sDTO) {
		Accommodation a = accommodationService.findAccommodation(aId);
		if(a == null)
			return null;
		ftn.xmlws.model.Service s = this.findService(sDTO.getId());
		if(s == null)
			return null;
		a.getServices().add(s);
		return accommodationRepository.save(a);
	}
	
	public boolean removeServiceFromAccommodation(Long aId, Long sId) {
		Accommodation a = accommodationService.findAccommodation(aId);
		if(a == null)
			return false;
		List<ftn.xmlws.model.Service> services = a.getServices();
		Predicate<ftn.xmlws.model.Service> predicate = p -> p.getId() == sId;
		services.removeIf(predicate);
		accommodationRepository.save(a);
		return true;
	}
}
