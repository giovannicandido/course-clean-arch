package pro.gsilva.courses.cleanarch.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.gsilva.courses.cleanarch.domain.person.Person;
import pro.gsilva.courses.cleanarch.domain.person.PersonId;
import pro.gsilva.courses.cleanarch.domain.person.PersonRepositoryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonUseCaseAdapter implements PersonUseCase {
    private final PersonRepositoryService personRepositoryService;

    @Override
    public Optional<Person> findPersonById(PersonId personId) {
        return personRepositoryService.findById(personId);
    }

    @Override
    public List<Person> findAll() {
        return personRepositoryService.findAll();
    }

    @Override
    public Person createNewPerson(Person person) {
        return personRepositoryService.save(person);
    }

    @Override
    public void deletePerson(PersonId personId) {
        personRepositoryService.deleteById(personId);
    }

    @Override
    public Person updatePerson(Person person) {
        return personRepositoryService.save(person);
    }
}
