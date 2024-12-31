package com.anshum.Social.Media.Controller;

import com.anshum.Social.Media.Model.Post;
import com.anshum.Social.Media.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private PostService service;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@Validated @RequestBody Post post){
        return new ResponseEntity<>(service.createPost(post), HttpStatus.OK);
    }
}
