package com.api.sample.controller;

import com.api.sample.model.Person;
import com.api.sample.repository.PersonRepository;
import com.api.sample.utils.Return;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.NonUniqueResultException;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Sample")
@CrossOrigin(origins="*")
public class Controller {

	@Autowired
	PersonRepository personRepository;
	
	Return ret = new Return();

	@GetMapping("/person")
	@ApiOperation(value="Select a list of people in PostgreSQL DB")
	public List<Person> getPerson(){
		return personRepository.findAll();
	}

	@GetMapping("/person/get-id")
	@ApiOperation(value="Select a unique person in PostgreSQL DB")
	public Optional<Person> getPersonById(@RequestParam(value="id") UUID id){
		return personRepository.findById(id);
	}
	
	@GetMapping("/person/get-document")
	@ApiOperation(value="Select a unique person in PostgreSQL DB")
	public Person getPersonByDocument(@RequestParam(value="document") long document){
		try {
			Person test = new Person();
			test = personRepository.findByDocument(document);
			if(test != null) {
				return test;
			}
			else {
				return null;
			}
		}
		catch(NonUniqueResultException e) {
			System.out.println("caiu exception");
			return null;
		}
	}

	@PostMapping("/person")
	@ApiOperation(value="Insert a unique person in PostgreSQL DB")
	public Return postPerson(@RequestBody Person person){
		Person test = new Person();
		test = personRepository.findByDocument(person.document);
		if(test == null) {
			personRepository.save(person);
			ret.setStatus(200);
			ret.setMessage("Inclusão realizada com Sucesso");
			return ret;
		}
		else {
			ret.setStatus(200);
			ret.setMessage("Cliente ja Existe na Base de Dados");
			return ret;
		}
	}

	@DeleteMapping("/person/document={document}")
	@ApiOperation(value="Delete a unique person in PostgreSQL DB")
	public Return deletePerson(@PathVariable(value="document") long document){
		Person test = new Person();
		test = personRepository.findByDocument(document);
		if(test != null) {
			personRepository.delete(test);
			ret.setStatus(200);
			ret.setMessage("Deleção realizada com Sucesso");
			return ret;
		}
		else {
			ret.setStatus(200);
			ret.setMessage("Documento não Cadastrado na Base de Dados");
			return ret;
		}
	}
	
	@PutMapping("/person")
	@ApiOperation(value="Update values of a unique person in PostgreSQL DB")
	public Return putPerson(@RequestBody Person person){
		Optional<Person> testId = personRepository.findById(person.id);
		System.out.println(testId);
		if(testId != null) {
			Person test = new Person();
			test = personRepository.findByDocument(person.document);
			if(test != null) {
				personRepository.save(person);
				ret.setStatus(200);
				ret.setMessage("Alteração realizada com Sucesso");
				return ret;
			}
			else {
				ret.setStatus(200);
				ret.setMessage("Documento não Cadastrado na Base de Dados");
				return ret;
			}
		}	
		else{
			ret.setStatus(200);
			ret.setMessage("Id não Cadastrado na Base de Dados");
			return ret;
		}
	}
}