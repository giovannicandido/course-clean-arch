package pro.gsilva.courses.cleanarch.presentation.v0.response;

import pro.gsilva.courses.cleanarch.domain.person.Person;

import java.util.List;

public record PersonResponseList(
        List<PersonResponse> persons
) {

    public static PersonResponseList of(List<Person> persons) {
        return new PersonResponseList(
                persons.stream()
                        .map(PersonResponse::of)
                        .toList()
        );
    }
}
