package com.samoylov.server.service;

import com.samoylov.dto.AccountDTO;
import com.samoylov.dto.CardDTO;
import com.samoylov.dto.CustomerDTO;
import com.samoylov.server.entity.Account;
import com.samoylov.server.entity.Card;
import com.samoylov.server.entity.Customer;
import com.samoylov.server.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    public Optional<CustomerDTO> getCustomerByID(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        Optional<CustomerDTO> customerDTO;

        customerDTO = customerOptional.map(customer -> new CustomerDTO(
                customer.getFirstName(),
                customer.getSecondName(),
                customer.getPatronymic(),
                getAccountDTOByCustomer(customer)));

        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        Iterable<Customer> customerIterable = customerRepository.findAll();

        customerIterable.forEach(
                customer -> {
                    customerDTOS.add(new CustomerDTO(
                            customer.getFirstName(),
                            customer.getSecondName(),
                            customer.getPatronymic(),
                            getAccountDTOByCustomer(customer)));
                }
        );

        return customerDTOS;
    }

    private Set<AccountDTO> getAccountDTOByCustomer(Customer customer) {
        Set<Account> accountSet = customer.getAccounts();
        Set<AccountDTO> accountDTOS = new HashSet<>();

        for (Account account : accountSet) {
            accountDTOS.add(new AccountDTO(
                    account.getAccount_number(),
                    account.getBalance(),
                    getCardDTOByAccount(account)
            ));
        }

        return accountDTOS;
    }


    private Set<CardDTO> getCardDTOByAccount(Account account) {
        Set<Card> cardSet = account.getCards();
        Set<CardDTO> cardDTOS = new HashSet<>();

        for (Card card : cardSet) {
            cardDTOS.add(new CardDTO(
                    card.getCard_number(),
                    card.getPin()
            ));
        }

        return cardDTOS;
    }
}

