package com.spring.api.service;

import com.spring.api.data.dto.PostDto;
import com.spring.api.data.dto.PostResponse;

public interface PostService {

    PostDto createPost(PostDto dto);

    PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto dto, Long id);

    void deletePostById(Long id);

}
