package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
// @PropertySource(name="EncryptedProperties", value = "classpath:encrypted.properties")
public class RegistrationApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}

}
