//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.22 at 05:05:02 PM CEST 
//


package com.projectxml.accommodationunittype;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accommodationUnitTypes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accommodationUnitTypes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccommodationUnitTypes" type="{http://www.projectXml.com/accommodationUnitType}accommodationUnitType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accommodationUnitTypes", propOrder = {
    "accommodationUnitTypes"
})
public class AccommodationUnitTypes {

    @XmlElement(name = "AccommodationUnitTypes")
    protected List<AccommodationUnitType> accommodationUnitTypes;

    
    public AccommodationUnitTypes() {
    	this.accommodationUnitTypes = new ArrayList<>();
    }
    /**
     * Gets the value of the accommodationUnitTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accommodationUnitTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccommodationUnitTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccommodationUnitType }
     * 
     * 
     */
    public List<AccommodationUnitType> getAccommodationUnitTypes() {
        if (accommodationUnitTypes == null) {
            accommodationUnitTypes = new ArrayList<AccommodationUnitType>();
        }
        return this.accommodationUnitTypes;
    }

}
