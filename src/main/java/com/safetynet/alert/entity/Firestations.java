package com.safetynet.alert.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Firestations {
	
	@Id
	@JsonProperty("address")
	String address;
	
	@JsonProperty("station")
	Integer station;
	
	/*@Column(name="global_entity", insertable = false, updatable = false)
	GlobalEntity[] globalEntity;
	
	@OneToMany
	public GlobalEntity[] getGlobalEntity() {
		return globalEntity;
	}

	public void setgGobalEntity(GlobalEntity[] globalEntity) {
		this.globalEntity = globalEntity;
	}*/
}
