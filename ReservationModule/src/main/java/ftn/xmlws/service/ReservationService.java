package ftn.xmlws.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.dto.CommentDTO;
import ftn.xmlws.dto.ReservationDTO;
import ftn.xmlws.dto.UserReservationsDTO;
import ftn.xmlws.model.CommentRate;
import ftn.xmlws.model.Reservation;
import ftn.xmlws.repository.AccommodationUnitRepository;
import ftn.xmlws.repository.ReservationRepository;
import ftn.xmlws.repository.UserRepository;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccommodationUnitRepository accommodationUnitRepository;
	
	
	
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
			ReservationDTO res = new ReservationDTO(r);
			return res;
		} else {
			return null;		
		}
		
	}

	public boolean makeReservation(ReservationDTO reservation) {
		System.out.println("ReservationServisce: " + reservation.getGuest().getUsername());
		Reservation r = new Reservation();
		r.setAccommodationUnit(this.accommodationUnitRepository.getOne(reservation.getAccommodationUnit().getId()));
		r.setGuest(this.userRepository.findByUsername(reservation.getGuest().getUsername()));
		//r.setAccommodationUnit(); ovde poziv mikroservisu za get acc
		//r.setGuest(restTemplate.getForObject("http://localhost:9006/users/user/"+reservation.getGuest().getUsername(), ftn.xmlws.model.User.class));// ovde poziv mikroservisu za uzer
		r.setConfirmed(false);
		r.setDeleted(false);
		r.setFromDateTime(reservation.getFromDateTime());
		r.setToDateTime(reservation.getToDateTime());
		
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
				r.setFromDateTime(reservation.getFromDateTime());
				r.setToDateTime(reservation.getToDateTime());
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
	
	public boolean agentConfirmReservation(Long id) {
		Reservation reservation = this.reservationRepository.getOne(id);

		if(reservation != null)
			if(!reservation.isDeleted()) {
				reservation.setAgentConfirmed(true);
				this.reservationRepository.save(reservation);
				return true;
			}
		
		return false;
	}
	
	public boolean addEditComment(Long id, String content) {
		Reservation reservation = this.reservationRepository.getOne(id);
		
		if(reservation == null)
			return false;
		
		if(reservation.getCommentRate() == null) {
			CommentRate newComment = new CommentRate();
			newComment.setApprovedComment(false);
			newComment.setCommentDateTime(LocalDateTime.now());
			newComment.setContentOfComment(content);
			reservation.setCommentRate(newComment);
			this.reservationRepository.save(reservation);
		} else {
			CommentRate existingComment = reservation.getCommentRate();
			existingComment.setApprovedComment(false);
			existingComment.setCommentDateTime(LocalDateTime.now());
			existingComment.setContentOfComment(content);
			reservation.setCommentRate(existingComment);
			this.reservationRepository.save(reservation);
		}
		
		return true;
	}
	
	public ReservationDTO rateReservation(Long reservationId, int rate) {
		Reservation reservation = this.reservationRepository.getOne(reservationId);
		
		if(reservation == null)
			return null;
		
		if(reservation.getCommentRate() == null && reservation.isAgentConfirmed()) {
			
			CommentRate newComment = new CommentRate();
			newComment.setOcena(rate);
			reservation.setCommentRate(newComment);
			this.reservationRepository.save(reservation);
		} else {
			if(reservation.isAgentConfirmed()) {
				CommentRate existingComment = reservation.getCommentRate();
				existingComment.setOcena(rate);
				reservation.setCommentRate(existingComment);
				this.reservationRepository.save(reservation);	
			}
			
		}
		
		return new ReservationDTO(reservation);
	}
	
	public CommentDTO getComment(Long reserVationId) {
		Reservation reservation = this.reservationRepository.getOne(reserVationId);
		
		if(reservation == null)
			return null;
		
		if(reservation.getCommentRate() == null) {
			return null;
		} else {
			CommentRate comment = reservation.getCommentRate();
			CommentDTO commentDTO = new CommentDTO();
			commentDTO.setContentOfComment(comment.getContentOfComment());
			commentDTO.setApprovedComment(comment.isApprovedComment());
			commentDTO.setRate(comment.getOcena());
			commentDTO.setReservationId(reserVationId);
			return commentDTO;
		}
		
	}
	
	public boolean deleteComment(Long reserVationId) {
		Reservation reservation = this.reservationRepository.getOne(reserVationId);
		
		if(reservation == null)
			return false;
		
		if(reservation.getCommentRate() == null) {
			return false;
		} else {
			reservation.setCommentRate(null);
			this.reservationRepository.save(reservation);
			return true;
		}
		
	}
	
	public boolean confirmComment(Long reserVationId) {
		Reservation reservation = this.reservationRepository.getOne(reserVationId);
		
		if(reservation == null)
			return false;
		
		if(reservation.getCommentRate() == null) {
			return false;
		} else {
			CommentRate comment = reservation.getCommentRate();
			comment.setApprovedComment(true);
			reservation.setCommentRate(comment);
			this.reservationRepository.save(reservation);
			return true;
		}
		
	}

}
