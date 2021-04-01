package com.api.sample.service;

import com.api.sample.data.dto.Return;
import com.api.sample.data.entity.Person;
import com.api.sample.data.mapper.PersonMapper;
import com.api.sample.data.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final KafkaTemplate template;
    private final String NOT_FOUND_MESSAGE = "Not found";
    private Return ret;

    public ResponseEntity<Object> getAllPerson() {
        List<Person> people = personRepository.findAll();

        if (!CollectionUtils.isEmpty(people)) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(personMapper.domainToResponse(people));
        } else {
            ret = buildReturn(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(ret);
        }
    }

    public ResponseEntity<Object> getPersonById(Long id) {
        Person person = personRepository.findById(id)
                                        .orElse(null);
        if (Objects.nonNull(person)) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(personMapper.domainToResponse(person));
        } else {
            ret = buildReturn(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(ret);
        }
    }

    public ResponseEntity<Object> getPersonByDocument(Long document) {
        Person person = personRepository.findByDocument(document)
                                        .orElse(null);
        if (Objects.nonNull(person)) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(personMapper.domainToResponse(person));
        } else {
            ret = buildReturn(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(ret);
        }
    }

    public ResponseEntity<Object> insertPerson(Person person) {
        try {
            personRepository.save(person);

            template.send("api-spring-topic", personMapper.domainToResponse(person)
                                                          .toString());
        } catch (DataIntegrityViolationException ex) {
            ret = buildReturn("Request can't be saved in database", HttpStatus.BAD_REQUEST.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(ret);
        }
        ret = buildReturn("Created", HttpStatus.CREATED.toString());
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ret);
    }

    public ResponseEntity<Object> deletePerson(Long document) {
        Person person = personRepository.findByDocument(document)
                                        .orElse(null);

        if (Objects.nonNull(person)) {
            personRepository.delete(person);
            ret = buildReturn("Deleted", HttpStatus.OK.toString());
        } else {
            ret = buildReturn("Document Not in Database", HttpStatus.NOT_FOUND.toString());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ret);
    }

    public ResponseEntity<Object> putPerson(Person person) {
        Person personInDb = personRepository.findByDocument(person.getDocument())
                                            .orElse(null);

        if (Objects.nonNull(personInDb)) {
            if (person.getId()
                      .equals(personInDb.getId())) {
                personRepository.save(person);
                ret = buildReturn("Change With Success", HttpStatus.OK.toString());
            } else {
                ret = buildReturn("The id is not the same of this document", HttpStatus.NOT_FOUND.toString());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(ret);
            }
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(ret);
        } else {
            ret = buildReturn("Document Not in Database", HttpStatus.NOT_FOUND.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(ret);
        }
    }

    public Return buildReturn(String message, String status) {
        return Return.builder()
                     .message(message)
                     .status(status)
                     .build();
    }
}