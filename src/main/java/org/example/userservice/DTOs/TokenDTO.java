package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.Models.Role;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TokenDTO
{
    private String value;
    private Date expiryDate;
}
