package pro.gsilva.courses.cleanarch.domain.error;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.net.URI;
import java.util.Map;

@Value
public class IllegalCodeException extends DomainException {
    private final String code;
    private final String domain;

    public IllegalCodeException(String code, String domain) {
        super(new IllegalCodeProblemType(code, domain));
        this.code = code;
        this.domain = domain;
    }

    @RequiredArgsConstructor
    public static class IllegalCodeProblemType implements ProblemType {
        private final String code;
        private final String domain;

        @Override
        public URI type() {
            return URI.create("/problem-details/illegal-code");
        }

        @Override
        public String title() {
            return "The code is unknown";
        }

        @Override
        public String detail() {
            return String.format("The code %s is not present in the type %s", code, domain);
        }
        @Override
        public Map<String, Object> properties() {
            return Map.of("code", code, "domain", domain);
        }
    }
}
