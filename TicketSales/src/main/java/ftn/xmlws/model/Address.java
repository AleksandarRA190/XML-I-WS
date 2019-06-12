//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.07 at 07:55:21 PM CEST 
//


package ftn.xmlws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Postal_code" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Street" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Apartment_number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Longitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Latitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "country",
    "city",
    "postalCode",
    "street",
    "number",
    "apartmentNumber",
    "longitude",
    "latitude",
    "id",
    "deleted"
})
@Entity
@XmlRootElement(name = "Address", namespace = "http://booking.uns.ac.rs/users")
public class Address {

    @XmlElement(name = "Country", namespace = "http://booking.uns.ac.rs/users", required = true)
    protected String country;
    @XmlElement(name = "City", namespace = "http://booking.uns.ac.rs/users", required = true)
    protected String city;
    @XmlElement(name = "Postal_code", namespace = "http://booking.uns.ac.rs/users")
    protected int postalCode;
    @XmlElement(name = "Street", namespace = "http://booking.uns.ac.rs/users", required = true)
    protected String street;
    @XmlElement(name = "Number", namespace = "http://booking.uns.ac.rs/users", required = true)
    protected String number;
    @XmlElement(name = "Apartment_number", namespace = "http://booking.uns.ac.rs/users")
    protected String apartmentNumber;
    @XmlElement(name = "Longitude", namespace = "http://booking.uns.ac.rs/users")
    protected double longitude;
    @XmlElement(name = "Latitude", namespace = "http://booking.uns.ac.rs/users")
    protected double latitude;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement(name = "Id", namespace = "http://booking.uns.ac.rs/users")
    protected Long id;
    
    @XmlElement(name = "Deleted", namespace = "http://booking.uns.ac.rs/users")
    protected boolean deleted;
    
    public Address() {}
    
    public Address(Address a) {
		this.country = a.getCountry();
		this.city = a.getCity();
		this.postalCode = a.getPostalCode();
		this.street = a.getStreet();
		this.number = a.getNumber();
		this.apartmentNumber = a.getApartmentNumber();
		this.longitude = a.getLongitude();
		this.latitude = a.getLatitude();
		this.deleted = false;
    }
    
    public Address(com.projectxml.address.Address a) {
		this.country = a.getCountry();
		this.city = a.getCity();
		this.postalCode = a.getPostalCode();
		this.street = a.getStreet();
		this.number = a.getNumber();
		this.apartmentNumber = a.getApartmentNumber();
		this.longitude = a.getLongitude();
		this.latitude = a.getLatitude();
		this.deleted = false;
    }

    public Address(String country, String city, int postalCode, String street, String number, String apartmentNumber,
			double longitude, double latitude) {
		super();
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
		this.street = street;
		this.number = number;
		this.apartmentNumber = apartmentNumber;
		this.longitude = longitude;
		this.latitude = latitude;
		this.deleted = false;
	}

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     */
    public void setPostalCode(int value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * Gets the value of the apartmentNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApartmentNumber() {
        return apartmentNumber;
    }

    /**
     * Sets the value of the apartmentNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApartmentNumber(String value) {
        this.apartmentNumber = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     */
    public void setLongitude(double value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     */
    public void setLatitude(double value) {
        this.latitude = value;
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

	@Override
	public String toString() {
		return "Address [country=" + country + ", city=" + city + ", postalCode=" + postalCode + ", street=" + street
				+ ", number=" + number + ", apartmentNumber=" + apartmentNumber + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", id=" + id + ", deleted=" + deleted + "]";
	}

}
