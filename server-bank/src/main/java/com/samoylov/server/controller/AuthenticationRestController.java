package com.samoylov.server.controller;

import com.samoylov.dto.ClientAuthRequest;
import com.samoylov.dto.ServerAuthResponse;
import com.samoylov.server.exception.WrongPinException;
import com.samoylov.server.security.JwtTokenProvider;
import com.samoylov.server.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@AllArgsConstructor
public class AuthenticationRestController {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private CardService cardService;

    @PostMapping("/login")
    public ServerAuthResponse login(@RequestBody ClientAuthRequest clientAuthRequest) {
        try {
            String cardNumber = cardService.getCardByNumber(clientAuthRequest.getCardNumber()).getCardNumber();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cardNumber, clientAuthRequest.getPin()));
            String token = jwtTokenProvider.createToken(cardNumber);
            return new ServerAuthResponse(cardNumber, token);
        } catch (AuthenticationException e) {
            throw new WrongPinException("Invalid password");
        }
    }
}
