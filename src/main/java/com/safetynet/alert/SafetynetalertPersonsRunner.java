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
import com.safetynet.alert.service.SafetynetalertPersonsService;

@Component
@Order(1)
public class SafetynetalertPersonsRunner implements CommandLineRunner {

	@Autowired
	private SafetynetalertPersonsService safetynetalertPersonsService;
	
	@Override
	public void run(String... args) throws Exception {
/*
		//safetynetalertPersonsService.jsonToDatabase();
		TypeReference<List<DataTableName>> DataTableNameIterable = new TypeReference<>() {};
		ObjectMapper DataTableNameMapper = new ObjectMapper();		
		
		try {
			String instring = TypeReference.class.getResourceAsStream(
					"https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json").toString();	
					
			List<DataTableName> result = DataTableNameMapper.readValue(instring
					, DataTableNameIterable) ;
			System.out.println(result.toString());
			//safetynetalertPersonsService.saveAllPersons(result);
			//logger.trace("file reader service get file content correctly");
		} catch (IOException e) {
			e.printStackTrace();
			//logger.error("file reader service can not fetch file content");
			
	
	} */
	} 
}
