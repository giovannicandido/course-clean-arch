package pro.gsilva.courses.cleanarch.presentation.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {@Server(url = "/")},
        info = @Info(
                title = "Clean Architecture Course Example",
                version = "1.0.0-SNAPSHOT",
                description = "API designed to example a optioned clean architecture with spring boot",
                contact = @Contact(
                        name = "Giovanni Silva",
                        email = "contact@gsilva.pro",
                        url = "https://blog.gsilva.pro/clean-architecture"
                )
        )
)
public class SwaggerConfig {
}
