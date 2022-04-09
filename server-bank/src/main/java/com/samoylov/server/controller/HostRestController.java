package com.samoylov.server.controller;

import com.samoylov.dto.CardDTO;
import com.samoylov.dto.CustomerDTO;
import com.samoylov.server.service.AccountService;
import com.samoylov.server.service.CardService;
import com.samoylov.server.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class HostRestController {
    private CustomerService customerService;
    private AccountService accountService;
    private CardService cardService;

    @GetMapping("customers")
    List<CustomerDTO> getCustomerInfo() {
        return customerService.getAllCustomers();
    }

    @PostMapping("customer/account/balance")
    BigDecimal getCustomerBalance(@RequestBody CardDTO cardDTO) {
        return accountService.getBalanceByCard(cardDTO.getCard_number(), cardDTO.getPin());

    }

    @PostMapping("customer/info")
    CustomerDTO getCustomerByCard(@RequestBody CardDTO cardDTO) {
        return customerService.getCustomerByCard(cardDTO.getCard_number(), cardDTO.getPin());
    }
}
