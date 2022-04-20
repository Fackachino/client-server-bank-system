package service;

import com.samoylov.dto.AccountDTO;
import com.samoylov.dto.CardDTO;
import com.samoylov.dto.CustomerDTO;
import com.samoylov.server.Application;
import com.samoylov.server.entity.Account;
import com.samoylov.server.entity.Card;
import com.samoylov.server.entity.Customer;
import com.samoylov.server.repository.AccountRepository;
import com.samoylov.server.repository.CustomerRepository;
import com.samoylov.server.service.CardService;
import com.samoylov.server.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerServiceTest {

    @MockBean
    CardService cardService;

    @Autowired
    CustomerService customerService;

    @MockBean
    AccountRepository accountRepository;

    @MockBean
    CustomerRepository customerRepository;

    String cardNumber;
    CardDTO cardDTO;
    AccountDTO accountDTO;
    Card card;
    Account account;
    Customer customer;
    CustomerDTO customerDTO;

    @Before
    public void setUp() {
        customer = new Customer();
        customer.setFirstName("Test");
        customer.setSecondName("Test");
        customer.setPatronymic("Test");

        cardNumber = "1488666777666123";
        cardDTO = new CardDTO();
        cardDTO.setCardNumber(cardNumber);

        accountDTO = new AccountDTO();
        accountDTO.setBalance(new BigDecimal("66.0"));
        accountDTO.setAccountNumber("123");
        accountDTO.setCards(new HashSet<>());
        accountDTO.getCards().add(cardDTO);

        card = new Card();
        card.setCardNumber(cardNumber);
        card.setPin("1111");

        account = new Account();
        account.setBalance(new BigDecimal("66.0"));
        account.setAccountNumber("123");
        account.setCards(new HashSet<>());
        card.setAccount(account);
        account.getCards().add(card);
        account.setCustomer(customer);
        customer.setAccounts(new HashSet<>());
        customer.getAccounts().add(account);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Test");
        customerDTO.setSecondName("Test");
        customerDTO.setPatronymic("Test");
        customerDTO.setAccounts(new HashSet<>());
        customerDTO.getAccounts().add(accountDTO);

        Mockito.when(cardService.getCardByNumber(cardNumber)).thenReturn(cardDTO);
        Mockito.when(accountRepository.getAccountByCard(cardNumber)).thenReturn(Optional.of(account));
        Mockito.when(customerRepository.getCustomerByCard(cardNumber)).thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.findAll()).thenReturn(customerList);

    }

    @DisplayName("Get Customer")
    @Test
    public void getCustomerByCard() {
        CustomerDTO actual = customerService.getCustomerByCard(cardNumber);
        CustomerDTO expected = customerDTO;

        assertEquals(expected, actual);

    }

    @DisplayName("Get Customers")
    @Test
    public void getCustomers() {
        List<CustomerDTO> actual = customerService.getAllCustomers();
        List<CustomerDTO> expected = new ArrayList<>();
        expected.add(customerDTO);

        assertEquals(expected, actual);
    }

}