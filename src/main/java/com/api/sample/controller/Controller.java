package com.api.sample.controller;

import com.api.sample.model.Person;
import com.api.sample.repository.PersonRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Sample")
@CrossOrigin(origins="*")
public class Controller {

	@Autowired
	PersonRepository personRepository;

	@GetMapping("/person")
	@ApiOperation(value="Select a list of people in PostgreSQL DB")
	public List<Person> getPerson(){
		return personRepository.findAll();
	}

	@GetMapping("/person/{id}")
	@ApiOperation(value="Select a unique person in PostgreSQL DB")
	public Optional<Person> getPersonById(@PathVariable(value="id") UUID id){
		return personRepository.findById(id);
	}

	@PostMapping("/person")
	@ApiOperation(value="Insert a unique person in PostgreSQL DB")
	public Person postPerson(@RequestBody Person person){
		return personRepository.save(person);
	}

	@DeleteMapping("/person")
	@ApiOperation(value="Delete a unique person in PostgreSQL DB")
	public String deletePerson(@RequestBody Person person){
			personRepository.delete(person);
			return("Deleção Realizada com Sucesso!");
	}
	@PutMapping("/person")
	@ApiOperation(value="Update values of a unique person in PostgreSQL DB")
	public Person putPerson(@RequestBody Person person){
		return personRepository.save(person);
	}
}
