package org.example.userservice.Services;

import org.example.userservice.Models.Token;
import org.example.userservice.Models.User;

public class UserServiceImpl implements UserService
{

    @Override
    public User signup(String name, String email, String password)
    {
        return null;
    }

    @Override
    public Token login(String email, String password)
    {
        return null;
    }

    @Override
    public User validateToken(String tokenValue)
    {
        return null;
    }
}
