package pro.gsilva.courses.cleanarch.presentation.v0.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pro.gsilva.courses.cleanarch.domain.person.Person;
import pro.gsilva.courses.cleanarch.domain.person.PersonId;

import java.time.LocalDate;

public record PersonRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotNull
        LocalDate birthDate,
        @NotNull
        @Valid
        AddressRequest address
) {
    public Person toDomain() {
        return toDomain(null);
    }
    public Person toDomain(Long id) {
        return Person.builder()
                .personId(PersonId.of(id))
                .firstName(firstName)
                .lastName(lastName)
                .birthDay(birthDate)
                .address(address.toDomain())
                .build();
    }
}
