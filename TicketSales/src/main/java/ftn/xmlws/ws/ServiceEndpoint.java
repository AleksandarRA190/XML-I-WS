package ftn.xmlws.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.projectxml.service.AddAccommodationServiceRequest;
import com.projectxml.service.AddAccommodationServiceResponse;
import com.projectxml.service.AddServiceRequest;
import com.projectxml.service.AddServiceResponse;
import com.projectxml.service.GetAccommodationServicesRequest;
import com.projectxml.service.GetAccommodationServicesResponse;
import com.projectxml.service.GetServiceRequest;
import com.projectxml.service.GetServiceResponse;
import com.projectxml.service.GetServicesRequest;
import com.projectxml.service.GetServicesResponse;
import com.projectxml.service.RemoveAccommodationServiceRequest;
import com.projectxml.service.RemoveServiceRequest;
import com.projectxml.service.Service;
import com.projectxml.service.Services;
import com.projectxml.service.UpdateServiceRequest;

import ftn.xmlws.dto.ServiceDTO;

@Endpoint
public class ServiceEndpoint {

	@Autowired
	private RestTemplate restTemplate;
	
	
	//********************************* get, add, remove service from/to accommodation *****************************************88
	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "getAccommodationServicesRequest")
	@ResponsePayload
	public GetAccommodationServicesResponse processGetAccommodationServicesRequest(@RequestPayload GetAccommodationServicesRequest request) {
		GetAccommodationServicesResponse response = new GetAccommodationServicesResponse();
	  
		Services s = restTemplate.getForObject("http://localhost:9009/accommodation/" + request.getAccommodationId() + "/services", Services.class);
		response.setServices(s);
	
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "addAccommodationServiceRequest")
	@ResponsePayload
	public AddAccommodationServiceResponse processAddAccommodationServiceRequest(@RequestPayload AddAccommodationServiceRequest request) {
		AddAccommodationServiceResponse response = new AddAccommodationServiceResponse();
	  
		Service s = restTemplate.postForObject("http://localhost:9009/accommodation/" + request.getAccommodationId() + "/services", request, Service.class);
		response.setService(s);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "removeAccommodationServiceRequest")
	@ResponsePayload
	public void processRemoveAccommodationServiceRequest(@RequestPayload RemoveAccommodationServiceRequest request) {
		restTemplate.delete("http://localhost:9009/accommodation/" + request.getAccommodationId() + "/services/" + request.getAccommodationServiceId());
	 }
	
	// ********************************** crud for services **************************************************
	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "getServicesRequest")
	@ResponsePayload
	public GetServicesResponse processGetServicesRequest(@RequestPayload GetServicesRequest request) {
		GetServicesResponse response = new GetServicesResponse();
	  
		Services s = restTemplate.getForObject("http://localhost:9009/services" , Services.class);
		response.setServices(s);
	
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "getServiceRequest")
	@ResponsePayload
	public GetServiceResponse processGetServiceRequest(@RequestPayload GetServiceRequest request) {
		GetServiceResponse response = new GetServiceResponse();
	  
		Service s = restTemplate.getForObject("http://localhost:9009/services/" + request.getServiceId(), Service.class);
		response.setService(s);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "addServiceRequest")
	@ResponsePayload
	public AddServiceResponse processAddServiceRequest(@RequestPayload AddServiceRequest request) {
		AddServiceResponse response = new AddServiceResponse();
		
		ServiceDTO sDTO = new ServiceDTO(request.getService());
	  
		Service s = restTemplate.postForObject("http://localhost:9009/services", sDTO, Service.class);
		response.setService(s);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "updateServiceRequest")
	@ResponsePayload
	public void processUpdateServiceRequest(@RequestPayload UpdateServiceRequest request) {
		ServiceDTO sDTO = new ServiceDTO(request.getService());
		restTemplate.put("http://localhost:9009/services/" + request.getService().getId(), sDTO);
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "removeServiceRequest")
	@ResponsePayload
	public void processRemoveServiceRequest(@RequestPayload RemoveServiceRequest request) {
		restTemplate.delete("http://localhost:9009/services/" + request.getServiceId());
	 }
}
