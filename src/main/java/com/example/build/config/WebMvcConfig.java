package com.example.build.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                // .allowCredentials(true)
                // .allowedMethods("PUT", "DELETE", "POST", "GET")
                // .allowedOrigins("http://localhost:8080/**")
                .exposedHeaders("*");
    }

}
