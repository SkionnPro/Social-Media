package com.anshum.Social.Media.Service;

import com.anshum.Social.Media.Model.User;
import com.anshum.Social.Media.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public User createUser(User user){
        return repo.save(user);
    }
}
