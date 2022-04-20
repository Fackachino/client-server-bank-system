package com.samoylov.server.controller;

import com.samoylov.dto.CardDTO;
import com.samoylov.dto.ClinetMoneyDTO;
import com.samoylov.dto.CustomerDTO;
import com.samoylov.server.security.JwtTokenProvider;
import com.samoylov.server.service.AccountService;
import com.samoylov.server.service.CardService;
import com.samoylov.server.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
        String cardNumber = getCardNumber(request);
        CustomerDTO customerDTO = customerService.getCustomerByCard(cardNumber);
        return customerDTO;
    }

    @GetMapping("/customer/account/balance")
    BigDecimal getCustomerBalance(HttpServletRequest request){
        String cardNumber = getCardNumber(request);
        CardDTO cardDTO = cardService.getCardByNumber(cardNumber);
        return accountService.getBalanceByCard(cardDTO.getCardNumber());
    }

    @PostMapping("/customer/account/balance/deposit")
    BigDecimal depositMoney(@RequestBody ClinetMoneyDTO moneyRequest, HttpServletRequest request){
        String cardNumber = getCardNumber(request);
        accountService.depositMoney(cardNumber, moneyRequest.getMoney());
        return accountService.getBalanceByCard(cardNumber);
    }

    @PostMapping("/customer/account/balance/withdraw")
    BigDecimal withdrawMoney(@RequestBody ClinetMoneyDTO moneyRequest, HttpServletRequest request){
        String cardNumber = getCardNumber(request);
        accountService.withDrawMoney(cardNumber, moneyRequest.getMoney());
        return accountService.getBalanceByCard(cardNumber);
    }

    private String getCardNumber(HttpServletRequest request){
        String token = request.getHeader("authorization").substring(7);
        return jwtTokenProvider.getUsername(token);
    }
}
