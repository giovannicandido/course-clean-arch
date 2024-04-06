package pro.gsilva.courses.cleanarch.domain;

public interface DomainWithID<ID> {
    DomainId<ID> getDomainId();
}
