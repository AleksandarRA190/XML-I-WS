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

import com.projectxml.accommodationunittype.AccommodationUnitType;
import com.projectxml.accommodationunittype.AccommodationUnitTypes;
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
		
		AccommodationUnitTypesDTO autsDTO = restTemplate.getForObject("http://accommodation-service/accommodationUnitType", AccommodationUnitTypesDTO.class);
		
		AccommodationUnitTypes auts = new AccommodationUnitTypes();
		for(AccommodationUnitTypeDTO autDTO: autsDTO.getAccommodationUnitTypes()) {
			AccommodationUnitType aut = new AccommodationUnitType(autDTO);
			auts.getAccommodationUnitTypes().add(aut);
		}
		
		response.setAccommodationUnitTypes(auts);
	  	return response;
	 }
	

	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnitType", localPart = "getAccommodationUnitTypeRequest")
	@ResponsePayload
	public GetAccommodationUnitTypeResponse processGetAccommodationUnitTypeRequest(@RequestPayload GetAccommodationUnitTypeRequest request) {
		GetAccommodationUnitTypeResponse response = new GetAccommodationUnitTypeResponse();
	  
		AccommodationUnitType aut = restTemplate.getForObject("http://accommodation-service/accommodationUnitType/" + request.getAccommodationUnitTypeId(), AccommodationUnitType.class);
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
		AccommodationUnitType aut = restTemplate.postForObject("http://accommodation-service/accommodationUnitType", requestHeader, AccommodationUnitType.class);
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
		
		restTemplate.put("http://accommodation-service/accommodationUnitType/" + request.getAccommodationUnitType().getId(), requestHeader);
	 }
	
	
	@PayloadRoot(namespace = "http://www.projectXml.com/accommodationUnitType", localPart = "removeAccommodationUnitTypeRequest")
	@ResponsePayload
	public void processRemoveAccommodationUnitTypeRequest(@RequestPayload RemoveAccommodationUnitTypeRequest request) {
		restTemplate.delete("http://accommodation-service/accommodationUnitType/" + request.getAccommodationUnitTypeId());
	 }

}
