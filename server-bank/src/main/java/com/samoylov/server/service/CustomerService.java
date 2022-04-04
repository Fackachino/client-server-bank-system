package com.samoylov.server.service;

import com.samoylov.dto.CustomerDTO;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public Optional<CustomerDTO> getCustomerByID(Long id);
    public List<CustomerDTO> getAllCustomers();
}
