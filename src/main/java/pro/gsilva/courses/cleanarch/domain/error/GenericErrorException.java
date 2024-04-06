package pro.gsilva.courses.cleanarch.domain.error;

import java.net.URI;

public class GenericErrorException extends DomainException {

    public GenericErrorException() {
        super(new GenericErrorExceptionProblemType());
    }

    public static class GenericErrorExceptionProblemType implements ProblemType {

        @Override
        public URI type() {
            return URI.create("/problem-details/unknown-error");
        }

        @Override
        public String title() {
            return "An unknown error occurred";
        }

        @Override
        public String detail() {
            return "An unknown error occurred while processing your request. This error will be logged and investigated by your team";
        }
    }
}
