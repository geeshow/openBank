package com.ken207.openbank;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenbankApplication.class, args);
	}

	@Bean
	Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
