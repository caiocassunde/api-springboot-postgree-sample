package com.api.sample.data.mapper;

import com.api.sample.data.dto.PersonDTO;
import com.api.sample.data.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PersonMapper {
    PersonDTO domainToResponse(Person person);

    List<PersonDTO> domainToResponse(List<Person> person);
}
