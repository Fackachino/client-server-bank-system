package com.samoylov.server.controller;
import com.samoylov.dto.CardDTO;
import com.samoylov.dto.CustomerDTO;
import com.samoylov.server.service.AccountService;
import com.samoylov.server.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@AllArgsConstructor
public class HostRestController {
    private CustomerService customerService;
    private AccountService accountService;

    @GetMapping("customers")
    List<CustomerDTO> getCustomerInfo(){
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        if (customerDTOS.size() == 0)
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Customers");
        else return customerDTOS;
    }

    @PostMapping("customer/account/balance")
    Long getCustomerBalance(@RequestBody CardDTO cardDTO){
        return accountService.getBalanceByCard(cardDTO.getCard_number(), cardDTO.getPin())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "Something went Wrong"));
    }

    @PostMapping("customer/info")
    CustomerDTO getCustomerByCard(@RequestBody CardDTO cardDTO){
        return customerService.getCustomerByCard(cardDTO.getCard_number(), cardDTO.getPin())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "Something went Wrong"
                ));
    }
}
