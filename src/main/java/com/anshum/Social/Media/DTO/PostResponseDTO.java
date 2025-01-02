package com.anshum.Social.Media.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDTO {
    private Long id;
    private String content;
    private String username;
    private LocalDateTime dateTime;
}
