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
 *         &lt;element name="accommodationUnits" type="{http://www.projectXml.com/accommodationUnit}accommodationUnits"/>
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
    "accommodationUnits"
})
@XmlRootElement(name = "getAccommodationUnitsResponse")
public class GetAccommodationUnitsResponse {

    @XmlElement(required = true)
    protected AccommodationUnits accommodationUnits;

    /**
     * Gets the value of the accommodationUnits property.
     * 
     * @return
     *     possible object is
     *     {@link AccommodationUnits }
     *     
     */
    public AccommodationUnits getAccommodationUnits() {
        return accommodationUnits;
    }

    /**
     * Sets the value of the accommodationUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccommodationUnits }
     *     
     */
    public void setAccommodationUnits(AccommodationUnits value) {
        this.accommodationUnits = value;
    }

}
