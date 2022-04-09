package com.samoylov.client.service;

import com.samoylov.dto.CardDTO;
import com.samoylov.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


@Service
@AllArgsConstructor
public class ATMService {
    private RestTemplate restTemplate;
    private final String CUSTOMER_INFO = "http://localhost:8081/customer/info";
    private final String ACCOUNT_BALANCE = "http://localhost:8081/customer/account/balance";

    public CustomerDTO getCustomerByCard(CardDTO cardDTO) {
        ResponseEntity<CustomerDTO> responseEntity = restTemplate.postForEntity(CUSTOMER_INFO, cardDTO, CustomerDTO.class);
        return responseEntity.getBody();
    }


    public BigDecimal getAccountBalanceByCard(CardDTO cardDTO) {
        ResponseEntity<BigDecimal> responseEntity = restTemplate.postForEntity(ACCOUNT_BALANCE, cardDTO, BigDecimal.class);
        return responseEntity.getBody();
    }

}
