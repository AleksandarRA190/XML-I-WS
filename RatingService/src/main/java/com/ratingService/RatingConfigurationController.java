package com.ratingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingService.beans.RatingConfiguration;

@RestController
@RefreshScope
public class RatingConfigurationController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping(value="/ratings")
	public RatingConfiguration retrieveRatingsFromConfiguration()
	{
	return new RatingConfiguration(configuration.getMaximum(),configuration.getMinimum());
	}

}
