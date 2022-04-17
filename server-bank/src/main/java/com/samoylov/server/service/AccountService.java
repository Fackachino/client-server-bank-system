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

    public BigDecimal getBalanceByCard(String cardNumber) {
        return getAccountByCard(cardNumber).getBalance();
    }

    public AccountDTO getAccountByCard(String cardNumber) {
        CardDTO cardDTO = cardService.getCardByNumber(cardNumber);
        return accountRepository.getAccountByCard(cardDTO.getCardNumber())
                .map(AccountEntityConverter::convertToDto)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with card: " + cardDTO.getCardNumber()));
    }
}
