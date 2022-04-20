package com.samoylov.server.service;

import com.samoylov.dto.CardDTO;
import com.samoylov.server.entity.Card;
import com.samoylov.server.exception.CardNotFoundException;
import com.samoylov.server.repository.CardRepository;
import com.samoylov.server.service.utility.CardEntityConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;

    public CardDTO getCardByNumber(String cardNumber) {
        Optional<Card> card = cardRepository.getCardByNumber(cardNumber);
        if (!card.isPresent()) {
            log.info("Card not found with number: " + cardNumber);
            throw new CardNotFoundException("Card not found with number: " + cardNumber);
        } else {
            return card.map(CardEntityConverter::convertToDTO).get();
        }
    }
}
