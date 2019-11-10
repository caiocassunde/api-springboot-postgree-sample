package com.api.sample.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.sample.model.Person;
import com.api.sample.repository.PersonRepository;
import com.api.sample.utils.Return;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	public ResponseEntity<Return> ResponseEntity(@RequestBody Person person){
		Person test = new Person();
		test = personRepository.findByDocument(person.document);
		if(test == null) {
			personRepository.save(person);
			ret.setStatus(HttpStatus.CREATED.toString());
			ret.setMessage("Inclusão realizada com Sucesso");
			return ResponseEntity.status(HttpStatus.CREATED).body(ret);
		}
		else {
			ret.setStatus(HttpStatus.OK.toString());
			ret.setMessage("Cliente ja Existe na Base de Dados");
			return ResponseEntity.status(HttpStatus.OK).body(ret);		
		}
	}

	@DeleteMapping("/person/document={document}")
	@ApiOperation(value="Delete a unique person in PostgreSQL DB")
	public ResponseEntity<Return> deletePerson(@PathVariable(value="document") long document){
		Person test = new Person();
		test = personRepository.findByDocument(document);
		if(test != null) {
			personRepository.delete(test);
			ret.setStatus(HttpStatus.OK.toString());
			ret.setMessage("Deleção realizada com Sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(ret);
		}
		else {
			ret.setStatus(HttpStatus.OK.toString());
			ret.setMessage("Documento não Cadastrado na Base de Dados");
			return ResponseEntity.status(HttpStatus.OK).body(ret);
		}
	}
	
	@PutMapping("/person")
	@ApiOperation(value="Update values of a unique person in PostgreSQL DB")
	public ResponseEntity<Return> putPerson(@RequestBody Person person){
		Optional<Person> testId = personRepository.findById(person.id);
		System.out.println(testId);
		if(testId != null) {
			Person test = new Person();
			test = personRepository.findByDocument(person.document);
			if(test != null) {
				personRepository.save(person);
				ret.setStatus(HttpStatus.OK.toString());
				ret.setMessage("Alteração realizada com Sucesso");
				return ResponseEntity.status(HttpStatus.OK).body(ret);
			}
			else {
				ret.setStatus(HttpStatus.OK.toString());
				ret.setMessage("Documento não Cadastrado na Base de Dados");
				return ResponseEntity.status(HttpStatus.OK).body(ret);
			}
		}	
		else{
			ret.setStatus(HttpStatus.OK.toString());
			ret.setMessage("Id não Cadastrado na Base de Dados");
			return ResponseEntity.status(HttpStatus.OK).body(ret);
		}
	}
}