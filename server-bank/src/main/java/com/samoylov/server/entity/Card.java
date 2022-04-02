package com.samoylov.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Card")
@NoArgsConstructor
@Getter
@Setter
public class Card {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_account", nullable = false)
    private Account id_account;

    @Column(name = "card_number")
    private long card_number;

    @Column(name = "pin")
    private int pin;
}

