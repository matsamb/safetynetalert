package com.safetynet.alert.unit;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.safetynet.alert.controller.SafetynetalertController;
import com.safetynet.alert.entity.ChildAlert;
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
	
	@Test
	public void testgetFloodOneAndTwoAndThree() throws Exception {
		mockMvc.perform(get("/flood/stations?stations=1,2,3"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testgetChildAlert1509CulverSt() throws Exception {
		
		ChildAlert c1 = new ChildAlert("Roger","Boyd",4);
		ChildAlert c2 = new ChildAlert("Tenley","Boyd",10);

		ChildAlert a1 = new ChildAlert("Felicia","Boyd",36);
		ChildAlert a2 = new ChildAlert("Jacob","Boyd",33);
		ChildAlert a3 = new ChildAlert("John","Boyd",38);

		ChildAlert[] c = {c1,c2};
		ChildAlert[] a = {a1,a2,a3};
		
		when(mockService.getCustomChildAlert(anyString())).thenReturn(c);
		when(mockService.getCustomChildAlertAdult(anyString())).thenReturn(a);

		
		mockMvc.perform(get("/childAlert?address=1509 Culver St"))
			.andExpect(status( ).isOk());
	}
	
}
