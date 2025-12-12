package com.ucb.plataforma.res.reviews.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {
                // Esta URL debería coincidir con el prefijo que le pongas en el Gateway
                @Server(
                        url = "/ms-res",
                        description = "Ruta del Gateway para el microservicio de Reseñas"
                )
        }
)
public class OpenApiConfig {

    @Value("${api.common.version:1.0.0}")
    String apiVersion;

    @Value("${api.common.title:API de Reseñas}")
    String apiTitle;

    @Value("${api.common.description:API para gestionar reseñas de cursos en la plataforma}")
    String apiDescription;

    @Value("${api.common.termsOfService:http://ucb.edu.bo/terminos}")
    String apiTermsOfService;

    @Value("${api.common.license:Apache 2.0}")
    String apiLicense;

    @Value("${api.common.licenseUrl:http://www.apache.org/licenses/LICENSE-2.0}")
    String apiLicenseUrl;

    @Value("${api.common.externalDocDesc:Documentación adicional}")
    String apiExternalDocDesc;

    @Value("${api.common.externalDocUrl:http://ucb.edu.bo/docs}")
    String apiExternalDocUrl;

    @Value("${api.common.contact.name:Soporte Plataforma Reseñas}")
    String apiContactName;

    @Value("${api.common.contact.url:http://ucb.edu.bo}")
    String apiContactUrl;

    @Value("${api.common.contact.email:soporte-resenas@ucb.edu.bo}")
    String apiContactEmail;

    /**
     * Se expone normalmente en:
     *   http://HOST:PUERTO/swagger-ui.html   (según tu configuración de springdoc)
     */
    @Bean
    public OpenAPI getOpenApiDocumentation() {
        return new OpenAPI()
                .info(new Info()
                        .title(apiTitle)
                        .description(apiDescription)
                        .version(apiVersion)
                        .contact(new Contact()
                                .name(apiContactName)
                                .url(apiContactUrl)
                                .email(apiContactEmail))
                        .termsOfService(apiTermsOfService)
                        .license(new License()
                                .name(apiLicense)
                                .url(apiLicenseUrl)))
                .externalDocs(new ExternalDocumentation()
                        .description(apiExternalDocDesc)
                        .url(apiExternalDocUrl));
    }
}
