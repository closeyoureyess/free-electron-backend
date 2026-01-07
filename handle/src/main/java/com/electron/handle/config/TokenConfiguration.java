package com.electron.handle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class TokenConfiguration {

    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }
}
