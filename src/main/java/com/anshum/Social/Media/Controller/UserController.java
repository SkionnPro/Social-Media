package com.anshum.Social.Media.Controller;

import com.anshum.Social.Media.DTO.UserDTO;
import com.anshum.Social.Media.DTO.UserResponseDTO;
import com.anshum.Social.Media.Model.User;
import com.anshum.Social.Media.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Validated @RequestBody UserDTO userDTO){
        try{
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());

            User createdUser = service.createUser(user);

            UserResponseDTO response = new UserResponseDTO();
            response.setId(createdUser.getId());
            response.setUsername(createdUser.getUsername());
            response.setEmail(createdUser.getEmail());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
}
