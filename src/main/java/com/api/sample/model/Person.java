package com.api.sample.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="TB_PERSON")
public class Person {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@JsonProperty("id")
	public UUID id = UUID.randomUUID();
	@JsonProperty("document")
	public String document;
	@JsonProperty("name")
	public String name;


	@JsonProperty("id")
	public String getId() {
		return id.toString();
	}

	@JsonProperty("document")
	public String document() {
		return document;
	}

	@JsonProperty("document")
	public void document(String document) {
		this.document = document;
	}
	
	@JsonProperty("name")
	public String name() {
		return name;
	}

	@JsonProperty("name")
	public void name(String name) {
		this.name = name;
	}
}