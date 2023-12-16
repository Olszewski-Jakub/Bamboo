package live.olszewski.bamboo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * This class is used to configure the OpenAPI documentation for the Bamboo API.
 * It uses annotations to define the API's information, servers, and security schemes.
 */
@OpenAPIDefinition(
        // The basic information about the API
        info = @Info(
                title = "Bamboo API",
                version = "1.0",
                description = "Documentation Bamboo API v1.0",
                contact = @Contact(
                        name = "Jakub",
                        email = "j.olszewski05@gmail.com"
                )

        ),
        // The servers where the API is hosted
        servers = {
                @Server(
                        description = "Local environment",
                        url = "http://localhost:8080"
                )
        },
        // The security requirements for the API
        security = {
                @SecurityRequirement(name = "bearerAuth"),
                @SecurityRequirement(name = "apiKey")
        }
)
// The JWT Token security scheme
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
// The API Key security scheme
@SecurityScheme(
        name = "apiKey",
        description = "API Key",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        paramName = "Authorization",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}