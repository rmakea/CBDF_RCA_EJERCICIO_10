package com.upiiz.superheroes.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Heroes",
                description = "Esta API proporciona acceso a diferentes tipos de SuperHeroes",
                version = "1.0.0",
                contact = @Contact(
                        name = "Raul Cardoso Acevedo",
                        email = "rcardosoa2100@alumno.ipn.mx",
                        url = "http://localhost:8080/heroes"
                ),
                license = @License(),
                termsOfService = "Solo se permiten 400 solicitudes"
        ),
        servers = {
                @Server(
                        description = "Servidor de pruebas",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Servidor de Produccion",
                        url = "https://cbdf-rca-ejercicio-10.onrender.com"
                )
        },
        tags = {
                @Tag(
                        name = "Heroes",
                        description = "API DE SUPERHEROES"
                )
        }
)
public class OpenApiConfiguration {
}
