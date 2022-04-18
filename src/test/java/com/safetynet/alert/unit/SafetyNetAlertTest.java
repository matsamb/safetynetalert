package com.safetynet.alert.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.safetynet.alert.controller.SafetynetalertController;
import com.safetynet.alert.service.SafetynetService;

@WebMvcTest(controllers = SafetynetalertController.class)
public class SafetyNetAlertTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SafetynetService mockService;

	
	@Test
	public void testgetFloodOne() throws Exception {
		mockMvc.perform(get("/flood/stations?stations=1"))
			.andExpect(status().isOk());
	}	
	
	@Test
	public void testgetFloodOneAndTwo() throws Exception {
		mockMvc.perform(get("/flood/stations?stations=1,2"))
			.andExpect(status().isOk());
	}	
	
}
