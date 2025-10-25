package org.example.userservice.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationConfig
{
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf -> csrf.disable());
        http.cors(cors -> cors.disable());

        /*http.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests.requestMatchers("/signup").permitAll()
        );
         */

        http.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests.anyRequest().permitAll()
        );

        return http.build();
    }
}
