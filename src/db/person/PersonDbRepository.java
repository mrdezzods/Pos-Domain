package db.person;

import java.util.List;

import domain.person.Person;

/**
 * 
 * @author Milan Sanders, Wouter Dumoulin
 *
 */
public interface PersonDbRepository {
	Person get(String userId);
	List<Person> getAll();
	void add(Person person);
	void update(Person person);
	void delete(String userId);
}
