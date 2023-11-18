package com.phy.Configure.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class AuthenticationManagerConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

//    @Autowired
//    public AuthenticationManagerConfig(AuthenticationConfiguration authenticationConfiguration){
//        this.authenticationConfiguration = authenticationConfiguration;
//    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
