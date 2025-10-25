package org.example.userservice.Services;

import org.example.userservice.Exceptions.PasswordMismatchException;
import org.example.userservice.Models.Token;
import org.example.userservice.Models.User;

public interface UserService
{
    User signup(String name, String email, String password);

    Token login(String email, String password) throws PasswordMismatchException;

    User validateToken(String tokenValue);
}
