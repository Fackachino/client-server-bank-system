package com.samoylov.server.repository;

import com.samoylov.server.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query("SELECT a FROM Account a " +
            "JOIN Card c ON c.account = a.id " +
            "WHERE c.cardNumber = :cardNumber")
    Optional<Account> getAccountByCard(String cardNumber);

    @Query("SELECT c.account FROM Card c " +
            "WHERE c.cardNumber = :cardNumber")
    Optional<Long> getAccountIdByCard(String cardNumber);
}
