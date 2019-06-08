package ftn.xmlws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.dto.ReservationDTO;
import ftn.xmlws.miscellaneous.MyTypeConverter;
import ftn.xmlws.model.Reservation;
import ftn.xmlws.repository.ReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public List<ReservationDTO> getAllReservations() {
		List<Reservation> list = new ArrayList<Reservation>();
		reservationRepository.findAll().forEach(list::add);
		
		List<ReservationDTO> listDto = new ArrayList<>();
		for(Reservation r : list) {
			if(!r.isDeleted())
				listDto.add(new ReservationDTO(r));
		}
		
		return listDto;
	}

	public ReservationDTO getReservation(Long id) {
		Reservation r = reservationRepository.getOne(id);
		
		if(r!=null) {
			System.out.println("Nije null nego je confirmed : " + r.isConfirmed());
			return new ReservationDTO(r);
		}
		
		System.out.println("Vratio je null");
		return null;
	}

	public boolean makeReservation(ReservationDTO reservation) {
		System.out.println("ReservationServisce: " + reservation.getGuest().getUsername());
		Reservation r = new Reservation();
		//r.setAccommodationUnit(); ovde poziv mikroservisu za get acc
		//r.setGuest(restTemplate.getForObject("http://localhost:9006/users/user/"+reservation.getGuest().getUsername(), ftn.xmlws.model.User.class));// ovde poziv mikroservisu za uzer
		r.setConfirmed(false);
		r.setDeleted(false);
		r.setFromDate(MyTypeConverter.localDateToXMLGregorianCalendar(reservation.getFromDate()));
		r.setToDate(MyTypeConverter.localDateToXMLGregorianCalendar(reservation.getToDate()));
		
		this.reservationRepository.save(r);
		return true;
	}

	public boolean removeReservation(Long id) {
		Reservation r = this.reservationRepository.getOne(id);
		
		if(r == null) {
			return false;
		}
		
		r.setDeleted(true);
		this.reservationRepository.save(r);
		return true;
	}

	public ReservationDTO updateReservation(ReservationDTO reservation) {
		Reservation r = this.reservationRepository.getOne(reservation.getId());

		if(r != null)
			if(!r.isDeleted()) {
				//ne moze se menjati korisnik ili soba jer to onda nije ta rezervacija
				r.setFromDate(MyTypeConverter.localDateToXMLGregorianCalendar(reservation.getFromDate()));
				r.setToDate(MyTypeConverter.localDateToXMLGregorianCalendar(reservation.getToDate()));
				this.reservationRepository.save(r);
				return reservation;
			}
		
		return null;
	}
	
	public boolean confirmReservation(Long id) {
		Reservation reservation = this.reservationRepository.getOne(id);

		if(reservation != null)
			if(!reservation.isDeleted()) {
				reservation.setConfirmed(true);
				this.reservationRepository.save(reservation);
				return true;
			}
		
		return false;
	}

}
