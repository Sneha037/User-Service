package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.Models.Role;
import org.example.userservice.Models.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTO
{
    private String name;
    private String email;
    private List<String> roles = new ArrayList<>();

    public static UserDTO from(User user)
    {
        if(user == null)
            return null;

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());

        if(user.getRoles() == null)
            return userDTO;

        for(Role role : user.getRoles())
        {
            userDTO.getRoles().add(role.getRoleName());
        }

        return userDTO;
    }
}
