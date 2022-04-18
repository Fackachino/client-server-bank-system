package com.samoylov.server.security;

import com.samoylov.server.entity.Card;
import com.samoylov.server.entity.Status;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

@NoArgsConstructor

public class JwtUser implements UserDetails {
    private String cardNumber;
    private String pin;
    private boolean enabled;

    public JwtUser(Card card){
        cardNumber = card.getCardNumber();
        pin = card.getPin();
        enabled = card.getStatus().equals(Status.ACTIVE);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

    @Override
    public String getPassword() {
        return pin;
    }

    @Override
    public String getUsername() {
        return cardNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
