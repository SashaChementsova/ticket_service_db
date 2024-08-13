package org.chementsova.dao;

import org.chementsova.model.Person;

import java.util.List;

public interface PersonDAO {
    void savePerson(Person person);

    List<Person> getPeople();

    List<Person> getPersonByID(int personId);

    void deletePerson(int personId);
}
