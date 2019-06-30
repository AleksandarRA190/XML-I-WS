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
import ftn.xmlws.dto.ServicesDTO;

@Endpoint
public class ServiceEndpoint {

	@Autowired
	private RestTemplate restTemplate;

	// ********************************* get, add, remove service from/to
	// accommodation *****************************************88
	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "getAccommodationServicesRequest")
	@ResponsePayload
	public GetAccommodationServicesResponse processGetAccommodationServicesRequest(
			@RequestPayload GetAccommodationServicesRequest request) {
		GetAccommodationServicesResponse response = new GetAccommodationServicesResponse();

		ServicesDTO ssDTO = restTemplate.getForObject(
				"http://accommodation-service/accommodation/" + request.getAccommodationId() + "/services", ServicesDTO.class);

		Services ss = new Services();
		for (ServiceDTO sDTO : ssDTO.getServices()) {
			Service s = new Service(sDTO);
			ss.getServices().add(s);
		}

		response.setServices(ss);
		return response;
	}

	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "addAccommodationServiceRequest")
	@ResponsePayload
	public AddAccommodationServiceResponse processAddAccommodationServiceRequest(
			@RequestPayload AddAccommodationServiceRequest request) {
		AddAccommodationServiceResponse response = new AddAccommodationServiceResponse();

		Service service = request.getService();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Service> requestHeader = new HttpEntity<>(service, headers);

		Service s = restTemplate.postForObject(
				"http://accommodation-service/accommodation/" + request.getAccommodationId() + "/services", requestHeader,
				Service.class);
		response.setService(s);

		return response;
	}

	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "removeAccommodationServiceRequest")
	@ResponsePayload
	public void processRemoveAccommodationServiceRequest(@RequestPayload RemoveAccommodationServiceRequest request) {
		restTemplate.delete("http://accommodation-service/accommodation/" + request.getAccommodationId() + "/services/"
				+ request.getAccommodationServiceId());
	}

	// ********************************** crud for services
	// **************************************************
	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "getServicesRequest")
	@ResponsePayload
	public GetServicesResponse processGetServicesRequest(@RequestPayload GetServicesRequest request) {
		GetServicesResponse response = new GetServicesResponse();

		ServicesDTO ssDTO = restTemplate.getForObject("http://accommodation-service/accommodation/services",
				ServicesDTO.class);

		Services ss = new Services();
		for (ServiceDTO sDTO : ssDTO.getServices()) {
			Service s = new Service(sDTO);
			ss.getServices().add(s);
		}

		response.setServices(ss);
		return response;
	}

	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "getServiceRequest")
	@ResponsePayload
	public GetServiceResponse processGetServiceRequest(@RequestPayload GetServiceRequest request) {
		GetServiceResponse response = new GetServiceResponse();

		Service s = restTemplate.getForObject("http://accommodation-service/accommodation/services/" + request.getServiceId(),
				Service.class);
		response.setService(s);

		return response;
	}

	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "addServiceRequest")
	@ResponsePayload
	public AddServiceResponse processAddServiceRequest(@RequestPayload AddServiceRequest request) {
		AddServiceResponse response = new AddServiceResponse();

		Service service = request.getService();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Service> requestHeader = new HttpEntity<>(service, headers);
		Service s = restTemplate.postForObject("http://accommodation-service/accommodation/services", requestHeader,
				Service.class);
		response.setService(s);

		return response;
	}

	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "updateServiceRequest")
	@ResponsePayload
	public void processUpdateServiceRequest(@RequestPayload UpdateServiceRequest request) {
		Service service = request.getService();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Service> requestHeader = new HttpEntity<>(service, headers);
		restTemplate.put("http://accommodation-service/accommodation/services/" + request.getService().getId(), requestHeader);
	}

	@PayloadRoot(namespace = "http://www.projectXml.com/service", localPart = "removeServiceRequest")
	@ResponsePayload
	public void processRemoveServiceRequest(@RequestPayload RemoveServiceRequest request) {
		restTemplate.delete("http://accommodation-service/accommodation/services/" + request.getServiceId());
	}
}
