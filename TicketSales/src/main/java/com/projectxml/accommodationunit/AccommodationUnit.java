//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.08 at 07:40:14 PM CEST 
//


package com.projectxml.accommodationunit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accommodationUnit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accommodationUnit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Floor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumberOfBeds" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DefaultPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="AccommodationType" type="{http://www.projectXml.com/accommodationUnit}accommodationUnitType"/>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Accommodation" type="{http://www.projectXml.com/accommodationUnit}accommodation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accommodationUnit", propOrder = {
    "floor",
    "number",
    "numberOfBeds",
    "defaultPrice",
    "accommodationType",
    "id",
    "accommodation"
})
public class AccommodationUnit {

    @XmlElement(name = "Floor")
    protected int floor;
    @XmlElement(name = "Number", required = true)
    protected String number;
    @XmlElement(name = "NumberOfBeds")
    protected int numberOfBeds;
    @XmlElement(name = "DefaultPrice")
    protected double defaultPrice;
    @XmlElement(name = "AccommodationType", required = true)
    protected AccommodationUnitType accommodationType;
    @XmlElement(name = "Id")
    protected long id;
    @XmlElement(name = "Accommodation", required = true)
    protected Accommodation accommodation;

    /**
     * Gets the value of the floor property.
     * 
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Sets the value of the floor property.
     * 
     */
    public void setFloor(int value) {
        this.floor = value;
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
     * Gets the value of the numberOfBeds property.
     * 
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Sets the value of the numberOfBeds property.
     * 
     */
    public void setNumberOfBeds(int value) {
        this.numberOfBeds = value;
    }

    /**
     * Gets the value of the defaultPrice property.
     * 
     */
    public double getDefaultPrice() {
        return defaultPrice;
    }

    /**
     * Sets the value of the defaultPrice property.
     * 
     */
    public void setDefaultPrice(double value) {
        this.defaultPrice = value;
    }

    /**
     * Gets the value of the accommodationType property.
     * 
     * @return
     *     possible object is
     *     {@link AccommodationUnitType }
     *     
     */
    public AccommodationUnitType getAccommodationType() {
        return accommodationType;
    }

    /**
     * Sets the value of the accommodationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccommodationUnitType }
     *     
     */
    public void setAccommodationType(AccommodationUnitType value) {
        this.accommodationType = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the accommodation property.
     * 
     * @return
     *     possible object is
     *     {@link Accommodation }
     *     
     */
    public Accommodation getAccommodation() {
        return accommodation;
    }

    /**
     * Sets the value of the accommodation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Accommodation }
     *     
     */
    public void setAccommodation(Accommodation value) {
        this.accommodation = value;
    }

}