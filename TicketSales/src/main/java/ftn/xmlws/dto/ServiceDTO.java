package ftn.xmlws.dto;

import ftn.xmlws.model.Service;

public class ServiceDTO {

	private String name;
	private String description;
	private Long id;
	
	public ServiceDTO() {
		
	}
	
	public ServiceDTO(Service s) {
		name = s.getName();
		description = s.getDescription();
		id = s.getId();
	}
	
	public ServiceDTO(com.projectxml.service.Service s) {
		name = s.getName();
		description = s.getDescription();
		id = s.getId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
