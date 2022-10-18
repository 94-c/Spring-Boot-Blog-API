package com.spring.api.service.impl;

import com.spring.api.data.dto.CommentDto;
import com.spring.api.data.entity.Comment;
import com.spring.api.data.entity.Post;
import com.spring.api.data.repository.CommentRepository;
import com.spring.api.data.repository.PostRepository;
import com.spring.api.service.CommentService;
import com.spring.api.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow();
        comment.setPost(post);
        Comment newComment =  commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow();

        Comment comment = commentRepository.findById(commentId).orElseThrow();

        if(!comment.getPost().getId().equals(post.getId())){
            System.out.println("Error");
        }

        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, long commentId, CommentDto dto) {
        Post post = postRepository.findById(postId).orElseThrow();

        Comment comment = commentRepository.findById(commentId).orElseThrow();

        if(!comment.getPost().getId().equals(post.getId())){
            System.out.println("Error");
        }

        comment.setBody(dto.getBody());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow();

        Comment comment = commentRepository.findById(commentId).orElseThrow();

        if(!comment.getPost().getId().equals(post.getId())){
            System.out.println("Error");
        }

        commentRepository.delete(comment);
    }

    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        return  commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
        return  comment;
    }
}
