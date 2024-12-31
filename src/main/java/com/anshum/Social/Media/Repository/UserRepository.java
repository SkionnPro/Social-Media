package com.anshum.Social.Media.Repository;

import com.anshum.Social.Media.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
