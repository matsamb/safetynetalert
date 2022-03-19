package com.safetynet.alert;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jni.File;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.factory.SafetynetalertFactory;
import com.safetynet.alert.service.SafetynetalertPersonsService;

import ch.qos.logback.core.net.SyslogOutputStream;

@SpringBootApplication
public class SafetynetalertApplication implements CommandLineRunner{

	//private static final Logger logger = LogManager.getLogger("alertApp");
	
	public static void main(String[] args) {
		SpringApplication.run(SafetynetalertApplication.class, args);
	}
	
	@Autowired
	private SafetynetalertPersonsService safetynetalertPersonsService;
	 
	@Override
	public void run(String... args) throws Exception {

		safetynetalertPersonsService.jsonToDatabase();
		//TypeReference<Map<String,Object>> DataTableNameIterable = new TypeReference<>() {};
		//ObjectMapper DataTableNameMapper = new ObjectMapper();		
		
		
	}
	
}
