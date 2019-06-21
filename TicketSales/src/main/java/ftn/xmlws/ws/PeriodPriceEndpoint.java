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

import com.projectxml.periodprice.AddPeriodPriceRequest;
import com.projectxml.periodprice.AddPeriodPriceResponse;
import com.projectxml.periodprice.GetPeriodPricesRequest;
import com.projectxml.periodprice.GetPeriodPricesResponse;
import com.projectxml.periodprice.PeriodPrice;
import com.projectxml.periodprice.PeriodPrices;
import com.projectxml.periodprice.RemovePeriodPriceRequest;
import com.projectxml.periodprice.UpdatePeriodPriceRequest;

import ftn.xmlws.dto.PeriodPriceDTO;
import ftn.xmlws.dto.PeriodPricesDTO;

@Endpoint
public class PeriodPriceEndpoint {

	@Autowired
	private RestTemplate restTemplate;
	
	@PayloadRoot(namespace = "http://www.projectXml.com/periodPrice", localPart = "getPeriodPricesRequest")
	@ResponsePayload
	public GetPeriodPricesResponse processGetPeriodPricesRequest(@RequestPayload GetPeriodPricesRequest request) {
		GetPeriodPricesResponse response = new GetPeriodPricesResponse();
	  
		PeriodPricesDTO ppDTO = new PeriodPricesDTO();
		ppDTO = restTemplate.getForObject("http://localhost:9009/accommodation/units/" + request.getAccommodationUnitId() + "/prices", PeriodPricesDTO.class);
		
		PeriodPrices pp = new PeriodPrices();
		for(PeriodPriceDTO pDTO: ppDTO.getPeriodPrices()) {
			PeriodPrice p = new PeriodPrice(pDTO);
			pp.getPeriodPrice().add(p);
		}
		
		response.setPeriodPrices(pp);
	  	return response;
		
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/periodPrice", localPart = "addPeriodPriceRequest")
	@ResponsePayload
	public AddPeriodPriceResponse processAddPeriodPriceRequest(@RequestPayload AddPeriodPriceRequest request) {
		AddPeriodPriceResponse response = new AddPeriodPriceResponse();
	  
		PeriodPrice periodPrice = request.getPeriodPrice();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PeriodPrice> requestHeader = new HttpEntity<>(periodPrice, headers);
		
		PeriodPrice pp = restTemplate.postForObject("http://localhost:9009/accommodation/units/" + request.getAccommodationUnitId() + "/prices", requestHeader, PeriodPrice.class);
		response.setPeriodPrice(pp);
		
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/periodPrice", localPart = "updatePeriodPriceRequest")
	@ResponsePayload
	public void processUpdatePeriodPriceRequest(@RequestPayload UpdatePeriodPriceRequest request) {
		PeriodPrice periodPrice = request.getPeriodPrice();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PeriodPrice> requestHeader = new HttpEntity<>(periodPrice, headers);
		restTemplate.put("http://localhost:9009/accommodation/units/prices/" + request.getPeriodPrice().getId(), requestHeader);
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/periodPrice", localPart = "removePeriodPriceRequest")
	@ResponsePayload
	public void processRemovePeriodPriceRequest(@RequestPayload RemovePeriodPriceRequest request) {
		restTemplate.delete("http://localhost:9009/accommodation/units/prices/" + request.getPeriodPriceId());
	}

}
