package com.ratingService.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ratingRest")
public class RatingController {
	
	@Autowired
	private RatingServiceClass ratingService;

	@RequestMapping(value = "/{reservationId}", method = RequestMethod.GET)
	public ResponseEntity<Rating> getRating(Long reservationId) {
		Rating rating = ratingService.getRating(reservationId);
		if (rating == null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(rating, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{rate}/{reservationId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> addRating(@PathVariable("rate") int rate, @PathVariable("reservationId") Long reservationId) {
		ratingService.saveRating(rate, reservationId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{rate}/{reservationId}", method = RequestMethod.POST)
	public ResponseEntity<Void> updateRating(@PathVariable("rate") int rate, @PathVariable("reservationId") Long reservationId) {
		ratingService.updateRating(rate, reservationId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
