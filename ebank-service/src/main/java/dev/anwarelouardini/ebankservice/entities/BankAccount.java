package dev.anwarelouardini.ebankservice.entities;

import dev.anwarelouardini.ebankservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @Builder
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private double balance;
    private String type;
    private Long customerId;
    @Transient
    private Customer customer;
}