package com.safetynet.alert.exceptions;

import org.springframework.stereotype.Component;

@Component
public class SafetynetalertNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	SafetynetalertNotFoundException(){}
	
	public SafetynetalertNotFoundException(String message) {
		super(message);
	}
}
