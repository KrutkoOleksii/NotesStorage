package com.example.notesStorage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("MvcConfig");
//        registry.addStatusController("/error", HttpStatus.CONFLICT);
		registry.addViewController("/login").setViewName("login");
	}

}
