package ftn.xmlws.miscellaneous;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class MyTypeConverter {
	
	public static LocalDate xmlCalendarToLocalDate(XMLGregorianCalendar xmlc) {
		return xmlc.toGregorianCalendar().toZonedDateTime().toLocalDate();
	}
	
	public static XMLGregorianCalendar localDateToXMLGregorianCalendar(LocalDate localDate) {
		
		GregorianCalendar gcal = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		try {
			XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			return xcal;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}

}
