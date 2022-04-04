package com.safetynet.alert;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alert.service.SafetynetService;

@SpringBootTest
@WebMvcTest
@TestPropertySource(locations = "classpath:applicationIT.properties")
public class SafetynetalertWebLayerTest {

	@Autowired
	private MockMvc alertWebMockMvc;
	
	@
	
	@MockBean  
	private SafetynetService serviceWebMockBean;
	
	@Test
	public void firestationssShouldReturnAFirestation() throws Exception {
		when(serviceWebMockBean.getAllFirestations()).thenReturn("\\[\\{\"address\":\"112 Steppes Pl\",\"station\":4\\}"
				+ ",{\"address\":\"1509 Culver St\",\"station\":3\\}\\]");
	}
	
	
}
