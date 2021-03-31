package com.api.sample.data.mapper;

import com.api.sample.data.dto.PersonDTO;
import com.api.sample.data.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", imports = LocalDateTime.class)
public interface PersonMapper {

    @Mapping(expression = "java(LocalDateTime.now())", target = "timestampGet")
    PersonDTO domainToResponse(Person person);

    @Mapping(expression = "java(LocalDateTime.now())", target = "timestampGet")
    List<PersonDTO> domainToResponse(List<Person> person);
}
