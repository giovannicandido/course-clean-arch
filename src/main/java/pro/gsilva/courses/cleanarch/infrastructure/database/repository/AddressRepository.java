package pro.gsilva.courses.cleanarch.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.gsilva.courses.cleanarch.infrastructure.database.entity.AddressEntity;
import pro.gsilva.courses.cleanarch.infrastructure.database.projections.PersonAddressId;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    PersonAddressId findFirstByPerson_Id(Long personId);
}
