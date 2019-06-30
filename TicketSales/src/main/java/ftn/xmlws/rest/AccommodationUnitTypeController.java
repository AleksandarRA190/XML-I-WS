package ftn.xmlws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/accommodationUnitType")
public class AccommodationUnitTypeController {

	@Autowired
	private RestTemplate restTemplate;
}
