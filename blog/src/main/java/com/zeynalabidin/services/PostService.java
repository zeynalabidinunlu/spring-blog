package com.zeynalabidin.services;

import java.util.List;
import java.util.UUID;

import com.zeynalabidin.domain.entities.Post;

public interface PostService {

	List<Post> getAllPosts(UUID categoryId,UUID tagId);
	
}
