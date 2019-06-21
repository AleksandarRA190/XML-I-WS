//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.4.0-b180830.0438 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.21 at 05:17:32 PM CEST 
//


package com.projectxml.periodprice;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import ftn.xmlws.adapter.LocalDateAdapter;
import ftn.xmlws.dto.PeriodPriceDTO;


/**
 * <p>Java class for periodPrice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="periodPrice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="AccommodationUnit" type="{http://www.projectXml.com/periodPrice}accommodationUnit"/&gt;
 *         &lt;element name="FromDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="ToDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "periodPrice", propOrder = {
    "id",
    "price",
    "accommodationUnit",
    "fromDate",
    "toDate"
})
public class PeriodPrice {

    @XmlElement(name = "Id")
    protected Long id;
    @XmlElement(name = "Price")
    protected double price;
    @XmlElement(name = "AccommodationUnit", required = true)
    protected AccommodationUnit accommodationUnit;
    @XmlElement(name = "FromDate", required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fromDate;
    @XmlElement(name = "ToDate", required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate toDate;

    
    public PeriodPrice() {
    	
    }
    
    public PeriodPrice(PeriodPriceDTO pDTO) {
    	this.id = pDTO.getId();
    	this.price = pDTO.getPrice();
    	this.accommodationUnit = new AccommodationUnit(pDTO.getAccommodationUnit());
    	this.fromDate = pDTO.getFromDate();
    	this.toDate = pDTO.getToDate();
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
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the accommodationUnit property.
     * 
     * @return
     *     possible object is
     *     {@link AccommodationUnit }
     *     
     */
    public AccommodationUnit getAccommodationUnit() {
        return accommodationUnit;
    }

    /**
     * Sets the value of the accommodationUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccommodationUnit }
     *     
     */
    public void setAccommodationUnit(AccommodationUnit value) {
        this.accommodationUnit = value;
    }

    /**
     * Gets the value of the fromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Sets the value of the fromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFromDate(LocalDate value) {
        this.fromDate = value;
    }

    /**
     * Gets the value of the toDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Sets the value of the toDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setToDate(LocalDate value) {
        this.toDate = value;
    }

}
