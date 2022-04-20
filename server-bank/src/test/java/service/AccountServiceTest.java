package service;

import com.samoylov.dto.AccountDTO;
import com.samoylov.dto.CardDTO;
import com.samoylov.server.Application;
import com.samoylov.server.entity.Account;
import com.samoylov.server.entity.Card;
import com.samoylov.server.repository.AccountRepository;
import com.samoylov.server.repository.CardRepository;
import com.samoylov.server.service.AccountService;
import com.samoylov.server.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private CardRepository cardRepository;

    @MockBean
    private CardService cardService;


    String cardNumber;
    CardDTO cardDTO;
    AccountDTO accountDTO;
    Card card;
    Account account;

    @BeforeEach
    void setUp() {
        cardNumber = "1488666777666123";

        cardDTO = new CardDTO();
        cardDTO.setCardNumber(cardNumber);

        accountDTO = new AccountDTO();
        accountDTO.setBalance(new BigDecimal("66.0"));
        accountDTO.setAccountNumber("123");
        accountDTO.setCards(new HashSet<>());
        accountDTO.getCards().add(cardDTO);

        card = new Card();
        card.setCardNumber("1488666777666123");
        card.setPin("1111");

        account = new Account();
        account.setBalance(new BigDecimal("66.0"));
        account.setAccountNumber("123");
        account.setCards(new HashSet<>());
        card.setAccount(account);
        account.getCards().add(card);

        Mockito.when(cardService.getCardByNumber(cardNumber)).thenReturn(cardDTO);
        Mockito.when(accountRepository.getAccountByCard(cardNumber)).thenReturn(Optional.of(account));
    }

    @DisplayName("Get Account")
    @Test
    void getAccountByCard() {
        AccountDTO actual = accountService.getAccountByCard(cardNumber);
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardNumber(cardNumber);

        AccountDTO expected = new AccountDTO();
        expected.setBalance(new BigDecimal("66.0"));
        expected.setAccountNumber("123");
        expected.setCards(new HashSet<>());
        expected.getCards().add(cardDTO);

        assertEquals(expected, actual);
    }

    @DisplayName("Get Balance")
    @Test
    void getBalanceByCard() {
        String cardNumber = "1488666777666123";
        BigDecimal expected = new BigDecimal("66.0");
        BigDecimal actual = accountService.getBalanceByCard(cardNumber);
        assertEquals(expected, actual);
    }
}