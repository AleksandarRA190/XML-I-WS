package ftn.xmlws.adapter;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String,LocalDate> {

	public LocalDate unmarshal(String value) {
		return LocalDate.parse(value);
	}
	
	public String marshal(LocalDate value) {
		if(value == null)
			return null;
		return value.toString();
	}
}
