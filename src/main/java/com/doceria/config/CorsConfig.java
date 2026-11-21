package com.doceria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // PERMITE TODAS AS ORIGENS (para desenvolvimento)
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        
        // PERMITE TODOS OS MÉTODOS
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        
        // PERMITE TODOS OS HEADERS
        config.setAllowedHeaders(Arrays.asList("*"));
        
        // PERMITE ENVIAR CREDENCIAIS (cookies, autenticação)
        config.setAllowCredentials(true);
        
        // TEMPO DE CACHE DA CONFIGURAÇÃO CORS (em segundos)
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}