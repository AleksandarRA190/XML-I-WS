package ftn.xmlws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.xmlws.dto.ReservationDTO;
import ftn.xmlws.service.ReservationService;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ReservationDTO>> getReservations() {

		List<ReservationDTO> reservations = reservationService.getAllReservations();

		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ReservationDTO> getReservation(@PathVariable("id") Long id) {
		ReservationDTO reservation = reservationService.getReservation(id);
		if (reservation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(reservation, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> makeReservation(@RequestBody ReservationDTO reservation) {

		System.out.println("Mikroservis: " + reservation.getGuest().getUsername());
		boolean success = reservationService.makeReservation(reservation);
		
		if (!success) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.CREATED);	
		}	
	}


	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ReservationDTO> updateReservation(@RequestBody ReservationDTO reservation) {

		ReservationDTO updatedReservation = reservationService.updateReservation(reservation);

		return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {

		reservationService.removeReservation(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> confirmResevation(@PathVariable("id") Long id) {
		boolean success = reservationService.confirmReservation(id);
		if (success) {
			return new ResponseEntity<>(true,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
	}

}
