package pro.gsilva.courses.cleanarch.presentation.v0.request;


import jakarta.validation.constraints.NotBlank;
import pro.gsilva.courses.cleanarch.domain.address.Address;
import pro.gsilva.courses.cleanarch.domain.address.CountryCode;
import pro.gsilva.courses.cleanarch.domain.address.ZipCode;

public record AddressRequest(
        @NotBlank
        String street,
        @NotBlank
        String city,
        @NotBlank
        String state,
        @NotBlank
        String neighborhood,
        @NotBlank
        String country,
        String zipCode,
        Integer number
) {

    public Address toDomain() {
        return Address.builder()
                .countryCode(CountryCode.of(country))
                .state(state)
                .city(city)
                .neighborhood(neighborhood)
                .street(street)
                .zipCode(ZipCode.of(zipCode))
                .number(number)
                .build();
    }
}
