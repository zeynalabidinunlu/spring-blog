package com.zeynalabidin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zeynalabidin.domain.dtos.PostDto;
import com.zeynalabidin.domain.entities.Post;
import com.zeynalabidin.domain.entities.User;
import com.zeynalabidin.mappers.PostMapper;
import com.zeynalabidin.services.PostService;
import com.zeynalabidin.services.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;
    
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID tagId) {
        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId){
    	User loggedInUser = userService.getUserById(userId);
    	List<Post> draftPosts = postService.getDraftPosts(loggedInUser);
    	
    	List<PostDto> postDtos = draftPosts.stream().map(postMapper ::toDto).toList();
    	return ResponseEntity.ok(postDtos);
    }
}
