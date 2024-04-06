package pro.gsilva.courses.cleanarch.presentation.v0.response;

import pro.gsilva.courses.cleanarch.domain.address.Address;
import pro.gsilva.courses.cleanarch.domain.address.CountryCode;
import pro.gsilva.courses.cleanarch.domain.address.ZipCode;

import static pro.gsilva.courses.cleanarch.domain.functions.NullFunctions.unwrapID;

public record AddressResponse(
        Long id, CountryCode countryCode, String state, String city, String neighborhood, String street,
        ZipCode zipCode, Integer number
) {
    public static AddressResponse of(Address address) {
        return new AddressResponse(
                unwrapID(address),
                address.countryCode(),
                address.state(),
                address.city(),
                address.neighborhood(),
                address.street(),
                address.zipCode(),
                address.number()
        );
    }
}
