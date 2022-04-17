package com.samoylov.server.service;

import com.samoylov.dto.CardDTO;
import com.samoylov.server.exception.CardNotFoundException;
import com.samoylov.server.exception.WrongPinException;
import com.samoylov.server.repository.CardRepository;
import com.samoylov.server.service.utility.CardEntityConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;

    public CardDTO getCardByNumber(String cardNumber) {
        return cardRepository.getCardByNumber(cardNumber)
                .map(CardEntityConverter::convertToDTO)
                .orElseThrow(() -> new CardNotFoundException("Card not found with number: " + cardNumber));
    }
}
