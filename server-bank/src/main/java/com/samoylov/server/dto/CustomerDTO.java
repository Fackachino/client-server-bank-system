package com.samoylov.server.dto;

import lombok.Value;
import java.util.Set;

@Value
public class CustomerDTO {
    long id;
    String firstName;
    String secondName;
    String patronymic;
    Set<AccountDTO> accounts;
}
