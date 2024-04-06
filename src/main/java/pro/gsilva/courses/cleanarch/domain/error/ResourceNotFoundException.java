package pro.gsilva.courses.cleanarch.domain.error;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.function.Supplier;

public class ResourceNotFoundException extends DomainException {

    public static Supplier<ResourceNotFoundException> ofId(String resourceId) {
        return () -> new ResourceNotFoundException(resourceId);
    }

    public ResourceNotFoundException(String resourceId) {
        super(new ResourceNotFoundProblem(resourceId));
    }

    @RequiredArgsConstructor
    public static class ResourceNotFoundProblem implements ProblemType  {
        private final String resource;

        @Override
        public URI type() {
            return URI.create("/problem-details/resource-not-found");
        }

        @Override
        public int status() {
            return ProblemType.NOT_FOUND;
        }

        @Override
        public String detail() {
            return "The resource id %s could not be found".formatted(resource);
        }

        @Override
        public String title() {
            return "The resource id could not be found";
        }
    }
}
