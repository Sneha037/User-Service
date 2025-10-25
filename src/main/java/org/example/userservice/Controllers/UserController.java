package org.example.userservice.Controllers;

import org.example.userservice.DTOs.LoginRequestDTO;
import org.example.userservice.DTOs.SignUpRequestDTO;
import org.example.userservice.DTOs.TokenDTO;
import org.example.userservice.DTOs.UserDTO;
import org.example.userservice.Exceptions.PasswordMismatchException;
import org.example.userservice.Models.Token;
import org.example.userservice.Models.User;
import org.example.userservice.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController
{

    UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/signup")
     public ResponseEntity<UserDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO)
     {
         User user = userService.signup(
                 signUpRequestDTO.getName(),
                 signUpRequestDTO.getEmail(),
                 signUpRequestDTO.getPassword()
         );

         UserDTO userDTO = UserDTO.from(user);

         ResponseEntity<UserDTO> response = ResponseEntity.ok(userDTO);

         return response;
     }

     @PostMapping("/login")
     public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) throws PasswordMismatchException {
         Token token  = userService.login(
                 loginRequestDTO.getEmail(),
                 loginRequestDTO.getPassword()
         );

         TokenDTO tokenDTO = TokenDTO.from(token);

         ResponseEntity<TokenDTO> response = ResponseEntity.ok(tokenDTO);

         return response;
     }

     @GetMapping("/validate/{tokenValue}")
     public UserDTO validateToken(@PathVariable("tokenValue") String tokenValue)
     {
         return null;
     }
}
