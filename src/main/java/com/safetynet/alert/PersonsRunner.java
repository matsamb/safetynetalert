package com.safetynet.alert;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.service.PersonsService;

@Component
@Order(1)
public class PersonsRunner implements CommandLineRunner {

	@Autowired
	private PersonsService safetynetalertPersonsService;
	
	@Override
	public void run(String... args) throws Exception {

		
	} 
}
