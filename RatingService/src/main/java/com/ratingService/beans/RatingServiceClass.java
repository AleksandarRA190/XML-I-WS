package com.ratingService.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceClass {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	public Rating getRating(Long reservationId) {
		Rating retVal = this.ratingRepository.findByReservationId(reservationId);
		return retVal;
	}
	
	
	public void saveRating(int rate, Long reservationId) {
		Rating rating = new Rating();
		rating.setReservationId(reservationId);
		rating.setRate(rate);
		this.ratingRepository.save(rating);
	}
	
	public void updateRating(int rate, Long reservationId) {
		Rating rating = this.ratingRepository.findByReservationId(reservationId); 
		rating.setRate(rate);
		this.ratingRepository.save(rating);
	}

}
