package pro.gsilva.courses.cleanarch.domain.person;

import pro.gsilva.courses.cleanarch.domain.DomainId;

public record PersonId(Long id) implements DomainId<Long> {

    public static PersonId of(Long id) {
        return new PersonId(id);
    }

    @Override
    public Long getId() {
        return id;
    }
}
