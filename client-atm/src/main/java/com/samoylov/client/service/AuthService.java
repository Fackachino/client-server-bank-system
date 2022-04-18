package com.samoylov.client.service;

import com.samoylov.dto.ClientAuthRequest;
import com.samoylov.dto.ServerAuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AuthService {
    private RestTemplate restTemplate;
    private AtmContext atmContext;
    private final String LOGIN = "/auth/login";

    public String authentication(ClientAuthRequest clientAuthRequest) {
        ResponseEntity<ServerAuthResponse> responseEntity = restTemplate.postForEntity(
                atmContext.getSERVER_PATH() + LOGIN,
                clientAuthRequest,
                ServerAuthResponse.class);

        atmContext.setJwtToken(responseEntity.getBody().getJwtToken());
        return "You logged in";
    }

    public String logout(){
        atmContext.setJwtToken("");
        return "You logged out";
    }

}
