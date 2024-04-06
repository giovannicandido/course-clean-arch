package pro.gsilva.courses.cleanarch.infrastructure.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pro.gsilva.courses.cleanarch.domain.address.AddressId;
import pro.gsilva.courses.cleanarch.domain.person.Person;
import pro.gsilva.courses.cleanarch.domain.person.PersonId;
import pro.gsilva.courses.cleanarch.domain.person.PersonRepositoryService;
import pro.gsilva.courses.cleanarch.infrastructure.database.entity.PersonEntity;
import pro.gsilva.courses.cleanarch.infrastructure.database.projections.PersonAddressId;
import pro.gsilva.courses.cleanarch.infrastructure.database.repository.AddressRepository;
import pro.gsilva.courses.cleanarch.infrastructure.database.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static pro.gsilva.courses.cleanarch.domain.functions.NullFunctions.isIdNull;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryAdapterImpl implements PersonRepositoryService {
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Override
    public Optional<Person> findById(PersonId personId) {
        return personRepository.findById(personId.id())
                .map(PersonEntity::toDomain);
    }

    @Override
    public Person save(Person person) {
        if(!isIdNull(person)) {
           PersonAddressId personAddressId = addressRepository.findFirstByPerson_Id(person.getPersonId().getId());
           person = person.toBuilder()
                   .address(person.getAddress().toBuilder()
                           .addressId(AddressId.of(personAddressId.getId()))
                           .build())
                   .build();
        }
        return personRepository.save(PersonEntity.fromDomain(person)).toDomain();
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll()
                .stream()
                .map(PersonEntity::toDomain)
                .toList();
    }

    @Override
    public void deleteById(PersonId personId) {
        personRepository.deleteById(personId.id());
    }
}
