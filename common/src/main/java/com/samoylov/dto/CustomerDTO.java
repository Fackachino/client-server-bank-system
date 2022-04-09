package com.samoylov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    String firstName;
    String secondName;
    String patronymic;
    Set<AccountDTO> accounts;

}
