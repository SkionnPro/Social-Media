package com.anshum.Social.Media.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    @NotBlank(message = "Content must not be empty")
    @Size(max = 500, message = "Content must not exceed 500 characters")
    private String content;

    @NotNull(message = "User ID is required")
    private Long userId;
}