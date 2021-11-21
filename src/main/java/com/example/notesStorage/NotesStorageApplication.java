package com.example.notesStorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ConfigurationPropertiesBinding
public class NotesStorageApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(NotesStorageApplication.class, args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(NotesStorageApplication.class);

    }
}
