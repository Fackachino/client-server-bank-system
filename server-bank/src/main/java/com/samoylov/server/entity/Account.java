package com.samoylov.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Account")
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer id_customer;

    @Column(name = "account_number")
    private long account_number;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToMany(mappedBy = "id_account")
    private Set<Card> cards;
}
