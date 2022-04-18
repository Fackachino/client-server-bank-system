package com.samoylov.client.controller;

import com.samoylov.client.service.ATMService;
import com.samoylov.client.service.AuthService;
import com.samoylov.dto.ClientAuthRequest;
import com.samoylov.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class AtmRestController {
    private ATMService atmService;
    private AuthService authService;

    @GetMapping("/customer")
    public CustomerDTO getCustomer() {
        return atmService.getCustomer();
    }

    @GetMapping("/logout")
    public String logout() {
        return authService.logout();
    }

    @GetMapping("/customer/account/balance")
    public BigDecimal getBalance() {
        return atmService.getBalance();
    }

    @PostMapping("/login")
    public String login(@RequestBody ClientAuthRequest clientAuthRequest) {
        return authService.authentication(clientAuthRequest);
    }
}
