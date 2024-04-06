package pro.gsilva.courses.cleanarch.presentation.config;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class DefaultErrorAttributes {

    public Map<String, Object> getErrorAttributes() {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "messageID", UUID.randomUUID()
        );
    }
}
