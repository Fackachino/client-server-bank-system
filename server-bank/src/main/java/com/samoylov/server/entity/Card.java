package com.samoylov.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Card")
@Data
@NoArgsConstructor
public class Card {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    @Column(name = "card_number")
    private long cardNumber;

    @Column(name = "pin")
    private int pin;
}

