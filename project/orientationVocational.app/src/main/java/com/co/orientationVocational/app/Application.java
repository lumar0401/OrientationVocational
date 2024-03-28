package com.co.orientationVocational.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.co.orientationVocational.app.services.implementation.fileService;

@SpringBootApplication
@ComponentScan(basePackages = "com.co.orientationVocational.app")
public class Application implements CommandLineRunner {
	@Autowired
	fileService fileservice;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*")
                .allowedHeaders("*");
            }
        };
    }
	
	@Override
	public void run(String... args) throws Exception {
		fileservice.deleteAll();
		fileservice.init();
	}
}