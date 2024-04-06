package pro.gsilva.courses.cleanarch.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.gsilva.courses.cleanarch.infrastructure.database.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
