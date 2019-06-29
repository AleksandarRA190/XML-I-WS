package ftn.xmlws;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import ftn.xmlws.service.UploadService;

@SpringBootApplication
public class AccommodationModuleApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	

	public static void main(String[] args) {
		SpringApplication.run(AccommodationModuleApplication.class, args);
	}

}
