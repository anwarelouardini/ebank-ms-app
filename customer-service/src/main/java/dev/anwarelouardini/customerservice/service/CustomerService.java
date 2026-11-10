package dev.anwarelouardini.customerservice.service;

import dev.anwarelouardini.customerservice.entities.Customer;
import dev.anwarelouardini.customerservice.repository.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @McpTool(description = "Get all customers")
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @McpTool(description = "Find a customer by id")
    public Customer findById(@McpToolParam(description = "The customer id") Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
    }

    @McpTool(description = "Save a new customer")
    public Customer save(@McpToolParam(description = "The Customer to save (name, email)") Customer customer) {
        return customerRepository.save(customer);
    }
}
