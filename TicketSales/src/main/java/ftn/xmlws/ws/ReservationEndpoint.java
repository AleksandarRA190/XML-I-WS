package ftn.xmlws.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.projectxml.reservation.ConfirmReservationRequest;
import com.projectxml.reservation.ConfirmReservationResponse;
import com.projectxml.reservation.GetReservationsByUnitRequest;
import com.projectxml.reservation.GetReservationsByUnitResponse;
import com.projectxml.reservation.Reservation;

@Endpoint
public class ReservationEndpoint {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PayloadRoot(namespace = "http://www.projectXml.com/reservation", localPart = "getReservationByUnitRequest")
	@ResponsePayload
	public GetReservationsByUnitResponse processGetReservationsByUnitRequest(
			@RequestPayload GetReservationsByUnitRequest request) {
		GetReservationsByUnitResponse response = new GetReservationsByUnitResponse();
	  
		
	  	response.getReservation().add(new Reservation());
	  	
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/reservation", localPart = "confirmReservationRequest")
	@ResponsePayload
	public ConfirmReservationResponse processConfirmReservationRequest(
			@RequestPayload ConfirmReservationRequest request) {
		
		ConfirmReservationResponse response = new ConfirmReservationResponse();
		
		Long reservationId = request.getIdReservation();
		
		boolean success = restTemplate.getForObject("http://localhost:9008/reservation/confirm/"+reservationId, boolean.class);
		
		response.setSuccess(success);
		
	  	return response;
	 }

}
