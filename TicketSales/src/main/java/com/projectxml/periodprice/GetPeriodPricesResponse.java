//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.4.0-b180830.0438 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.21 at 05:17:32 PM CEST 
//


package com.projectxml.periodprice;

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
 *         &lt;element name="periodPrices" type="{http://www.projectXml.com/periodPrice}periodPrices"/&gt;
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
    "periodPrices"
})
@XmlRootElement(name = "getPeriodPricesResponse")
public class GetPeriodPricesResponse {

    @XmlElement(required = true)
    protected PeriodPrices periodPrices;

    /**
     * Gets the value of the periodPrices property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodPrices }
     *     
     */
    public PeriodPrices getPeriodPrices() {
        return periodPrices;
    }

    /**
     * Sets the value of the periodPrices property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodPrices }
     *     
     */
    public void setPeriodPrices(PeriodPrices value) {
        this.periodPrices = value;
    }

}
