package ftn.xmlws.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.projectxml.accommodation.Accommodation;
import com.projectxml.accommodation.AddAccommodationRequest;
import com.projectxml.accommodation.AddAccommodationResponse;
import com.projectxml.accommodation.GetAccommodationRequest;
import com.projectxml.accommodation.GetAccommodationResponse;
import com.projectxml.accommodation.RemoveAccommodationRequest;
import com.projectxml.accommodation.UpdateAccommodationRequest;

@Endpoint
public class AccommodationEndpoint {

	@Autowired
	private RestTemplate restTemplate;
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodation", localPart = "getAccommodationRequest")
	@ResponsePayload
	public GetAccommodationResponse processGetAccommodationRequest(@RequestPayload GetAccommodationRequest request) {
		GetAccommodationResponse response = new GetAccommodationResponse();
	  
		Accommodation a = restTemplate.getForObject("http://localhost:9008/accommodation/" + request.getId(), Accommodation.class);
		response.setAccommodation(a);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodation", localPart = "addAccommodationRequest")
	@ResponsePayload
	public AddAccommodationResponse processAddAccommodationRequest(@RequestPayload AddAccommodationRequest request) {
		AddAccommodationResponse response = new AddAccommodationResponse();
	  
		Accommodation a = restTemplate.postForObject("http://localhost:9008/accommodation", request, Accommodation.class);
		response.setAccommodation(a);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodation", localPart = "updateAccommodationRequest")
	@ResponsePayload
	public void processUpdateAccommodationRequest(@RequestPayload UpdateAccommodationRequest request) {
		restTemplate.put("http://localhost:9008/accommodation/" + request.getId(), request);
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodation", localPart = "removeAccommodationRequest")
	@ResponsePayload
	public void processRemoveAccommodationRequest(@RequestPayload RemoveAccommodationRequest request) {
		restTemplate.delete("http://localhost:9008/accommodation/" + request.getId());
	 }
}
