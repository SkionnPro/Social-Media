package com.anshum.Social.Media.Controller;

import com.anshum.Social.Media.DTO.LoginDTO;
import com.anshum.Social.Media.DTO.UserDTO;
import com.anshum.Social.Media.DTO.UserResponseDTO;
import com.anshum.Social.Media.Model.User;
import com.anshum.Social.Media.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO,HttpSession session){
        User user = service.loginUser(loginDTO.getUsername(), loginDTO.getPassword());

        if(user == null)
            return new ResponseEntity<>("Invalid Credentials...", HttpStatus.UNAUTHORIZED);

        session.setAttribute("UserId", user.getId());
        return new ResponseEntity<>("Login Successful...", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate();
        return new ResponseEntity<>("Logout Successful", HttpStatus.OK);
    }
}
