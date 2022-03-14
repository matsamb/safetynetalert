package com.safetynet.alert.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SafetyNetAlertFileReadingIntegrationTests {

	@Test
	void givenHelloInaFile_WhenReadFileIsCalled_ThenItShouldReturnHello(){
		String reference = "hello";
		
		String result = "";
				
		assertThat(result).isEqualTo(reference);
	}
}
