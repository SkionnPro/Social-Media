package com.anshum.Social.Media.Repository;

import com.anshum.Social.Media.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
