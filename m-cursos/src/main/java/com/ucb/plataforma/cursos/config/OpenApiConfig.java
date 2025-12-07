package com.ucb.plataforma.cursos.config;

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
                // ️ Esta URL debe coincidir con el prefijo de ruta configurado en el Gateway
                @Server(url = "/m-cursos", description = "Ruta del Gateway para el microservicio de Cursos")
        }
)

public class OpenApiConfig {


        @Value("${api.common.version:2.3.0}")
        String apiVersion;

        @Value("${api.common.title:API de Cursos}")
        String apiTitle;

        @Value("${api.common.description:API para gestionar los cursos en la plataforma educativa}")
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

        @Value("${api.common.contact.name:Soporte Plataforma Cursos}")
        String apiContactName;

        @Value("${api.common.contact.url:http://ucb.edu.bo}")
        String apiContactUrl;

        @Value("${api.common.contact.email:soporte@ucb.edu.bo}")
        String apiContactEmail;

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