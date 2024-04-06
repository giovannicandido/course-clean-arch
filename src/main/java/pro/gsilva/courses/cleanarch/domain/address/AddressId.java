package pro.gsilva.courses.cleanarch.domain.address;

import pro.gsilva.courses.cleanarch.domain.DomainId;

public record AddressId(Long id) implements DomainId<Long> {

    public static AddressId of(Long id) {
        return new AddressId(id);
    }

    @Override
    public Long getId() {
        return id;
    }

}
