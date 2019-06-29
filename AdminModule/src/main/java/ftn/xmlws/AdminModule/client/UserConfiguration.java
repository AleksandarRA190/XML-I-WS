package ftn.xmlws.AdminModule.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class UserConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("ftn.xmlws.AdminModule");
		return marshaller;
	}

	@Bean
	public UserClient userClient(Jaxb2Marshaller marshaller) {
		UserClient userClient = new UserClient();
		userClient.setDefaultUri("http://localhost:9007/ws");
		userClient.setMarshaller(marshaller);
		userClient.setUnmarshaller(marshaller);
		return userClient;
	}
}
