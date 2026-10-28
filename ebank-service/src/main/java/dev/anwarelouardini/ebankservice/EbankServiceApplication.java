package dev.anwarelouardini.ebankservice;

import dev.anwarelouardini.ebankservice.entities.BankAccount;
import dev.anwarelouardini.ebankservice.service.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(BankAccountService bankAccountService) {
        return args -> {
            for (int i = 1; i <= 3; i++) {
                for (int j = 0; j < 5; j++) {
                    bankAccountService.save(BankAccount.builder()
                                    .type(Math.random() > .5 ? "CURRENT-ACCOUNT" : "SAVING-ACCOUNT")
                                    .balance(100 + Math.random() * 60000)
                                    .customerId((long) i)
                            .build());
                }
            }
        };

    }

}
