package com.samoylov.server.dto;

import lombok.Value;

@Value
public class CardDTO {
    long id;
    long card_number;
    int pin;
}
