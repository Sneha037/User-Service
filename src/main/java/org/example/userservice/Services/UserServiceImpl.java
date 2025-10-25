package org.example.userservice.Services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.userservice.Exceptions.PasswordMismatchException;
import org.example.userservice.Models.Token;
import org.example.userservice.Models.User;
import org.example.userservice.Repositories.TokenRepository;
import org.example.userservice.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    private final TokenRepository tokenRepository;
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }
    @Override
    public User signup(String name, String email, String password)
    {
        Optional<User>  optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent())
        {
            // redirect to login page;
            return optionalUser.get();
        }

        // If the email is not present in the database, then create a new User and return the user
        // We shouldn't store password as it is in database, instead use BCrypt Password Encoder

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    @Override
    public Token login(String email, String password) throws PasswordMismatchException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty())
        {
            // Redirect user to signup page
            return null;
        }

        // Match the password

        User user = optionalUser.get();

        if(!bCryptPasswordEncoder.matches(password, user.getPassword()))
        {
            throw new PasswordMismatchException("The password is invalid");
        }

        // If the password matches, then the login is successful and we should generate the token

        Token token = new Token();

        token.setUser(user);
        token.setTokenValue(RandomStringUtils.randomAlphanumeric(128));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30); // Token valid for 30 days
        Date date = calendar.getTime();
        token.setExpiryDate(date);

        return tokenRepository.save(token);
    }

    @Override
    public User validateToken(String tokenValue)
    {
        return null;
    }
}
