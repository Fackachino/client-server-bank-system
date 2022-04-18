package com.samoylov.server.service.utility;

import com.samoylov.dto.CardDTO;
import com.samoylov.server.entity.Card;

public class CardEntityConverter {
    public static CardDTO convertToDTO(Card card) {
        return new CardDTO(
                card.getCardNumber()
        );
    }
}
