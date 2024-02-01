package com.system.medical.mhs.acessos.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EntityScan("com.system.medical.mhs.dados.mySql.entities")
@ComponentScan(
        {
        	"com.system.medical.mhs.dados.mySql",
            "com.system.medical.mhs.dados.mySql.repositories",
            "com.system.medical.mhs.dados.mySql.repositories.app",
            "com.system.medical.mhs.dados.mySql.repositories.pwa",
            "com.system.medical.mhs.dados.mySql.pacientes",
            "com.system.medical.mhs.acessos.api.app",
            "com.system.medical.mhs.acessos.api.pwa",
            "com.system.medical.mhs.acessos.api.pacientes.pwa",
            
            "com.system.medical.mhs.acessos.api"
    	})
public class Aplicacao {

    public static void main(String[] args) {
        SpringApplication.run(Aplicacao.class, args);
    }
	
    //Permitir acesso cors do pwa
    //Enable Global CORS support for the application
    @Bean
    public WebMvcConfigurerAdapter corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost:5555")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                        .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers") //What is this for?
                        .allowCredentials(true);
            }
        };
    }
}
