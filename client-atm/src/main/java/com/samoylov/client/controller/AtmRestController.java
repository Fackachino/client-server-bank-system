package com.samoylov.client.controller;

import com.samoylov.client.service.ATMService;
import com.samoylov.dto.CardDTO;
import com.samoylov.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AtmRestController {
    private ATMService atmService;

    @PostMapping("customer/info")
    public CustomerDTO getCustomerInfo(@RequestBody CardDTO cardDTO){
        return atmService.getCustomerByCard(cardDTO);
    }

    @PostMapping("customer/account/balance")
    public Long getAccountBalanceByCard(@RequestBody CardDTO cardDTO){
        return atmService.getAccountBalanceByCard(cardDTO);
    }

}
