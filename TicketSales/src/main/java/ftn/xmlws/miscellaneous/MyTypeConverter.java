package ftn.xmlws.miscellaneous;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class MyTypeConverter {
	
	public static LocalDateTime xmlCalendarToLocalDateTime(XMLGregorianCalendar xmlc) {
		return xmlc.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
	}
	
	public static XMLGregorianCalendar localDateTimeToXMLGregorianCalendar(LocalDateTime localDate) {
		
		GregorianCalendar gcal = GregorianCalendar.from(localDate.toLocalDate().atStartOfDay(ZoneId.systemDefault()));
		try {
			XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			return xcal;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}

}
