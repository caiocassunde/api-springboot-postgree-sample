package com.api.sample.data.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "PersonDtoBuilder", toBuilder = true)
@JsonDeserialize(builder = PersonDTO.PersonDtoBuilder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {
    public Long id;

    public Long document;

    public String name;

    public LocalDateTime timestampGet;

    @JsonPOJOBuilder(withPrefix = "")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PersonDtoBuilder {

    }
}
