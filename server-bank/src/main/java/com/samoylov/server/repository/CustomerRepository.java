package com.samoylov.server.repository;

import com.samoylov.server.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Query("SELECT cu FROM Customer cu " +
            "JOIN Account a ON cu.id = a.customer " +
            "JOIN Card c on c.account = a.id " +
            "WHERE c.cardNumber = :cardNumber and c.pin = :pin")
    Optional<Customer> getCustomerByCard(long cardNumber, int pin);
}
