package com.spring.api.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CommentDto {

    private Long id;
    private Long postId;
    private String body;
    private String userIp;
    private Integer parentsId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
