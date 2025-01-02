package com.anshum.Social.Media.Controller;

import com.anshum.Social.Media.DTO.LoginDTO;
import com.anshum.Social.Media.DTO.UserDTO;
import com.anshum.Social.Media.DTO.UserResponseDTO;
import com.anshum.Social.Media.Model.UserTable;
import com.anshum.Social.Media.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
            UserTable userTable = new UserTable();
            userTable.setUsername(userDTO.getUsername());
            userTable.setEmail(userDTO.getEmail());
            userTable.setPassword(userDTO.getPassword());

            UserTable createdUserTable = service.createUser(userTable);

            UserResponseDTO response = new UserResponseDTO();
            response.setId(createdUserTable.getId());
            response.setUsername(createdUserTable.getUsername());
            response.setEmail(createdUserTable.getEmail());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO,HttpSession session){
        UserTable userTable = service.loginUser(loginDTO.getUsername(), loginDTO.getPassword());

        if(userTable == null)
            return new ResponseEntity<>("Invalid Credentials...", HttpStatus.UNAUTHORIZED);

        session.setAttribute("UserId", userTable.getId());
        return new ResponseEntity<>("Login Successful...", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate();
        return new ResponseEntity<>("Logout Successful", HttpStatus.OK);
    }
}
