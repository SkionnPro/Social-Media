package com.anshum.Social.Media.Service;

import com.anshum.Social.Media.Model.UserTable;
import com.anshum.Social.Media.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository repo;

    public UserTable createUser(UserTable userTable){
        if(repo.existsByEmail(userTable.getEmail())){
            throw new RuntimeException("Email already in use");
        }
        if(repo.existsByUsername(userTable.getUsername())){
            throw new RuntimeException("Username already in use");
        }
        return repo.save(userTable);
    }

    public UserTable getUserById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found with id : " + id));
    }

    public UserTable loginUser(String username, String password) {
        UserTable userTable = repo.findByUsername(username);

        if(userTable != null && password == userTable.getPassword())
            return userTable;
        return null;
    }
}
