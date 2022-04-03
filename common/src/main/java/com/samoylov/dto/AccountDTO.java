package com.samoylov.server.dto;

import lombok.Value;
import java.math.BigDecimal;
import java.util.Set;

@Value
public class AccountDTO {
    long id;
    long account_number;
    BigDecimal balance;
    Set<CardDTO> cards;
}
