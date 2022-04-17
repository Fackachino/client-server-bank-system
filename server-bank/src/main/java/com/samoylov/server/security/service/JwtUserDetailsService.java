package com.samoylov.server.security.service;

import com.samoylov.server.repository.CardRepository;
import com.samoylov.server.security.JwtUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private CardRepository cardRepository;

    public UserDetails loadUserByUsername(String cardNumber) throws UsernameNotFoundException {
        return cardRepository.getCardByNumber(cardNumber)
                .map(JwtUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Card with number: " + cardNumber + " not found."));
    }
}
