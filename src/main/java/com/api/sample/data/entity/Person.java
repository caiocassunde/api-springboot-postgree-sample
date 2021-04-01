package com.api.sample.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table
public class Person {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long id;

	@Column(unique = true, nullable = false)
	public Long document;

	@Column
	public String name;
}