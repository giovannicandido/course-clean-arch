package pro.gsilva.courses.cleanarch.domain.error;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    private final ProblemType problemType;

    public DomainException(ProblemType problemType) {
        this.problemType = problemType;
    }
}
