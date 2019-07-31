package com.api.sample.controller;

import com.api.sample.model.Person;
import com.api.sample.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value="/api")
public class Controller {

	@Autowired
	PersonRepository personRepository;

	@GetMapping("/person")
	public List<Person> getPerson(){
		return personRepository.findAll();
	}

	@GetMapping("/person/{id}")
	public Optional<Person> getPersonById(@PathVariable(value="id") UUID id){
		return personRepository.findById(id);
	}

	@PostMapping("/person")
	public Person postPerson(@RequestBody Person produto){
		return personRepository.save(produto);
	}
}
