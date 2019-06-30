package ftn.xmlws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ftn.xmlws.dto.Rating;

@RestController
@RequestMapping(value = "/ratingRest")
public class RatingController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
	public ResponseEntity<Rating> getRating(Long reservationId) {

		Rating rating = new Rating();
		rating.setCommentId(56L);
		rating.setId(1L);
		rating.setRate(4);

		return new ResponseEntity<>(rating, HttpStatus.OK);
	}

	@RequestMapping(value = "/{rate}/{commentId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> addRating(@PathVariable("rate") int rate, @PathVariable("commentId") Long commentId) {
		// ratingService.saveRating(rate, commentId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/accommodationAverage/{accommodationId}", method = RequestMethod.GET)
	public ResponseEntity<Double> getAvgRating(@PathVariable("accommodationId") Long id) {
		Double avg = restTemplate.getForObject("http://accommodation-service/accommodation/getAvgRating/" + id,
				Double.class);

		return new ResponseEntity<>(avg, HttpStatus.OK);
	}

}
