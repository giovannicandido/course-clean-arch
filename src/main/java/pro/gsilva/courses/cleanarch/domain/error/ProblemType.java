package pro.gsilva.courses.cleanarch.domain.error;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

public interface ProblemType {

    int INTERNAL_SERVER_ERROR = 500;
    int NOT_FOUND = 404;

    URI type();

    String title();

    default int status() {
        return INTERNAL_SERVER_ERROR;
    }

    default String detail() {
        return null;
    }

    default URI instance() {
        return null;
    }

    default Map<String, Object> properties() {
        return Collections.emptyMap();
    }
}
