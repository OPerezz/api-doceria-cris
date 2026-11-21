package com.doceria.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI doceriaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Doceria Artesanal API")
                        .description("API para gerenciamento de produtos, categorias e avaliações")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Doceria Artesanal")
                                .email("contato@doceria.com.br")
                                .url("https://doceria.com.br"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .tags(Arrays.asList(
                        new Tag().name("Categorias").description("Operações relacionadas a categorias"),
                        new Tag().name("Produtos").description("Operações relacionadas a produtos"),
                        new Tag().name("Avaliações").description("Operações relacionadas a avaliações")
                ));
    }
}