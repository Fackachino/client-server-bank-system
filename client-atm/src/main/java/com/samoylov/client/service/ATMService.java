package com.samoylov.client.service;

import com.samoylov.client.exception.LoginClientException;
import com.samoylov.dto.ClinetMoneyDTO;
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
    private final String ACCOUNT_BALANCE = "/customer/account/balance";
    private final String DEPOSIT_BALANCE = "/customer/account/balance/deposit";
    private final String WITHDRAW_BALANCE = "/customer/account/balance/withdraw";
    private AtmContext atmContext;

    public BigDecimal getBalance() {
        HttpHeaders httpHeaders = getHeaderWithToken();

        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        ResponseEntity<BigDecimal> responseEntity = restTemplate.exchange(atmContext.getSERVER_PATH() + ACCOUNT_BALANCE, HttpMethod.GET, entity, BigDecimal.class);

        return responseEntity.getBody();
    }

    public CustomerDTO getCustomer() {
        HttpHeaders httpHeaders = getHeaderWithToken();

        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        ResponseEntity<CustomerDTO> responseEntity = restTemplate.exchange(atmContext.getSERVER_PATH() + CUSTOMER, HttpMethod.GET, entity, CustomerDTO.class);

        return responseEntity.getBody();
    }

    public BigDecimal depositMoney(ClinetMoneyDTO moneyDTO){
        HttpHeaders httpHeaders = getHeaderWithToken();

        HttpEntity<ClinetMoneyDTO> entity = new HttpEntity<>(moneyDTO, httpHeaders);
        ResponseEntity<BigDecimal> responseEntity = restTemplate.postForEntity(atmContext.getSERVER_PATH()+DEPOSIT_BALANCE, entity, BigDecimal.class);

        return responseEntity.getBody();
    }

    public BigDecimal withDraw(ClinetMoneyDTO moneyDTO){
        HttpHeaders httpHeaders = getHeaderWithToken();

        HttpEntity<ClinetMoneyDTO> entity = new HttpEntity<>(moneyDTO, httpHeaders);
        ResponseEntity<BigDecimal> responseEntity = restTemplate.postForEntity(atmContext.getSERVER_PATH()+WITHDRAW_BALANCE, entity, BigDecimal.class);

        return responseEntity.getBody();
    }

    private HttpHeaders getHeaderWithToken(){
        String token = atmContext.getJwtToken();

        if (token.equals("")) {
            throw new LoginClientException("Token is empty");
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer_" + token);

        return httpHeaders;
    }
}