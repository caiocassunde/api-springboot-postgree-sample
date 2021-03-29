package com.api.sample.data.repository;


import com.api.sample.data.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByDocument(Long document);
}