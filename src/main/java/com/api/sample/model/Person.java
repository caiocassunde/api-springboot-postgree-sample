package com.api.sample.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name="TB_PERSON")
public class Person {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@JsonProperty("id")
	public UUID id = UUID.randomUUID();
	@JsonProperty("document")
	public long document;
	@JsonProperty("name")
	public String name;

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", document=" + document +
				", name='" + name + '\'' +
				'}';
	}

}