package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.Models.Role;
import org.example.userservice.Models.Token;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TokenDTO
{
    private String value;
    private Date expiryDate;

    public static TokenDTO from(Token token)
    {
        if(token == null)
            return null;

        TokenDTO tokenDTO = new TokenDTO();

        tokenDTO.setValue(token.getTokenValue());
        tokenDTO.setExpiryDate(token.getExpiryDate());

        return tokenDTO;
    }
}
