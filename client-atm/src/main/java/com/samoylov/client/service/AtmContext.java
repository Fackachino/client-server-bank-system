package com.samoylov.client.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class AtmContext {
    private String jwtToken = "";
    private String SERVER_PATH = "http://localhost:8081";
}
