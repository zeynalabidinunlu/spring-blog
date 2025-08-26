package com.zeynalabidin.services;

import java.util.List;
import java.util.UUID;

import com.zeynalabidin.domain.CreatePostRequest;
import com.zeynalabidin.domain.UpdatePostRequest;
import com.zeynalabidin.domain.entities.Post;
import com.zeynalabidin.domain.entities.User;

public interface PostService {

	List<Post> getAllPosts(UUID categoryId, UUID tagId);

	List<Post> getDraftPosts(User user);

	Post createPost(User user, CreatePostRequest createPostRequest);

	Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
	
	Post getPost(UUID id);
	
	void deletePosts(UUID id);
}
