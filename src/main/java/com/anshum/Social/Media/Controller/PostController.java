package com.anshum.Social.Media.Controller;

import com.anshum.Social.Media.DTO.PostDTO;
import com.anshum.Social.Media.DTO.PostResponseDTO;
import com.anshum.Social.Media.Model.Post;
import com.anshum.Social.Media.Model.User;
import com.anshum.Social.Media.Service.PostService;
import com.anshum.Social.Media.Service.UserService;
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
    private final PostService postService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<PostResponseDTO> createPost(@Validated @RequestBody PostDTO postDTO){
        try{
            User user = userService.getUserById(postDTO.getUserId());
            Post post = new Post();
            post.setContent(postDTO.getContent());
            post.setUser(user);

            Post createdPost = postService.createPost(post);

            PostResponseDTO response = new PostResponseDTO();
            response.setId(createdPost.getId());
            response.setContent(createdPost.getContent());
            response.setUsername(createdPost.getUser().getUsername());
            response.setDateTime(createdPost.getDateTime());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

    }
}