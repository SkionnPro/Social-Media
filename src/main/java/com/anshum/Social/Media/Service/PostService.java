package com.anshum.Social.Media.Service;

import com.anshum.Social.Media.Model.Post;
import com.anshum.Social.Media.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository repo;

    public Post createPost(Post post){
        return repo.save(post);
    }


}
