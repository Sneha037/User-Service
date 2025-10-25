package org.example.userservice.Services;

import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.userservice.Exceptions.InvalidTokenException;
import org.example.userservice.Exceptions.PasswordMismatchException;
import org.example.userservice.Models.Role;
import org.example.userservice.Models.Token;
import org.example.userservice.Models.User;
import org.example.userservice.Repositories.RoleRepository;
import org.example.userservice.Repositories.TokenRepository;
import org.example.userservice.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    private final TokenRepository tokenRepository;
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository, RoleRepository roleRepository)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
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

        Optional<Role> userRole = roleRepository.findByRoleName("GUEST");

        userRole.ifPresent(role -> user.getRoles().add(role));

        return userRepository.save(user);
    }

    @Override
    public String login(String email, String password) throws PasswordMismatchException {
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

      /*  Token token = new Token();

        token.setUser(user);
        token.setTokenValue(RandomStringUtils.randomAlphanumeric(128));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30); // Token valid for 30 days
        Date date = calendar.getTime();
        token.setExpiryDate(date);

        return tokenRepository.save(token);

       */

        //Generate a JWT token instead of random alphanumeric token

        // 3 parameters for JWT: Header, Payload, Signature, A.B.C format

        // Header: Algorithm and Token Type
        // Payload: Claims (user information, expiry date, issued at, etc)
        // Signature

        String userData = "{\n" +
                "   \"email\": \"mukherjeesneha037@gmail.com\",\n" +
                "   \"roles\": [\n" +
                "      \"Guest\",\n" +
                "      \"Buyer\"\n" +
                "   ],\n" +
                "   \"expiryDate\": \"22ndSept2026\"\n" +
                "}";
          /*
          Byte array is used to represent binary data. In the context of JWT (JSON Web Token),
            the payload is the part of the token that contains the claims or information about the user.
            JJWT accepts the payload as a byte array to allow for flexibility in encoding and decoding the data.
           */
        byte[] payload = userData.getBytes(StandardCharsets.UTF_8);

        /*
        Using builder pattern here to create JWT token
         */
        String token = Jwts.builder().content(payload).compact();

        return token;
    }

    @Override
    public User validateToken(String tokenValue) throws InvalidTokenException
    {
        Optional<Token> optionalToken = tokenRepository.findByTokenValueAndExpiryDateAfter(tokenValue, new Date());

        /*
        Modified the method to not throw exception, but return null if token is invalid, so that Product Service handles it gracefully
         */

        if(optionalToken.isEmpty())
        {
            //throw new InvalidTokenException("The token is invalid or has expired");
            return null;
        }

        return optionalToken.get().getUser();
    }
}
