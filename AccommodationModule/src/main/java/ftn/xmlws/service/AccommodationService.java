package ftn.xmlws.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ftn.xmlws.dto.AccommodationCommentsDTO;
import ftn.xmlws.dto.AccommodationDTO;
import ftn.xmlws.dto.AccommodationSearchDTO;
import ftn.xmlws.dto.AccommodationUnitDTO;
import ftn.xmlws.dto.CommentDTO;
import ftn.xmlws.dto.ServiceDTO;
import ftn.xmlws.dto.UserDTO;
import ftn.xmlws.enums.Role;
import ftn.xmlws.model.Accommodation;
import ftn.xmlws.model.AccommodationUnit;
import ftn.xmlws.model.Address;
import ftn.xmlws.model.Image;
import ftn.xmlws.model.Reservation;
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
		if (list == null)
			return null;
		List<AccommodationDTO> listDTO = new ArrayList<>();
		for (Accommodation a : list) {
			if (!a.isDeleted())
				listDTO.add(new AccommodationDTO(a));
		}
		return listDTO;
	}

	public AccommodationDTO getAccommodationByUser(String username) {
		UserDTO userDTO = restTemplate.getForObject("http://user-service/users/" + username, UserDTO.class);
		System.out.println(userDTO.getUsername() + " -> " + userDTO.getAccommodation().getName());
		if (userDTO.getRole().equals(Role.AGENT)) {
			return userDTO.getAccommodation();
		}
		return null;
	}

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
		return freeAccommodations;
	}

	public Accommodation findAccommodation(Long id) {
		Accommodation a = null;
		try {
			a = accommodationRepository.getOne(id);
			if (a.isDeleted())
				return null;
		} catch (EntityNotFoundException e) {
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
		if (accommodation == null)
			return false;
		accommodation.setDeleted(true);
		accommodationRepository.save(accommodation);
		return true;
	}

	public Double getAvgRating(Long id) {
		Accommodation accommodation = this.findAccommodation(id);

		double sumOfRatings = 0;
		double numOfRatings = 0;
		List<AccommodationUnit> allUnits = accommodation.getAccommodationUnits();
		List<Reservation> allReservations = new ArrayList<Reservation>();
		
		for(AccommodationUnit au : allUnits) {
			allReservations.addAll(au.getReservations());
		}
		
		for(Reservation res : allReservations) {
			if(!res.isDeleted() && res.getCommentRate() != null) {
				sumOfRatings += res.getCommentRate().getOcena();
				numOfRatings++;
			}
		}
		
		return sumOfRatings/numOfRatings;
	}
	
	public AccommodationCommentsDTO getAllComments(Long id) {
		Accommodation accommodation = this.findAccommodation(id);

		List<AccommodationUnit> units = accommodation.getAccommodationUnits();
		System.out.println("Units "  + units.size());
		List<Reservation> reservations = new ArrayList<Reservation>();
		for(AccommodationUnit au : units)
			reservations.addAll(au.getReservations());
		
		System.out.println("Res "  + reservations.size());
		
		AccommodationCommentsDTO retVal = new AccommodationCommentsDTO();
		for(Reservation r : reservations) {
			if(r.getCommentRate()!=null) {
				if(r.getCommentRate().isApprovedComment() && r.getCommentRate().getContentOfComment() != null) {
					if(!(r.getCommentRate().getContentOfComment().equals(""))) {
						System.out.println("Usao");
						CommentDTO comment = new CommentDTO();
						comment.setContentOfComment(r.getCommentRate().getContentOfComment());
						comment.setReservationId(r.getId());
						comment.setApprovedComment(r.getCommentRate().isApprovedComment());
						comment.setRate(r.getCommentRate().getOcena());
						retVal.getComments().add(comment);
						retVal.getUserDTOs().add(new UserDTO(r.getGuest()));
					}
					
				}
			}
		}
		
		return retVal;
	}
	
	public List<Byte> getImages(Long id) throws IOException {
		Accommodation accommodation = this.findAccommodation(id);

		Path currentRelativePath = Paths.get("");
		
		String s = 	currentRelativePath.toAbsolutePath().toString();
		s+="/data/img/101_img1.jpg";
		Image image = new Image(s);
		
		return image.getImage();
	}
}
