package com.samoylov.server.controller;

import com.samoylov.dto.CardDTO;
import com.samoylov.dto.CustomerDTO;
import com.samoylov.server.security.JwtTokenProvider;
import com.samoylov.server.service.AccountService;
import com.samoylov.server.service.CardService;
import com.samoylov.server.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class HostRestController {
    private CustomerService customerService;
    private AccountService accountService;
    private JwtTokenProvider jwtTokenProvider;
    private CardService cardService;

    @GetMapping("/customers")
    List<CustomerDTO> getCustomerInfo() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer")
    CustomerDTO getCustomer(HttpServletRequest request){
        String token = request.getHeader("authorization").substring(7);
        String cardNumber = jwtTokenProvider.getUsername(token);
        CustomerDTO customerDTO = customerService.getCustomerByCard(cardNumber);
        return customerDTO;
    }

    @GetMapping("/customer/account/balance")
    BigDecimal getCustomerBalance(HttpServletRequest request){
        String token = request.getHeader("authorization").substring(7);
        String cardNumber = jwtTokenProvider.getUsername(token);
        CardDTO cardDTO = cardService.getCardByNumber(cardNumber);
        return accountService.getBalanceByCard(cardDTO.getCardNumber());
    }
}
