package pro.gsilva.courses.cleanarch.application;

import pro.gsilva.courses.cleanarch.domain.person.Person;
import pro.gsilva.courses.cleanarch.domain.person.PersonId;

import java.util.List;
import java.util.Optional;

public interface PersonUseCase {
    Optional<Person> findPersonById(PersonId id);

    List<Person> findAll();

    Person createNewPerson(Person person);

    void deletePerson(PersonId personId);

    Person updatePerson(Person domain);
}
