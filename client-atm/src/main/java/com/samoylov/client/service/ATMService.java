package com.samoylov.client.service;

import com.samoylov.client.exception.LoginClientException;
import com.samoylov.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ATMService {
    private RestTemplate restTemplate;
    private final String CUSTOMER = "/customer";
    private final String ACCOUNT_BALANCE = "customer/account/balance";
    private AtmContext atmContext;

    public BigDecimal getBalance() {
        String token = atmContext.getJwtToken();

        if (token.equals("")) {
            throw new LoginClientException("Token is empty");
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer_" + token);

        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        ResponseEntity<BigDecimal> responseEntity = restTemplate.exchange(atmContext.getSERVER_PATH() + ACCOUNT_BALANCE, HttpMethod.GET, entity, BigDecimal.class);

        return responseEntity.getBody();
    }

    public CustomerDTO getCustomer() {
        String token = atmContext.getJwtToken();

        if (token.equals("")) {
            throw new LoginClientException("Token is empty");
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer_" + token);

        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        ResponseEntity<CustomerDTO> responseEntity = restTemplate.exchange(atmContext.getSERVER_PATH() + CUSTOMER, HttpMethod.GET, entity, CustomerDTO.class);

        return responseEntity.getBody();
    }
}