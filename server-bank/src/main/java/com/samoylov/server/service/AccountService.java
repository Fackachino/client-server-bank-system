package com.samoylov.server.service;

import com.samoylov.server.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;

    public Optional<Long> getBalanceByCard(long cardNumber, int pin) {
        return accountRepository.getBalanceByCard(cardNumber, pin);
    }
}
