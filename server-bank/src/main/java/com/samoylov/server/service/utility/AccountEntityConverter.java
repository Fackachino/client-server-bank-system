package com.samoylov.server.service.utility;

import com.samoylov.dto.AccountDTO;
import com.samoylov.server.entity.Account;

import java.util.stream.Collectors;

public class AccountEntityConverter {
    public static AccountDTO convertToDto(Account accountEntity) {
        AccountDTO accountDTO = new AccountDTO(
                accountEntity.getAccount_number(),
                accountEntity.getBalance(),
                accountEntity.getCards().stream()
                        .map(CardEntityConverter::convertToDTO)
                        .collect(Collectors.toSet()));
        return accountDTO;
    }
}
