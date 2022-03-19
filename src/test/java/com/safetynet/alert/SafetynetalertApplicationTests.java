package com.safetynet.alert;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:applicationIT.properties")
class SafetynetalertApplicationTests {

	@Test
	void contextLoads() {
	}

}
