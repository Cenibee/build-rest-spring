package com.cenibee.learn.springrest.system;

import com.cenibee.learn.springrest.employee.Employee;
import com.cenibee.learn.springrest.employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(Employee.builder()
                    .name("Bilbo Baggins")
                    .role("burglar")
                    .build()));
            log.info("Preloading " + repository.save(Employee.builder()
                    .name("Frodo Baggins")
                    .role("thief")
                    .build()));
        };
    }
}
