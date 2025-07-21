package com.paulopsms.idp_authenticator;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
public class IdpAuthenticatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdpAuthenticatorApplication.class, args);
	}

	@PostConstruct
	public void setTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo")); // Or your desired timezone
	}
}
