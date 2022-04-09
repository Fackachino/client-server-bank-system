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

    private CardDTO getCardByNumber(long cardNumber) {
        return cardRepository.getCardByNumber(cardNumber)
                .map(CardEntityConverter::convertToDTO)
                .orElseThrow(() -> new CardNotFoundException("Card not found with number: " + cardNumber));
    }

    public CardDTO getCard(long cardNumber, int pin) {
        CardDTO cardDTO = getCardByNumber(cardNumber);

        if (cardDTO.getPin() != pin) {
            throw new WrongPinException("Wrong Pin: " + pin);
        } else return cardDTO;
    }
}
