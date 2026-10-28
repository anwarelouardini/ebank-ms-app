package dev.anwarelouardini.customerservice;

import dev.anwarelouardini.customerservice.entities.Customer;
import dev.anwarelouardini.customerservice.repository.CustomerRepository;
import dev.anwarelouardini.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CustomerService customerService) {
        return args -> {
            List<String> names = List.of("Ahmed", "Ali", "Mohammed");
            names.forEach(name -> {
                customerService.save(Customer.builder()
                                .name(name)
                                .email(name.toLowerCase() + "@mail.com")
                        .build());
            });
        };
    }

}
