package api.sample.teste.service;

import com.api.sample.data.entity.Person;
import com.api.sample.data.repository.PersonRepository;
import com.api.sample.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = PersonService.class)
public class PersonServiceTest {
	@InjectMocks
	private PersonService personService;

	@Mock
	private PersonRepository personRepository;

	@Mock
	private KafkaTemplate template;

	@Test
	public void createNewPerson() {
		when(personRepository.findByDocument(anyLong())).thenReturn(null);
		when(template.send(anyString(), anyString())).then(null);

		Person person = new Person(1L, 123L, "teste");

		personService.insertPerson(person);
	}

}
