//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.07 at 07:55:21 PM CEST 
//


package ftn.xmlws.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import ftn.xmlws.dto.ReservationDTO;
import ftn.xmlws.miscellaneous.MyTypeConverter;


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
 *         &lt;element name="From_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="To_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Confirmed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Comment_rate" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Komentar" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="Odobren" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                           &lt;attribute name="Sadrzaj" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Ocena">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                         &lt;enumeration value="1"/>
 *                         &lt;enumeration value="2"/>
 *                         &lt;enumeration value="3"/>
 *                         &lt;enumeration value="4"/>
 *                         &lt;enumeration value="5"/>
 *                         &lt;enumeration value="0"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Datum_vreme" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://booking.uns.ac.rs/accommodation}Accommodation_unit"/>
 *         &lt;element name="Guest" type="{http://booking.uns.ac.rs/users}User"/>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://booking.uns.ac.rs/reservation}Message" maxOccurs="unbounded" minOccurs="0"/>
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
    "fromDate",
    "toDate",
    "confirmed",
    "commentRate",
    "accommodationUnit",
    "guest",
    "id",
    "deleted",
    "messages"
})
@Entity
@XmlRootElement(name = "Reservation", namespace = "http://booking.uns.ac.rs/reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement(name = "Id", namespace = "http://booking.uns.ac.rs/reservation")
    protected Long id;

	@Transient
    @XmlElement(name = "From_date", namespace = "http://booking.uns.ac.rs/reservation", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fromDate;
    
	@Column(name = "From_date")
	public Calendar getFromDateToCalendar() {
	    return new GregorianCalendar(fromDate.getYear(), fromDate.getMonth(), fromDate.getDay());
	}
	
    @Transient
    @XmlElement(name = "To_date", namespace = "http://booking.uns.ac.rs/reservation", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar toDate;
    
    @Column(name = "To_date")
	public Calendar getToDateToCalendar() {
	    return new GregorianCalendar(toDate.getYear(), toDate.getMonth(), toDate.getDay());
	}
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @XmlElement(name = "Guest", namespace = "http://booking.uns.ac.rs/reservation", required = true)
    protected User guest;
    
    @XmlElement(name = "Confirmed", namespace = "http://booking.uns.ac.rs/reservation")
    protected boolean confirmed;
    
    @XmlElement(name = "Deleted", namespace = "http://booking.uns.ac.rs/reservation")
    protected boolean deleted;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @XmlElement(name = "Accommodation_unit", required = true)
    protected AccommodationUnit accommodationUnit;
    
    /*@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName="id", nullable = false, unique = true)	
    @XmlElement(name = "Comment_rate", namespace = "http://booking.uns.ac.rs/reservation")
    protected Reservation.CommentRate commentRate;

    @XmlElement(name = "Message", namespace = "http://booking.uns.ac.rs/reservation")
    protected List<Message> messages;*/

    
public Reservation() {}
    
    
    public Reservation(XMLGregorianCalendar fromDate, XMLGregorianCalendar toDate, boolean confirmed) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.confirmed = confirmed;
		this.deleted = false;
	}
    
    public Reservation(ReservationDTO res) {
		super();
		this.fromDate = MyTypeConverter.localDateToXMLGregorianCalendar(res.getFromDate());
		this.toDate = MyTypeConverter.localDateToXMLGregorianCalendar(res.getToDate());
		this.confirmed = res.isConfirmed();
		this.deleted = res.isDeleted();
	}

    
    /**
     * Gets the value of the fromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFromDate() {
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
    public void setFromDate(XMLGregorianCalendar value) {
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
    public XMLGregorianCalendar getToDate() {
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
    public void setToDate(XMLGregorianCalendar value) {
        this.toDate = value;
    }

    /**
     * Gets the value of the confirmed property.
     * 
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * Sets the value of the confirmed property.
     * 
     */
    public void setConfirmed(boolean value) {
        this.confirmed = value;
    }

    /**
     * Gets the value of the commentRate property.
     * 
     * @return
     *     possible object is
     *     {@link Reservation.CommentRate }
     *     
     */
    /*public Reservation.CommentRate getCommentRate() {
        return commentRate;
    }
*/
    /**
     * Sets the value of the commentRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reservation.CommentRate }
     *     
     */
  /*  public void setCommentRate(Reservation.CommentRate value) {
        this.commentRate = value;
    }
*/
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
     * Gets the value of the guest property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getGuest() {
        return guest;
    }

    /**
     * Sets the value of the guest property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setGuest(User value) {
        this.guest = value;
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
     * Gets the value of the messages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Message }
     * 
     * 
     */
  /*  public List<Message> getMessages() {
        if (messages == null) {
            messages = new ArrayList<Message>();
        }
        return this.messages;
    }
*/

    
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
     *         &lt;element name="Komentar" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="Odobren" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                 &lt;attribute name="Sadrzaj" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Ocena">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *               &lt;enumeration value="1"/>
     *               &lt;enumeration value="2"/>
     *               &lt;enumeration value="3"/>
     *               &lt;enumeration value="4"/>
     *               &lt;enumeration value="5"/>
     *               &lt;enumeration value="0"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="Datum_vreme" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    /*
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "komentar",
        "ocena"
    })
    @Entity
    public static class CommentRate {

    	@XmlElement(name = "Komentar", namespace = "http://booking.uns.ac.rs/reservation")
        protected Reservation.CommentRate.Komentar komentar;

    	@XmlElement(name = "Ocena", namespace = "http://booking.uns.ac.rs/reservation")
        protected int ocena;
        
    	@Transient
    	@XmlAttribute(name = "Datum_vreme")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar datumVreme;

    	@Column(name = "Datum_vreme")
    	public Calendar getFromDateToCalendar() {
    	    return new GregorianCalendar(datumVreme.getYear(), datumVreme.getMonth(), datumVreme.getDay());
    	}
        /**
         * Gets the value of the komentar property.
         * 
         * @return
         *     possible object is
         *     {@link Reservation.CommentRate.Komentar }
         *     
         */
        //public Reservation.CommentRate.Komentar getKomentar() {
          //  return komentar;
        //}

        /**
         * Sets the value of the komentar property.
         * 
         * @param value
         *     allowed object is
         *     {@link Reservation.CommentRate.Komentar }
         *     
         */
        //public void setKomentar(Reservation.CommentRate.Komentar value) {
          //  this.komentar = value;
        //}

        /**
         * Gets the value of the ocena property.
         * 
         */
        //public int getOcena() {
          //  return ocena;
        //}

        /**
         * Sets the value of the ocena property.
         * 
         */
        //public void setOcena(int value) {
         //   this.ocena = value;
        //}

        /**
         * Gets the value of the datumVreme property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        //public XMLGregorianCalendar getDatumVreme() {
          //  return datumVreme;
        //}

        /**
         * Sets the value of the datumVreme property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        //public void setDatumVreme(XMLGregorianCalendar value) {
          //  this.datumVreme = value;
        //}


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="Odobren" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *       &lt;attribute name="Sadrzaj" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        /*@XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        @Entity
        public static class Komentar {

            @XmlAttribute(name = "Odobren")
            protected Boolean odobren;
            @XmlAttribute(name = "Sadrzaj")
            protected String sadrzaj;
*/
            /**
             * Gets the value of the odobren property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
  /*          public Boolean isOdobren() {
                return odobren;
            }
*/
            /**
             * Sets the value of the odobren property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
  /*          public void setOdobren(Boolean value) {
                this.odobren = value;
            }

    */        /**
             * Gets the value of the sadrzaj property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
      /*      public String getSadrzaj() {
                return sadrzaj;
            }
*/
            /**
             * Sets the value of the sadrzaj property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
  /*          public void setSadrzaj(String value) {
                this.sadrzaj = value;
            }
*/
  //      }

    

}
