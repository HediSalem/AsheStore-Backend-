package tn.enig.project.RestServices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.enig.project.Dao.IPerson;
import tn.enig.project.Entities.Person;
import tn.enig.project.dto.PersonDTO;
import tn.enig.project.dto.mapper.PersonMapper;

@RestController
public class PersonRestServices {

	@Autowired
	private IPerson personDao;

	@Autowired
	private PersonMapper personMapper;

	@GetMapping("/persons")
	public List<PersonDTO> getAll() {
		Iterable<Person> allPersons = personDao.findAll();
		return StreamSupport.stream(allPersons.spliterator(), false).map(personMapper::convertPersonToDTO)
				.collect(Collectors.toList());

	}

	@PostMapping("/persons")
	public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {
		Person person = personMapper.convertPersonFromDTOToEntity(personDTO);
		if(person == null) {
			return null;
		}
		person.setId(null);
		person = personDao.save(person);
		return personMapper.convertPersonToDTO(person);
		
	}

	@GetMapping("/persons/{id}")
	public PersonDTO  getPersonById(@PathVariable Long id) {
		if(id == null) {
			return null;
		}
		Optional<Person> person = personDao.findById(id);
		if(person.isPresent()) {
			return personMapper.convertPersonToDTO(person.get());
		}
		return null;
		
	}

	@DeleteMapping("/persons/{id}")
	public void deletePerson(@PathVariable Long id) {
		personDao.deleteById(id);
	}
	
	@PostMapping("/persons/login")
	public PersonDTO  getPersonById(@RequestBody PersonDTO personDTO) {
		if(personDTO == null) {
			return null;
		}
		Person person = personDao.findPersonbyEmailAndPassword(personDTO.getEmail(), personDTO.getPassword());
		if(person != null) {
			return personMapper.convertPersonToDTO(person);
		}
		return null;
		
	}
}
