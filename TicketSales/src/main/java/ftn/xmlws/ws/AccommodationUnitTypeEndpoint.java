package ftn.xmlws.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.projectxml.accommodationunittype.AccommodationUnitType;
import com.projectxml.accommodationunittype.AddAccommodationUnitTypeRequest;
import com.projectxml.accommodationunittype.AddAccommodationUnitTypeResponse;
import com.projectxml.accommodationunittype.GetAccommodationUnitTypeRequest;
import com.projectxml.accommodationunittype.GetAccommodationUnitTypeResponse;
import com.projectxml.accommodationunittype.GetAccommodationUnitTypesRequest;
import com.projectxml.accommodationunittype.GetAccommodationUnitTypesResponse;
import com.projectxml.accommodationunittype.RemoveAccommodationUnitTypeRequest;
import com.projectxml.accommodationunittype.UpdateAccommodationUnitTypeRequest;

import ftn.xmlws.dto.AccommodationUnitTypeDTO;
import ftn.xmlws.dto.AccommodationUnitTypesDTO;

@Endpoint
public class AccommodationUnitTypeEndpoint {

	@Autowired
	private RestTemplate restTemplate;
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnitType", localPart = "getAccommodationUnitTypesRequest")
	@ResponsePayload
	public GetAccommodationUnitTypesResponse processGetAccommodationUnitTypesRequest(@RequestPayload GetAccommodationUnitTypesRequest request) {
		GetAccommodationUnitTypesResponse response = new GetAccommodationUnitTypesResponse();
		
		AccommodationUnitTypesDTO auts = restTemplate.getForObject("http://localhost:9009/accommodationUnitType", AccommodationUnitTypesDTO.class);
		List<AccommodationUnitTypeDTO> list = auts.getAccommodationUnitTypes();
		
		response.getAccommodationUnitTypes().addAll(list);
	  	return response;
	 }
	

	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnitType", localPart = "getAccommodationUnitTypeRequest")
	@ResponsePayload
	public GetAccommodationUnitTypeResponse processGetAccommodationUnitTypeRequest(@RequestPayload GetAccommodationUnitTypeRequest request) {
		GetAccommodationUnitTypeResponse response = new GetAccommodationUnitTypeResponse();
	  
		AccommodationUnitType aut = restTemplate.getForObject("http://localhost:9009/accommodationUnitType/" + request.getAccommodationUnitTypeId(), AccommodationUnitType.class);
		response.setAccommodationUnitType(aut);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnitType", localPart = "addAccommodationUnitTypeRequest")
	@ResponsePayload
	public AddAccommodationUnitTypeResponse processAddAccommodationUnitTypeRequest(@RequestPayload AddAccommodationUnitTypeRequest request) {
		AddAccommodationUnitTypeResponse response = new AddAccommodationUnitTypeResponse();
		
		AccommodationUnitType accommodationUnitType = request.getAccommodationUnitType();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AccommodationUnitType> requestHeader = new HttpEntity<>(accommodationUnitType, headers);
		AccommodationUnitType aut = restTemplate.postForObject("http://localhost:9009/accommodationUnitType", requestHeader, AccommodationUnitType.class);
		response.setAccommodationUnitType(aut);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnitType", localPart = "updateAccommodationUnitTypeRequest")
	@ResponsePayload
	public void processUpdateAccommodationUnitTypeRequest(@RequestPayload UpdateAccommodationUnitTypeRequest request) {
		AccommodationUnitType accommodationUnitType = request.getAccommodationUnitType();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AccommodationUnitType> requestHeader = new HttpEntity<>(accommodationUnitType, headers);
		
		restTemplate.put("http://localhost:9009/accommodationUnitType/" + request.getAccommodationUnitType().getId(), requestHeader);
	 }
	
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnitType", localPart = "removeAccommodationUnitTypeRequest")
	@ResponsePayload
	public void processRemoveAccommodationUnitTypeRequest(@RequestPayload RemoveAccommodationUnitTypeRequest request) {
		restTemplate.delete("http://localhost:9009/accommodationUnitType/" + request.getAccommodationUnitTypeId());
	 }

}
