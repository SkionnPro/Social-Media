package com.anshum.Social.Media.Repository;

import com.anshum.Social.Media.Model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    UserTable findByUsername(String username);
}
