package org.example.userservice.Exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class PasswordMismatchException extends Exception
{
    public  PasswordMismatchException(String message)
    {
        super(message);
    }
}
