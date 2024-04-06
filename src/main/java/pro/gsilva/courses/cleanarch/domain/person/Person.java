package pro.gsilva.courses.cleanarch.domain.person;

import lombok.Builder;
import lombok.Value;
import pro.gsilva.courses.cleanarch.domain.DomainId;
import pro.gsilva.courses.cleanarch.domain.DomainWithID;
import pro.gsilva.courses.cleanarch.domain.address.Address;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Value
@Builder(toBuilder = true)
public class Person implements DomainWithID<Long> {

    private static final Map<String, Integer> LEGAL_AGE_IN_COUNTRIES = Map.of(
            Locale.US.getCountry(),
            21,
            Locale.UK.getCountry(),
            21,
            "BR",
            18
    );
    private PersonId personId;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;

    private Address address;

    private Integer getLegalAgeInCountry(Locale.IsoCountryCode countryCode) {
        return LEGAL_AGE_IN_COUNTRIES.get(countryCode) == null ? 0 : LEGAL_AGE_IN_COUNTRIES.get(countryCode);
    }

    public int ageInYears() {
        return (int) ChronoUnit.YEARS.between(birthDay, LocalDateTime.now());
    }

    public boolean canSignIn(Locale.IsoCountryCode countryCode) {
        return ageInYears() >= getLegalAgeInCountry(countryCode) && Objects.equals(address.countryCode(), countryCode.name());
    }

    @Override
    public DomainId<Long> getDomainId() {
        return personId;
    }
}
