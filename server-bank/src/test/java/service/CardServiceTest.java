package service;

import com.samoylov.dto.CardDTO;
import com.samoylov.server.Application;
import com.samoylov.server.entity.Card;
import com.samoylov.server.repository.CardRepository;
import com.samoylov.server.service.CardService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CardServiceTest {
    @MockBean
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    @DisplayName("Get Card")
    @Test
    void getCardByNumber() {
        String cardNumber = "1488666777666123";

        Card card = new Card();
        card.setCardNumber(cardNumber);

        CardDTO expected = new CardDTO();
        expected.setCardNumber(cardNumber);

        Mockito.when(cardRepository.getCardByNumber(cardNumber)).thenReturn(Optional.of(card));
        CardDTO actual = cardService.getCardByNumber("1488666777666123");

        assertEquals(expected, actual);
    }
}
