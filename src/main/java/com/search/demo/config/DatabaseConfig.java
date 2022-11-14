package com.search.demo.config;

import com.search.demo.model.User;
import com.search.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class DatabaseConfig {

    /**
     * Set up user repository with predefined entries
     * @param userRepository UserRepository object
     * @return CommandLineRunner
     */
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return string -> {
            userRepository.save(new User(1, "pmburlacu", "Petru-Marian", "Burlacu"));
            userRepository.save(new User(2, "pburla", "Peter", "Burla"));
            userRepository.save(new User(3, "bachpem", "Bach", "Pembe"));
        };
    }
}
