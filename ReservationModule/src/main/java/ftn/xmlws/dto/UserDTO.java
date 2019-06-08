package ftn.xmlws.dto;

import ftn.xmlws.enums.Role;
import ftn.xmlws.model.Address;
import ftn.xmlws.model.User;

public class UserDTO {
	
	private Long id;
	private String name;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private boolean enabled;
    private boolean deleted;
    private Role role;
    private long bussinesRegistrationNumber;
    private boolean blocked;
    private Address address;
    private AccommodationDTO accommodation;
    
    public UserDTO() {
    	
    }
    
    

    public UserDTO(User user) {
		super();
		this.name = user.getName();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
		this.deleted = user.isDeleted();
		this.role = user.getRole();
		this.bussinesRegistrationNumber = user.getBussinesRegistrationNumber();
		this.blocked = user.isBlocked();
		this.setAddress(user.getAddress());
		this.id = user.getId();
		
		if(this.role == Role.AGENT && user.getAgentOfAccommodation() != null) {
			this.accommodation = new AccommodationDTO(user.getAgentOfAccommodation());
		}
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public boolean isDeleted() {
		return deleted;
	}



	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}



	public long getBussinesRegistrationNumber() {
		return bussinesRegistrationNumber;
	}



	public void setBussinesRegistrationNumber(long bussinesRegistrationNumber) {
		this.bussinesRegistrationNumber = bussinesRegistrationNumber;
	}



	public boolean isBlocked() {
		return blocked;
	}



	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public AccommodationDTO getAccommodation() {
		return accommodation;
	}



	public void setAccommodation(AccommodationDTO accommodation) {
		this.accommodation = accommodation;
	}

    

}
