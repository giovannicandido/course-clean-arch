package pro.gsilva.courses.cleanarch.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pro.gsilva.courses.cleanarch.domain.address.Address;
import pro.gsilva.courses.cleanarch.domain.address.AddressId;
import pro.gsilva.courses.cleanarch.domain.address.CountryCode;
import pro.gsilva.courses.cleanarch.domain.address.ZipCode;

import static pro.gsilva.courses.cleanarch.domain.functions.NullFunctions.unwrapID;


@Entity
@Table(name = "address")
@SequenceGenerator(name = "address_seq", sequenceName = "address_seq", initialValue = 1, allocationSize = 1)
@Getter
@Setter
@ToString(exclude = "person")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    private Long id;
    @Column(length = 4, nullable = false)
    private String countryCode;
    @Column(length = 30, nullable = false)
    private String state;
    @Column(length = 45, nullable = false)
    private String city;
    @Column(length = 45, nullable = false)
    private String neighborhood;
    @Column(length = 50, nullable = false)
    private String street;
    @Column(length = 10)
    private String zipCode;
    private Integer number;

    @OneToOne
    @JoinColumn(nullable = false, name = "person_id")
    private PersonEntity person;


    public static AddressEntity fromDomain(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(unwrapID(address));
        addressEntity.setCountryCode(address.countryCode().name());
        addressEntity.setState(address.state());
        addressEntity.setCity(address.city());
        addressEntity.setNeighborhood(address.neighborhood());
        addressEntity.setStreet(address.street());
        addressEntity.setZipCode(address.zipCode().value());
        addressEntity.setNumber(address.number());
        return addressEntity;
    }
    public Address toDomain() {
        CountryCode code = CountryCode.valueOf(countryCode);
        ZipCode zip = new ZipCode(zipCode);
        return new Address(AddressId.of(id), code, state, city, neighborhood, street, zip, number);
    }

}
