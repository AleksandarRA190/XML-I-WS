package ftn.xmlws.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.projectxml.accommodation.Accommodation;
import com.projectxml.accommodation.Accommodations;
import com.projectxml.accommodation.AddAccommodationRequest;
import com.projectxml.accommodation.AddAccommodationResponse;
import com.projectxml.accommodation.GetAccommodationByUserRequest;
import com.projectxml.accommodation.GetAccommodationByUserResponse;
import com.projectxml.accommodation.GetAccommodationRequest;
import com.projectxml.accommodation.GetAccommodationResponse;
import com.projectxml.accommodation.GetAccommodationsRequest;
import com.projectxml.accommodation.GetAccommodationsResponse;
import com.projectxml.accommodation.RemoveAccommodationRequest;
import com.projectxml.accommodation.UpdateAccommodationRequest;

import ftn.xmlws.dto.AccommodationDTO;
import ftn.xmlws.dto.AccommodationsDTO;

@Endpoint
public class AccommodationEndpoint {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodation", localPart = "getAccommodationsRequest")
	@ResponsePayload
	public GetAccommodationsResponse processGetAccommodationsRequest(@RequestPayload GetAccommodationsRequest request) {
		GetAccommodationsResponse response = new GetAccommodationsResponse();
		
		AccommodationsDTO asDTO = restTemplate.getForObject("http://localhost:9009/accommodation", AccommodationsDTO.class);
		
		Accommodations as = new Accommodations();
		for(AccommodationDTO aDTO: asDTO.getAccommodations()) {
			Accommodation a = new Accommodation(aDTO);
			as.getAccommodations().add(a);
		}
		
		response.setAccommodations(as);
	  	return response;
	 }
	
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodation", localPart = "getAccommodationRequest")
	@ResponsePayload
	public GetAccommodationResponse processGetAccommodationRequest(@RequestPayload GetAccommodationRequest request) {
		GetAccommodationResponse response = new GetAccommodationResponse();
	  
		Accommodation a = restTemplate.getForObject("http://localhost:9009/accommodation/" + request.getId(), Accommodation.class);
		response.setAccommodation(a);
		
	  	return response;
	 }
	
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodation", localPart = "getAccommodationByUserRequest")
	@ResponsePayload
	public GetAccommodationByUserResponse processGetAccommodationByUserRequest(@RequestPayload GetAccommodationByUserRequest request) {
		GetAccommodationByUserResponse response = new GetAccommodationByUserResponse();
	  
		Accommodation a = restTemplate.getForObject("http://localhost:9009/accommodation/byUser/" + request.getUsername(), Accommodation.class);
		response.setAccommodation(a);
		
	  	return response;
	 }
	
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodation", localPart = "addAccommodationRequest")
	@ResponsePayload
	public AddAccommodationResponse processAddAccommodationRequest(@RequestPayload AddAccommodationRequest request) {
		AddAccommodationResponse response = new AddAccommodationResponse();
		
		Accommodation accommodation = request.getAccommodation();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Accommodation> requestHeader = new HttpEntity<>(accommodation, headers);
		Accommodation a = restTemplate.postForObject("http://localhost:9009/accommodation", requestHeader, Accommodation.class);
		response.setAccommodation(a);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodation", localPart = "updateAccommodationRequest")
	@ResponsePayload
	public void processUpdateAccommodationRequest(@RequestPayload UpdateAccommodationRequest request) {
		Accommodation accommodation = request.getAccommodation();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Accommodation> requestHeader = new HttpEntity<>(accommodation, headers);
		
		restTemplate.put("http://localhost:9009/accommodation/" + request.getAccommodation().getId(), requestHeader);
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodation", localPart = "removeAccommodationRequest")
	@ResponsePayload
	public void processRemoveAccommodationRequest(@RequestPayload RemoveAccommodationRequest request) {
		restTemplate.delete("http://localhost:9009/accommodation/" + request.getId());
	 }
}
