//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.23 at 10:21:11 PM CEST 
//


package com.projectxml.message;

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
 *         &lt;element name="respondId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="message" type="{http://www.projectXml.com/message}messageToSend"/>
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
    "respondId",
    "message"
})
@XmlRootElement(name = "respondRequest")
public class RespondRequest {

    protected long respondId;
    @XmlElement(required = true)
    protected MessageToSend message;

    /**
     * Gets the value of the respondId property.
     * 
     */
    public long getRespondId() {
        return respondId;
    }

    /**
     * Sets the value of the respondId property.
     * 
     */
    public void setRespondId(long value) {
        this.respondId = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link MessageToSend }
     *     
     */
    public MessageToSend getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageToSend }
     *     
     */
    public void setMessage(MessageToSend value) {
        this.message = value;
    }

}
