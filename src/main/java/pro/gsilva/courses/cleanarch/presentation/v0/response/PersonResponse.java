package pro.gsilva.courses.cleanarch.presentation.v0.response;

import pro.gsilva.courses.cleanarch.domain.person.Person;

import static pro.gsilva.courses.cleanarch.domain.functions.NullFunctions.ofNullable;
import static pro.gsilva.courses.cleanarch.domain.functions.NullFunctions.unwrapID;

public record PersonResponse(Long id, String firstName, String lastName, AddressResponse address) {
    public static PersonResponse of(Person person) {
        return new PersonResponse(
                unwrapID(person),
                person.getFirstName(),
                person.getLastName(),
                ofNullable(person.getAddress(), AddressResponse::of));
    }
}
