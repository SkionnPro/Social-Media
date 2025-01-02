package com.anshum.Social.Media.Controller;

import com.anshum.Social.Media.DTO.PostDTO;
import com.anshum.Social.Media.DTO.PostResponseDTO;
import com.anshum.Social.Media.Model.Post;
import com.anshum.Social.Media.Model.UserTable;
import com.anshum.Social.Media.Service.PostService;
import com.anshum.Social.Media.Service.UserService;
import jakarta.servlet.http.HttpSession;
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

    private UserTable checkLoggedInUser(HttpSession session){
        Object userId = session.getId();
        if(userId != null){
            return userService.getUserById((Long) userId);
        }
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@Validated @RequestBody PostDTO postDTO, HttpSession session){

        UserTable loginCheck = checkLoggedInUser(session);

        if(loginCheck == null){
            return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
        }

        try{
            UserTable userTable = userService.getUserById(postDTO.getUserId());
            Post post = new Post();
            post.setContent(postDTO.getContent());
            post.setUserTable(userTable);

            Post createdPost = postService.createPost(post);

            PostResponseDTO response = new PostResponseDTO();
            response.setId(createdPost.getId());
            response.setContent(createdPost.getContent());
            response.setUsername(createdPost.getUserTable().getUsername());
            response.setDateTime(createdPost.getDateTime());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

    }
}