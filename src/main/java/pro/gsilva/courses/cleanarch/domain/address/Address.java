package pro.gsilva.courses.cleanarch.domain.address;

import lombok.Builder;
import pro.gsilva.courses.cleanarch.domain.DomainId;
import pro.gsilva.courses.cleanarch.domain.DomainWithID;

@Builder(toBuilder = true)
public record Address(AddressId addressId, CountryCode countryCode, String state, String city, String neighborhood, String street,
                      ZipCode zipCode, Integer number) implements DomainWithID<Long> {
    @Override
    public DomainId<Long> getDomainId() {
        return addressId;
    }
}
