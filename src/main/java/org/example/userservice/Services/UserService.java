package org.example.userservice.Services;

import org.example.userservice.Exceptions.InvalidTokenException;
import org.example.userservice.Exceptions.PasswordMismatchException;
import org.example.userservice.Models.Token;
import org.example.userservice.Models.User;

public interface UserService
{
    User signup(String name, String email, String password);

    /*
    Changing the method signature to return String instead of Token to accomodate JWT token representation
    We do not need to return the entire Token object, asd JWTs are self validating and contain all necessary information
     */
    //Token login(String email, String password) throws PasswordMismatchException;

    String login(String email, String password) throws PasswordMismatchException;

    User validateToken(String tokenValue) throws InvalidTokenException;
}
