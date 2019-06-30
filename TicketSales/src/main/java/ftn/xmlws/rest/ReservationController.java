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

import ftn.xmlws.dto.CommentDTO;
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
		
		ReservationDTO reservation = restTemplate.getForObject("http://reservation-service/reservation/"+id, ReservationDTO.class);
		
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
		
		restTemplate.put("http://reservation-service/reservation/add", request);
		
		return new ResponseEntity<>(HttpStatus.OK);		
	}


	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ReservationDTO> updateReservation(@RequestBody ReservationDTO reservation) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ReservationDTO> request = new HttpEntity<>(reservation,headers);
		
		ReservationDTO updatedReservation = restTemplate.postForObject("http://reservation-service/reservation/update",request,ReservationDTO.class);
		
		return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {

		String url = "http://reservation-service/reservation/"+id;
		restTemplate.delete(url);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET)
	public ResponseEntity<Void> confirmReservation(@PathVariable("id") Long id) {
		
		boolean success = restTemplate.getForObject("http://reservation-service/reservation/confirm/"+id, boolean.class);
		
		if (!success) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/agentConfirm/{id}", method = RequestMethod.GET)
	public ResponseEntity<Void> agentConfirmReservation(@PathVariable("id") Long id) {
		
		boolean success = restTemplate.getForObject("http://reservation-service/reservation/agentConfirm/"+id, boolean.class);
		
		if (!success) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value = "/addEditComment/{id}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addEditComment(@PathVariable("id") Long id,@RequestBody CommentDTO commentDTO) {
		boolean success = restTemplate.postForObject("http://reservation-service/reservation/addEditComment/"+id, commentDTO,boolean.class);
		if (success) {
			return new ResponseEntity<>(true,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/confirmComment/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> confirmComment(@PathVariable("id") Long id) {
		boolean success = restTemplate.getForObject("http://reservation-service/reservation/confirmComment/"+id,boolean.class);
		if (success) {
			return new ResponseEntity<>(true,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getComment/{id}", method = RequestMethod.GET)
	public ResponseEntity<CommentDTO> getComment(@PathVariable("id") Long id) {
		CommentDTO comment = restTemplate.getForObject("http://reservation-service/reservation/getComment/"+id,CommentDTO.class);
		
		if (comment != null) {
			return new ResponseEntity<>(comment,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/comment/{idReservation}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteComment(@PathVariable("idReservation") Long id) {
		
		restTemplate.delete("http://reservation-service/reservation/comment/"+id);

		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rateReservation", method = RequestMethod.POST)
	public ResponseEntity<ReservationDTO> rateReservation(@RequestBody CommentDTO commentRateDTO) {

		ReservationDTO res  = restTemplate.postForObject("http://reservation-service/reservation/"
				+ "rateReservation",commentRateDTO,ReservationDTO.class);

		if (res!=null) {
			return new ResponseEntity<>(res,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	

}
