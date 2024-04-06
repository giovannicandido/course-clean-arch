package pro.gsilva.courses.cleanarch.domain.person;

import java.util.List;
import java.util.Optional;

public interface PersonRepositoryService {
    Optional<Person> findById(PersonId personId);
    Person save(Person person);
    List<Person> findAll();

    void deleteById(PersonId personId);
}
