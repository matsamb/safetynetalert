package com.safetynet.alert.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CommunityEmail {
	
	
	@Id
	String email;
	
	String city;

}
