package pro.gsilva.courses.cleanarch.presentation.v0.response.error;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import pro.gsilva.courses.cleanarch.domain.error.DomainException;
import pro.gsilva.courses.cleanarch.domain.error.ProblemType;
import pro.gsilva.courses.cleanarch.presentation.config.DefaultErrorAttributes;

import java.util.HashMap;

@RequiredArgsConstructor
@Component
public class ProblemDetailFactory {

    private final DefaultErrorAttributes defaultErrorAttributes;

    public ProblemDetail build(final ProblemType problem) {
        var problemDetail = ProblemDetail.forStatus(problem.status());
        problemDetail.setType(problem.type());
        problemDetail.setInstance(problem.instance());
        problemDetail.setDetail(problem.detail());
        problemDetail.setProperties(problem.properties());
        problemDetail.setTitle(problem.title());

        var properties = problem.properties();

        properties = properties == null ? new HashMap<>() : new HashMap<>(properties);
        properties.putAll(defaultErrorAttributes.getErrorAttributes());

        problemDetail.setProperties(properties);
        return problemDetail;
    }

    public ProblemDetail build(final DomainException domainException) {
        return build(domainException.getProblemType());
    }
}
