package pro.gsilva.courses.cleanarch.domain.address;

public record ZipCode(String value) {
    public static ZipCode of(String zipCode) {
        return new ZipCode(zipCode);
    }
}
