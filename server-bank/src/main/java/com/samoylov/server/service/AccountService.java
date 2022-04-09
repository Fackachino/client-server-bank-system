package com.samoylov.server.service;

import com.samoylov.dto.AccountDTO;
import com.samoylov.dto.CardDTO;
import com.samoylov.server.exception.AccountNotFoundException;
import com.samoylov.server.repository.AccountRepository;
import com.samoylov.server.service.utility.AccountEntityConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;
    private CardService cardService;

    public BigDecimal getBalanceByCard(long cardNumber, int pin) {
        return getAccountByCard(cardNumber, pin).getBalance();
    }

    public AccountDTO getAccountByCard(long cardNumber, int pin) {
        CardDTO cardDTO = cardService.getCard(cardNumber, pin);
        return accountRepository.getAccountByCard(cardDTO.getCard_number(), cardDTO.getPin())
                .map(AccountEntityConverter::convertToDto)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with card: " + cardDTO.getCard_number()));
    }
}
