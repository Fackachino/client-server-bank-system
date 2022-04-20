package com.samoylov.server.service;

import com.samoylov.dto.AccountDTO;
import com.samoylov.dto.CardDTO;
import com.samoylov.server.entity.Account;
import com.samoylov.server.exception.AccountNotFoundException;
import com.samoylov.server.exception.BalanceException;
import com.samoylov.server.repository.AccountRepository;
import com.samoylov.server.service.utility.AccountEntityConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        return AccountEntityConverter.convertToDto(getAccount(cardNumber));
    }

    public BigDecimal depositMoney(String cardNumber, BigDecimal money) {
        Account account = getAccount(cardNumber);
        account.setBalance(account.getBalance().add(money));
        accountRepository.save(account);
        return account.getBalance();
    }

    public BigDecimal withDrawMoney(String cardNumber, BigDecimal money) {
        Account account = getAccount(cardNumber);

        BigDecimal balance = account.getBalance();
        if (balance.compareTo(money) < 0) {
            throw new BalanceException("Not enough money to withdraw");
        }

        account.setBalance(account.getBalance().subtract(money));
        accountRepository.save(account);
        return account.getBalance();
    }

    private Account getAccount(String cardNumber) {
        CardDTO cardDTO = cardService.getCardByNumber(cardNumber);
        return accountRepository.getAccountByCard(cardDTO.getCardNumber())
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with card: " + cardDTO.getCardNumber()));
    }
}
