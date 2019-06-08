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
	
}
