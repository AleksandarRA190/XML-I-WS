//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.4.0-b180830.0438 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.18 at 10:14:09 AM CEST 
//


package com.projectxml.accommodationunittype;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accommodationUnitType" type="{http://www.projectXml.com/accommodationUnitType}accommodationUnitType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "accommodationUnitType"
})
@XmlRootElement(name = "addAccommodationUnitTypeResponse")
public class AddAccommodationUnitTypeResponse {

    @XmlElement(required = true)
    protected AccommodationUnitType accommodationUnitType;

    /**
     * Gets the value of the accommodationUnitType property.
     * 
     * @return
     *     possible object is
     *     {@link AccommodationUnitType }
     *     
     */
    public AccommodationUnitType getAccommodationUnitType() {
        return accommodationUnitType;
    }

    /**
     * Sets the value of the accommodationUnitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccommodationUnitType }
     *     
     */
    public void setAccommodationUnitType(AccommodationUnitType value) {
        this.accommodationUnitType = value;
    }

}
