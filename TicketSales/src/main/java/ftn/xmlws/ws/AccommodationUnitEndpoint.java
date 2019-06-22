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

import ftn.xmlws.dto.AccommodationUnitDTO;
import ftn.xmlws.dto.AccommodationUnitsDTO;

@Endpoint
public class AccommodationUnitEndpoint {

	@Autowired
	private RestTemplate restTemplate;
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnit", localPart = "getAccommodationUnitsRequest")
	@ResponsePayload
	public GetAccommodationUnitsResponse processGetAccommodationUnitsRequest(@RequestPayload GetAccommodationUnitsRequest request) {
		GetAccommodationUnitsResponse response = new GetAccommodationUnitsResponse();
	  
		AccommodationUnitsDTO ausDTO = restTemplate.getForObject("http://localhost:9009/accommodation/" + request.getId() + "/units", AccommodationUnitsDTO.class);
		
		AccommodationUnits aus = new AccommodationUnits();
		for(AccommodationUnitDTO auDTO: ausDTO.getAccommodationUnits()) {
			AccommodationUnit au = new AccommodationUnit(auDTO);
			aus.getAccommodationUnits().add(au);
		}
		response.setAccommodationUnits(aus);
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnit", localPart = "getAccommodationUnitRequest")
	@ResponsePayload
	public GetAccommodationUnitResponse processGetAccommodationUnitRequest(@RequestPayload GetAccommodationUnitRequest request) {
		GetAccommodationUnitResponse response = new GetAccommodationUnitResponse();
	  
		AccommodationUnit au = restTemplate.getForObject("http://localhost:9009/accommodation/units/" + request.getAccommodationUnitId(), AccommodationUnit.class);
		response.setAccommodationUnit(au);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnit", localPart = "addAccommodationUnitRequest")
	@ResponsePayload
	public AddAccommodationUnitResponse processAddAccommodationUnitRequest(@RequestPayload AddAccommodationUnitRequest request) {
		AddAccommodationUnitResponse response = new AddAccommodationUnitResponse();
		
		AccommodationUnit unit = request.getAccommodationUnit();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AccommodationUnit> requestHeader = new HttpEntity<>(unit, headers);
		AccommodationUnit au = restTemplate.postForObject("http://localhost:9009/accommodation/"+request.getAccommodationId()+"/units", requestHeader, AccommodationUnit.class);
		response.setAccommodationUnit(au);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnit", localPart = "updateAccommodationUnitRequest")
	@ResponsePayload
	public void processUpdateAccommodationUnitRequest(@RequestPayload UpdateAccommodationUnitRequest request) {
		AccommodationUnit unit = request.getAccommodationUnit();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AccommodationUnit> requestHeader = new HttpEntity<>(unit, headers);
		restTemplate.put("http://localhost:9009/accommodation/units/" + request.getAccommodationUnit().getId(), requestHeader);
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnit", localPart = "removeAccommodationUnitRequest")
	@ResponsePayload
	public void processRemoveAccommodationUnitRequest(@RequestPayload RemoveAccommodationUnitRequest request) {
		restTemplate.delete("http://localhost:9009/accommodation/units/" + request.getAccommodationUnitId());
	 }
}
