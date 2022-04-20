package com.samoylov.server.service;

import com.samoylov.dto.CardDTO;
import com.samoylov.dto.CustomerDTO;
import com.samoylov.server.entity.Customer;
import com.samoylov.server.exception.CustomerNotFoundException;
import com.samoylov.server.repository.CustomerRepository;
import com.samoylov.server.service.utility.CustomerEntityConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;
    private CardService cardService;

    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        Iterable<Customer> customerIterable = customerRepository.findAll();

        customerIterable.forEach(
                customer -> customerDTOS.add(CustomerEntityConverter.convertToDTO(customer))
        );

        if (customerDTOS.size() == 0) {
            log.info("Customers in DB not found");
            throw new CustomerNotFoundException("Customers not found");
        } else return customerDTOS;
    }

    public CustomerDTO getCustomerByCard(String cardNumber) {
        CardDTO cardDTO = cardService.getCardByNumber(cardNumber);
        Optional<Customer> customer = customerRepository.getCustomerByCard(cardDTO.getCardNumber());
        if (!customer.isPresent()) {
            log.info("Customer with card: " + cardNumber + " not found");
            throw new CustomerNotFoundException("Customer not found");
        } else return customer.map(CustomerEntityConverter::convertToDTO).get();

    }
}