package com.anshum.Social.Media.Service;

import com.anshum.Social.Media.Model.User;
import com.anshum.Social.Media.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    public User createUser(User user){
        if(repo.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already in use");
        }
        if(repo.existsByUsername(user.getUsername())){
            throw new RuntimeException("Username already in use");
        }
        return repo.save(user);
    }

    public User getUserById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found with id : " + id));
    }

    public User loginUser(String username, String password) {
        User user = repo.findByUsername(username);

        if(user != null && password == user.getPassword())
            return user;
        return null;
    }
}
