package com.safetynet.alert;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alert.controller.SafetynetalertController;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:applicationIT.properties")
class SafetynetalertApplicationTests {
	
	@Autowired
	private MockMvc alertMockMvc;
	
	@Autowired
	private SafetynetalertController alertController;
	

	
	

}
