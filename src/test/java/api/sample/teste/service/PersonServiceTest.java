package api.sample.teste.service;

import com.api.sample.data.dto.PersonDTO;
import com.api.sample.data.entity.Person;
import com.api.sample.data.mapper.PersonMapper;
import com.api.sample.data.repository.PersonRepository;
import com.api.sample.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = PersonService.class)
public class PersonServiceTest {
    @InjectMocks
    private PersonService personService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @Mock
    private KafkaTemplate template;

	@Test
	public void getAllPersonTest() throws IOException {
		when(personRepository.findAll()).thenReturn(personListMock());
		when(personMapper.domainToResponse((List<Person>) any())).thenReturn(personDTOListMock());

		personService.getPersonById(1L);
	}

    @Test
    public void getPersonByIdTest() throws IOException {
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(personMock()));
        when(personMapper.domainToResponse((Person) any())).thenReturn(personDTOMock());

        personService.getPersonById(1L);
    }

    @Test
    public void getPersonByDocumentTest() throws IOException {
        when(personRepository.findByDocument(anyLong())).thenReturn(Optional.of(personMock()));
        when(personMapper.domainToResponse((Person) any())).thenReturn(personDTOMock());

        personService.getPersonByDocument(1L);
    }

//	@Test
//	public void createNewPersonTest() {
//		when(personRepository.findByDocument(anyLong())).thenReturn(null);
//		when(template.send(anyString(), anyString())).thenReturn(OngoingStubbing<CompletableFuture>());
//
//		Person person = new Person(1L, 123L, "teste");
//
//		personService.insertPerson(person);
//	}

//	private List<Person> buildPersonResponseList(String responseFilePath) throws IOException {
//		Resource personResponse = resourceLoader.getResource("classpath:" + responseFilePath);
//
//		return objectMapper.readValue(personResponse.getInputStream(), List<Person>.class);
//	}

//	private Person buildPersonResponse(String responseFilePath) throws IOException {
//		Resource personResponse = resourceLoader.getResource("classpath:" + responseFilePath);
//
//		return objectMapper.readValue(personResponse.getInputStream(), Person.class);
//	}

    private List<Person> personListMock() {
        Person person = new Person();
        person.setId(1L);
        person.setDocument(123L);
        person.setName("teste");

        List<Person> personList = new ArrayList<>();

        personList.add(person);
        return personList;
    }

    private Person personMock() {
        Person person = new Person();
        person.setId(1L);
        person.setDocument(123L);
        person.setName("teste");

        return person;
    }

    private PersonDTO personDTOMock() {
        return PersonDTO.builder()
                .id(1L)
                .document(123L)
                .name("teste")
                .build();
    }

    private List<PersonDTO> personDTOListMock() {
        List<PersonDTO> personDTOList = new ArrayList<>();
        personDTOList.add(PersonDTO.builder()
                .id(1L)
                .document(123L)
                .name("teste")
                .build());
        return personDTOList;
    }
}
