package br.com.unesp.Thales_Hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.unesp.Thales_Hotel.repositories")
public class ThalesHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThalesHotelApplication.class, args);
	}

}
