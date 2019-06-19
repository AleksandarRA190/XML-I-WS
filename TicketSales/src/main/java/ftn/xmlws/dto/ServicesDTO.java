package ftn.xmlws.dto;

import java.util.ArrayList;
import java.util.List;

public class ServicesDTO {

	private List<ServiceDTO> services;
	
	public ServicesDTO() {
		this.services = new ArrayList<>();
	}

	public List<ServiceDTO> getServices() {
		return services;
	}

	public void setServices(List<ServiceDTO> services) {
		this.services = services;
	}
}
