package com.safetynet.alert.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name="medical_records")
@IdClass(MedicalRecordsId.class)
public @Data class MedicalRecords implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="first_name")
	@JsonProperty("firstName")
	private String firstName;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="last_name")
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("medications")
	private String medications;
	@JsonProperty("allergies")
	private String allergies;
	
}
