package tn.enig.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enig.project.Dao.IPerson;
import tn.enig.project.Entities.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private final IPerson personDao;
	
	
	@Autowired
	public PersonServiceImpl(IPerson personDao) {
		super();
		this.personDao = personDao;
	}



	@Override
	public void addPerson(Person person) {
		personDao.save(person);

	}

}
