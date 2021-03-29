package com.api.sample.controller;

import com.api.sample.data.dto.PersonDto;
import com.api.sample.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.sample.data.entity.Person;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/person")
@Api(value="API REST Sample")
@CrossOrigin(origins="*")
public class PersonController {

	private final PersonService personService;

	@GetMapping
	@ApiOperation(value="Select a list of people in DB")
	public ResponseEntity<Object> getAllPerson(){
		return personService.getAllPerson();
	}

	@GetMapping("/id/{id}")
	@ApiOperation(value="Select a unique person by Id in DB")
	public ResponseEntity<Object> getPersonById(@PathVariable(value="id") Long id){
		return personService.getPersonById(id);
	}
	
	@GetMapping("/document/{document}")
	@ApiOperation(value="Select a unique person by Document in DB")
	public ResponseEntity<Object> getPersonByDocument(@PathVariable(value="document") Long document){
		return personService.getPersonByDocument(document);
	}

	@PostMapping
	@ApiOperation(value="Insert a unique person in DB")
	public ResponseEntity<Object> insertPerson(@RequestBody Person person){
		return personService.insertPerson(person);
	}

	@DeleteMapping("/document/{document}")
	@ApiOperation(value="Delete a unique person in DB")
	public ResponseEntity<Object> deletePerson(@PathVariable(value="document") Long document){
		return personService.deletePerson(document);
	}
	
	@PutMapping
	@ApiOperation(value="Update values of a unique person in DB")
	public ResponseEntity<Object> putPerson(@RequestBody Person person){
		return personService.putPerson(person);
	}
}