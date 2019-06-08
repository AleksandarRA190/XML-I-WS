package ftn.xmlws.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.projectxml.accommodationunit.AccommodationUnit;
import com.projectxml.accommodationunit.AccommodationUnits;
import com.projectxml.accommodationunit.AddAccommodationUnitRequest;
import com.projectxml.accommodationunit.AddAccommodationUnitResponse;
import com.projectxml.accommodationunit.GetAccommodationUnitRequest;
import com.projectxml.accommodationunit.GetAccommodationUnitResponse;
import com.projectxml.accommodationunit.GetAccommodationUnitsRequest;
import com.projectxml.accommodationunit.GetAccommodationUnitsResponse;
import com.projectxml.accommodationunit.RemoveAccommodationUnitRequest;
import com.projectxml.accommodationunit.UpdateAccommodationUnitRequest;

@Endpoint
public class AccommodationUnitEndpoint {

	@Autowired
	private RestTemplate restTemplate;
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnit", localPart = "getAccommodationUnitsRequest")
	@ResponsePayload
	public GetAccommodationUnitsResponse processGetAccommodationUnitsRequest(@RequestPayload GetAccommodationUnitsRequest request) {
		GetAccommodationUnitsResponse response = new GetAccommodationUnitsResponse();
	  
		AccommodationUnits aus = restTemplate.getForObject("http://localhost:9008/accommodation/" + request.getId() + "/units", AccommodationUnits.class);
		response.setAccommodationUnits(aus);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnit", localPart = "getAccommodationUnitRequest")
	@ResponsePayload
	public GetAccommodationUnitResponse processGetAccommodationUnitRequest(@RequestPayload GetAccommodationUnitRequest request) {
		GetAccommodationUnitResponse response = new GetAccommodationUnitResponse();
	  
		AccommodationUnit au = restTemplate.getForObject("http://localhost:9008/accommodation/units/" + request.getAccommodationUnitId(), AccommodationUnit.class);
		response.setAccommodationUnit(au);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnit", localPart = "addAccommodationUnitRequest")
	@ResponsePayload
	public AddAccommodationUnitResponse processAddAccommodationUnitRequest(@RequestPayload AddAccommodationUnitRequest request) {
		AddAccommodationUnitResponse response = new AddAccommodationUnitResponse();
	  
		AccommodationUnit au = restTemplate.postForObject("http://localhost:9008/accommodation/" + request.getAccommodationId() + "/units", request, AccommodationUnit.class);
		response.setAccommodationUnit(au);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnit", localPart = "updateAccommodationUnitRequest")
	@ResponsePayload
	public void processUpdateAccommodationUnitRequest(@RequestPayload UpdateAccommodationUnitRequest request) {
		restTemplate.put("http://localhost:9008/accommodation/units/" + request.getAccommodationUnitId(), request);
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnit", localPart = "removeAccommodationUnitRequest")
	@ResponsePayload
	public void processRemoveAccommodationUnitRequest(@RequestPayload RemoveAccommodationUnitRequest request) {
		restTemplate.delete("http://localhost:9008/accommodation/units/" + request.getAccommodationUnitId());
	 }
}
