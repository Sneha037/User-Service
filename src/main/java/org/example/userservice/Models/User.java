package org.example.userservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel
{
    private String name;
    private String email;
    private String password;
    @ManyToMany
    private List<Role> roles = new ArrayList<Role>();
}

/*
Cardinality: User - Roles
              M : M
             User : Token
             1 : M
 */
