package tn.enig.project.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.enig.project.Entities.Person;
import tn.enig.project.dto.PersonDTO;


@Component
public class PersonMapper {
	
	@Autowired
	public CartMapper cartMapper;

	public PersonDTO convertPersonToDTO(Person person) {
		if(person ==  null) {
			return null;
		}
		PersonDTO personDTO = new PersonDTO();
		personDTO.setFirstName(person.getFirstName());
		personDTO.setLastName(person.getLastName());
		personDTO.setId(person.getId());
		personDTO.setEmail(person.getEmail());
		personDTO.setPassword(person.getPassword());
		personDTO.setTelephone(person.getTelephone());
		personDTO.setType(person.getType());
		personDTO.setCertif(person.getCertif());
		personDTO.setCart(cartMapper.convertCartToDTO(person.getCart()));
		return personDTO;
	}
	
	
	public Person convertPersonFromDTOToEntity(PersonDTO personDTO) {
		if(personDTO == null) {
			return null;
		}
		Person person = new Person();
		person.setAdresse(personDTO.getAddress());
		if(personDTO.getCart() != null) {
			person.setCart(cartMapper.convertCartDTOToEntity(personDTO.getCart()));
		}
		person.setCertif(personDTO.getCertif());
		person.setEmail(personDTO.getEmail());
		person.setFirstName(personDTO.getFirstName());
		person.setId(personDTO.getId());
		person.setLastName(personDTO.getLastName());
		person.setPassword(personDTO.getPassword());
		person.setTelephone(personDTO.getTelephone());
		person.setType(personDTO.getType());
		
		return person;
		
		
	}
}
