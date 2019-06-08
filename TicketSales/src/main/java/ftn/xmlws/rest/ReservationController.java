package ftn.xmlws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ftn.xmlws.dto.ReservationDTO;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/*@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ReservationDTO>> getReservations() {

		List<ReservationDTO> reservations = reservationService.getAllReservations();

		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ReservationDTO> getResevation(@PathVariable("id") Long id) {
		
		ReservationDTO reservation = restTemplate.getForObject("http://localhost:9008/reservation/"+id, ReservationDTO.class);
		
		if (reservation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(reservation, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> makeReservation(@RequestBody ReservationDTO reservation) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ReservationDTO> request = new HttpEntity<>(reservation,headers);
		
		restTemplate.put("http://localhost:9008/reservation/add", request);
		System.out.println("Glavni bekend: " + reservation.getGuest().getUsername());
		return new ResponseEntity<>(HttpStatus.OK);		
	}


	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ReservationDTO> updateReservation(@RequestBody ReservationDTO reservation) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ReservationDTO> request = new HttpEntity<>(reservation,headers);
		
		ReservationDTO updatedReservation = restTemplate.postForObject("http://localhost:9008/reservation/update",request,ReservationDTO.class);
		
		return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {

		String url = "http://localhost:9008/reservation/"+id;
		restTemplate.delete(url);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET)
	public ResponseEntity<Void> confirmResevation(@PathVariable("id") Long id) {
		
		boolean success = restTemplate.getForObject("http://localhost:9008/reservation/confirm/"+id, boolean.class);
		
		if (!success) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	

}