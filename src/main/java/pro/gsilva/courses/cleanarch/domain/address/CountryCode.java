package pro.gsilva.courses.cleanarch.domain.address;

import lombok.Getter;
import pro.gsilva.courses.cleanarch.domain.error.IllegalCodeException;

@Getter
public enum CountryCode {
    US("United States of America"),
    UK("United Kingdom"),
    BR("Brazil");

    private final String countryName;

    CountryCode(String countryName) {
        this.countryName = countryName;
    }

    public static CountryCode of(String countryCode) {
        try {
            return CountryCode.valueOf(countryCode.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalCodeException(countryCode, "Country");
        }
    }
}
