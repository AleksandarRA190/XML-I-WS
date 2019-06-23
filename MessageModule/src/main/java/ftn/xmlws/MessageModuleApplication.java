package ftn.xmlws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories

public class MessageModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageModuleApplication.class, args);
	}

}
