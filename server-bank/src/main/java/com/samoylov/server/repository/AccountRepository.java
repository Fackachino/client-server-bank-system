package com.samoylov.server.repository;

import com.samoylov.server.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query("SELECT a.balance FROM Account a " +
            "JOIN Card c ON c.id_account = a.id " +
            "WHERE c.card_number = :cardNumber and c.pin = :pin")
    Optional<Long> getBalanceByCard(long cardNumber, int pin);

}
