package com.samoylov.server.service.utility;

import com.samoylov.dto.CustomerDTO;
import com.samoylov.server.entity.Customer;

import java.util.stream.Collectors;

public class CustomerEntityConverter {
    public static CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO(
                customer.getFirstName(),
                customer.getSecondName(),
                customer.getPatronymic(),
                customer.getAccounts().stream()
                        .map(AccountEntityConverter::convertToDto)
                        .collect(Collectors.toSet())
        );
        return customerDTO;
    }
}
