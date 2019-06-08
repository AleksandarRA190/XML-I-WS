package ftn.xmlws.ws;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.projectxml.address.AddAddressRequest;
import com.projectxml.address.AddAddressResponse;
import com.projectxml.address.Address;
import com.projectxml.address.GetAddressRequest;
import com.projectxml.address.GetAddressResponse;

@Endpoint
public class AddressEndpoint {
	
	
	@PayloadRoot(namespace = "http://www.projectXml.com/address", localPart = "getAddressRequest")
	@ResponsePayload
	public GetAddressResponse processGetAddressRequest(@RequestPayload GetAddressRequest request) {
		GetAddressResponse response = new GetAddressResponse();
	  
		Address address = new Address();
		address.setId(request.getId());
		address.setCountry("France");
		address.setCity("Paris");
		address.setApartmentNumber("45b");
		address.setLatitude(45.8);
	  	address.setLongitude(48.5);
	  	address.setNumber("22y");
	  	address.setPostalCode(41000);
	  	address.setStreet("Notr Dam burnsss");
	  
	  	response.setAddress(address);
	  	
	   
	  	return response;
	 }
	
	@PayloadRoot(namespace = "http://www.projectXml.com/address", localPart = "addAddressRequest")
	@ResponsePayload
	public AddAddressResponse processAddAddressRequest(@RequestPayload AddAddressRequest request) {
		AddAddressResponse response = new AddAddressResponse();
	  
		//add address to database via RESTful web services 
		response.setSuccess(true);
		
	  	return response;
	 }
}
