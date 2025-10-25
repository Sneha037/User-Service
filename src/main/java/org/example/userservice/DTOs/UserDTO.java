package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.Models.User;

@Getter
@Setter
public class UserDTO
{
    private String name;
    private String email;

    public static UserDTO from(User user)
    {
        if(user == null)
            return null;

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
