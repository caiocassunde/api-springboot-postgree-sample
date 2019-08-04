package com.api.sample.repository;


import com.api.sample.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findById(UUID id);
    Person findByDocument(long document);
}