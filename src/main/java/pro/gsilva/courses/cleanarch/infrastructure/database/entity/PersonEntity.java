package pro.gsilva.courses.cleanarch.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pro.gsilva.courses.cleanarch.domain.person.Person;
import pro.gsilva.courses.cleanarch.domain.person.PersonId;

import java.time.LocalDate;

import static pro.gsilva.courses.cleanarch.domain.functions.NullFunctions.*;

@Entity
@Table(name = "person")
@SequenceGenerator(name = "person_seq", sequenceName = "person_seq", initialValue = 1, allocationSize = 1)
@Getter
@Setter
@ToString(exclude = "address")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private LocalDate birthDay;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.DETACH}, optional = false, mappedBy = "person")
    private AddressEntity address;

    public static PersonEntity fromDomain(Person person) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(unwrapID(person));
        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setBirthDay(person.getBirthDay());

        var addressEntity = ofNullable(person.getAddress(), AddressEntity::fromDomain);
        personEntity.setAddress(addressEntity);
        executeIfNotNull(addressEntity, (entity) -> {
            entity.setPerson(personEntity);
        });

        return personEntity;
    }

    public Person toDomain() {
        return Person.builder()
                .personId(PersonId.of(id))
                .firstName(firstName)
                .lastName(lastName)
                .birthDay(birthDay)
                .address(ofNullable(address, AddressEntity::toDomain))
                .build();
    }
}
