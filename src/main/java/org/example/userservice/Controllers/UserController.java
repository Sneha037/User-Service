package org.example.userservice.Controllers;

import org.example.userservice.DTOs.LoginRequestDTO;
import org.example.userservice.DTOs.SignUpRequestDTO;
import org.example.userservice.DTOs.TokenDTO;
import org.example.userservice.DTOs.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController
{
    @PostMapping("/signup")
     public ResponseEntity<UserDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO)
     {
         return null;
     }

     @PostMapping("/login")
     public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestDTO loginRequestDTO)
     {
         return null;
     }

     @GetMapping("/validate/{tokenValue}")
     public UserDTO validateToken(@PathVariable("tokenValue") String tokenValue)
     {
         return null;
     }
}
