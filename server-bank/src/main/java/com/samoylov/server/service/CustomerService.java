package com.samoylov.server.service;

import com.samoylov.dto.CardDTO;
import com.samoylov.dto.CustomerDTO;
import com.samoylov.server.entity.Customer;
import com.samoylov.server.exception.CustomerNotFoundException;
import com.samoylov.server.repository.CustomerRepository;
import com.samoylov.server.service.utility.CustomerEntityConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;
    private CardService cardService;

    public Optional<CustomerDTO> getCustomerByID(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        Optional<CustomerDTO> customerDTO;
        customerDTO = customerOptional.map(CustomerEntityConverter::convertToDTO);

        return customerDTO;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        Iterable<Customer> customerIterable = customerRepository.findAll();

        customerIterable.forEach(
                customer -> customerDTOS.add(CustomerEntityConverter.convertToDTO(customer))
        );

        if (customerDTOS.size() == 0) {
            throw new CustomerNotFoundException("Customers not found");
        } else return customerDTOS;
    }

    public CustomerDTO getCustomerByCard(long cardNumber, int pin) {
        CardDTO cardDTO = cardService.getCard(cardNumber, pin);
        return customerRepository.getCustomerByCard(cardDTO.getCard_number(), cardDTO.getPin())
                .map(CustomerEntityConverter::convertToDTO)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

    }
}

