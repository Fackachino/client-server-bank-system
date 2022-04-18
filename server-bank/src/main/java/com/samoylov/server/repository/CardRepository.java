package com.samoylov.server.repository;

import com.samoylov.server.entity.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CardRepository extends CrudRepository<Card, Long> {
    @Query("SELECT c FROM Card c " +
            "WHERE c.cardNumber = :cardNumber")
    Optional<Card> getCardByNumber(String cardNumber);
}
