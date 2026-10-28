package dev.anwarelouardini.customerservice.controllers;

import dev.anwarelouardini.customerservice.entities.Customer;
import dev.anwarelouardini.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRestController {
    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    List<Customer> findAllCustomer() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{id}")
    Customer findCustomerById(@PathVariable Long id){
        return customerService.findById(id);
    }

    @PostMapping("/customers")
    Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

}
