package dev.anwarelouardini.ebankservice.controllers;

import dev.anwarelouardini.ebankservice.entities.BankAccount;
import dev.anwarelouardini.ebankservice.service.BankAccountService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankAccountRestController {
    private BankAccountService bankAccountService;

    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/accounts")
    public List<BankAccount> findAllBankAccounts() {
        return bankAccountService.findAll();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccountById(@PathVariable String id) {
        return bankAccountService.findById(id);
    }

    @PostMapping("/accounts")
    public BankAccount saveBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.save(bankAccount);
    }

}
