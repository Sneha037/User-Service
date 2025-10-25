package org.example.userservice.Repositories;

import org.example.userservice.Models.Token;
import org.example.userservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long>
{
    Optional<User> findByTokenValue(String Id);

    Token save(Token token);

    // select * from Token where tokenValue = ? && expiryDate > now()

    Optional<Token> findByTokenValueAndExpiryDateAfter(String tokenValue, Date currentDate);
}
