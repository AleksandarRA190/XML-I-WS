package ftn.xmlws.wsconfig;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
			
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}
	
	@Bean(name = "addresses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema addressesSchema) {
	  DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
	  definition.setPortTypeName("AddressPort");
	  definition.setTargetNamespace("http://www.projectXml.com/address");
	  definition.setLocationUri("/ws");
	  definition.setSchema(addressesSchema);
	  return definition;
	}
	
	@Bean
	public XsdSchema addressesSchema() {
	  return new SimpleXsdSchema(new ClassPathResource("address.xsd"));
	}
	
	
	@Bean(name = "reservations")
	public DefaultWsdl11Definition defaultWsdl12Definition(XsdSchema reservationsSchema) {
	  DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
	  definition.setPortTypeName("ReservationPort");
	  definition.setTargetNamespace("http://www.projectXml.com/reservation");
	  definition.setLocationUri("/ws");
	  definition.setSchema(reservationsSchema);
	  return definition;
	}
	
	@Bean
	public XsdSchema reservationsSchema() {
	  return new SimpleXsdSchema(new ClassPathResource("reservation.xsd"));
	}
	
	@Bean(name = "mojusers")
	public DefaultWsdl11Definition defaultWsdl14Definition(XsdSchema mojusersSchema) {
	  DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
	  definition.setPortTypeName("MojuserPort");
	  definition.setTargetNamespace("http://www.projectXml.com/mojuser");
	  definition.setLocationUri("/ws");
	  
	  definition.setSchema(mojusersSchema);
	  return definition;
	}
	
	@Bean
	public XsdSchema mojusersSchema() {
	  return new SimpleXsdSchema(new ClassPathResource("mojuser.xsd"));
	}
	
	@Bean(name = "accommodations")
	public DefaultWsdl11Definition defaultWsdl15Definition(XsdSchema accommodationsSchema) {
	  DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
	  definition.setPortTypeName("AccommodationPort");
	  definition.setTargetNamespace("http://www.projectXml.com/accommodation");
	  definition.setLocationUri("/ws");
	  definition.setSchema(accommodationsSchema);
	  return definition;
	}
	
	@Bean
	public XsdSchema accommodationsSchema() {
	  return new SimpleXsdSchema(new ClassPathResource("Accommodation.xsd"));
	}
	
	
	@Bean(name = "accommodationUnits")
	public DefaultWsdl11Definition defaultWsdl16Definition(XsdSchema accommodationUnitsSchema) {
	  DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
	  definition.setPortTypeName("AccommodationUnitPort");
	  definition.setTargetNamespace("http://www.projectXml.com/accommodationUnit");
	  definition.setLocationUri("/ws");
	  definition.setSchema(accommodationUnitsSchema);
	  return definition;
	}
	
	@Bean
	public XsdSchema accommodationUnitsSchema() {
	  return new SimpleXsdSchema(new ClassPathResource("AccommodationUnit.xsd"));
	}
	
	@Bean(name = "services")
	public DefaultWsdl11Definition defaultWsdl17Definition(XsdSchema servicesSchema) {
	  DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
	  definition.setPortTypeName("ServicePort");
	  definition.setTargetNamespace("http://www.projectXml.com/service");
	  definition.setLocationUri("/ws");
	  definition.setSchema(servicesSchema);
	  return definition;
	}
	
	@Bean
	public XsdSchema servicesSchema() {
	  return new SimpleXsdSchema(new ClassPathResource("Service.xsd"));
	}
	
}
