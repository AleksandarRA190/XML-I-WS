//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.4.0-b180830.0438 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.24 at 04:48:00 PM CEST 
//


package ftn.xmlws.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ftn.xmlws.dto.UserDTO;
import ftn.xmlws.enums.Role;


/**
 * <p>Java class for User complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="User"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Lastname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="Enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Role" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element ref="{http://booking.uns.ac.rs/users}Address"/&gt;
 *         &lt;element name="Bussines_registration_number" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="Blocked" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {
    "name",
    "lastname",
    "email",
    "username",
    "password",
    "id",
    "enabled",
    "deleted",
    "role",
    "address",
    "bussinesRegistrationNumber",
    "blocked"
})
@Entity
public class User {
	
    @XmlElement(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @XmlElement(name = "Name", required = true)
    protected String name;
    
    @XmlElement(name = "Lastname", required = true)
    protected String lastname;
    
    @XmlElement(name = "Email", required = true)
    protected String email;
    
    @XmlElement(name = "Username", required = true)
    protected String username;
    
    @XmlElement(name = "Password", required = true)
    protected String password;
    
    @XmlElement(name = "Enabled")
    protected boolean enabled;
    
    @XmlElement(name = "Deleted")
    protected boolean deleted;
    
    @XmlElement(name = "Role", required = true)
    protected Role role;
    
    @XmlElement(name = "Address", required = true)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName="id", nullable = false, unique = true)	
    protected Address address;
    
    @XmlElement(name = "Bussines_registration_number")
    protected long bussinesRegistrationNumber;
    
    @XmlElement(name = "Blocked")
    protected boolean blocked;
    
    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @XmlElement(name = "Reservation", required = true)
    protected List<Reservation> reservations = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Accommodation agentOfAccommodation;
    
    public User() {
    	
    }
    
    

    public User(String name, String lastname, String email, String username, String password, boolean enabled,
			boolean deleted, Role role, long bussinesRegistrationNumber, boolean blocked) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.deleted = deleted;
		this.role = role;
		this.bussinesRegistrationNumber = bussinesRegistrationNumber;
		this.blocked = blocked;
	}
    
    public User(UserDTO dto) {
    	super();
    	this.lastname = dto.getLastname();
    	this.name = dto.getName();
    	this.email = dto.getEmail();
    	this.username = dto.getUsername();
    	this.password = dto.getPassword();
    	this.enabled = dto.isEnabled();
    	this.deleted = dto.isDeleted();
    	this.role = dto.getRole();
    	this.bussinesRegistrationNumber = dto.getBussinesRegistrationNumber();
    	this.blocked = dto.isBlocked();
    }



	/**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the lastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the value of the lastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastname(String value) {
        this.lastname = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the enabled property.
     * 
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     */
    public void setEnabled(boolean value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the deleted property.
     * 
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     */
    public void setDeleted(boolean value) {
        this.deleted = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(Role value) {
        this.role = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the bussinesRegistrationNumber property.
     * 
     */
    public long getBussinesRegistrationNumber() {
        return bussinesRegistrationNumber;
    }

    /**
     * Sets the value of the bussinesRegistrationNumber property.
     * 
     */
    public void setBussinesRegistrationNumber(long value) {
        this.bussinesRegistrationNumber = value;
    }

    /**
     * Gets the value of the blocked property.
     * 
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * Sets the value of the blocked property.
     * 
     */
    public void setBlocked(boolean value) {
        this.blocked = value;
    }



	public List<Reservation> getReservations() {
		return reservations;
	}



	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}



	public Accommodation getAgentOfAccommodation() {
		return agentOfAccommodation;
	}



	public void setAgentOfAccommodation(Accommodation accommodation) {
		this.agentOfAccommodation = accommodation;
	}

}
