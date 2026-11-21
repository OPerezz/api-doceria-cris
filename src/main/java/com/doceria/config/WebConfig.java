package com.doceria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.doceria.interceptor.ApiKeyInterceptor;
 
 
@Configuration
public class WebConfig implements WebMvcConfigurer {
	//registrando o interceptador
    @Autowired
    private ApiKeyInterceptor apiKeyInterceptor;
 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiKeyInterceptor)
                .addPathPatterns("/**") // Aplica a todas as rotas /produtos **
              .excludePathPatterns("/**/public/**");
               
}
    
}
