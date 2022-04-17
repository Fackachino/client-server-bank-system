package com.samoylov.server.controller;

import com.samoylov.dto.CardDTO;
import com.samoylov.server.exception.WrongPinException;
import com.samoylov.server.security.JwtTokenProvider;
import com.samoylov.server.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
@AllArgsConstructor
public class AuthenticationRestController {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private CardService cardService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody CardDTO cardDTO) {
        try {
            String cardNumber = cardService.getCardByNumber(cardDTO.getCardNumber()).getCardNumber();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cardNumber, cardDTO.getPin()));
            String token = jwtTokenProvider.createToken(cardNumber);

            Map<String, String> response = new HashMap<>();
            response.put("cardNumber", cardNumber);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new WrongPinException("Invalid password");
        }
    }
}
