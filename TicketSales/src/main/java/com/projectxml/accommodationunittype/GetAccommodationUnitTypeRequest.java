//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.22 at 05:05:02 PM CEST 
//


package com.projectxml.accommodationunittype;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="accommodationUnitTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "accommodationUnitTypeId"
})
@XmlRootElement(name = "getAccommodationUnitTypeRequest")
public class GetAccommodationUnitTypeRequest {

    protected long accommodationUnitTypeId;

    /**
     * Gets the value of the accommodationUnitTypeId property.
     * 
     */
    public long getAccommodationUnitTypeId() {
        return accommodationUnitTypeId;
    }

    /**
     * Sets the value of the accommodationUnitTypeId property.
     * 
     */
    public void setAccommodationUnitTypeId(long value) {
        this.accommodationUnitTypeId = value;
    }

}
