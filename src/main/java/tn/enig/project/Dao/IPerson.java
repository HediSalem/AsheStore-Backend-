package tn.enig.project.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import tn.enig.project.Entities.Person;

@Repository
@Component
public interface IPerson extends CrudRepository<Person, Long>{
	
	@Query("select p from Person p where p.email = :email and p.password = :password")
	Person findPersonbyEmailAndPassword(@Param("email") String email, @Param("password") String password);

}
