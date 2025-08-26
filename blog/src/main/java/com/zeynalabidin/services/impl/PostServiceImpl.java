package com.zeynalabidin.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeynalabidin.domain.CreatePostRequest;
import com.zeynalabidin.domain.PostStatus;
import com.zeynalabidin.domain.entities.Category;
import com.zeynalabidin.domain.entities.Post;
import com.zeynalabidin.domain.entities.Tag;
import com.zeynalabidin.domain.entities.User;
import com.zeynalabidin.repositories.PostRepository;
import com.zeynalabidin.services.CategoryService;
import com.zeynalabidin.services.PostService;
import com.zeynalabidin.services.TagService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final CategoryService categoryService;
	private final TagService tagService;

	private static final int WORDS_PER_MINUTE = 200;

	@Override
	@Transactional(readOnly = true)
	public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
		if (categoryId != null && tagId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			Tag tag = tagService.getTagById(tagId);
			return postRepository.findAllByStatusAndCategoryAndTagsContaining(PostStatus.PUBLISHED, category, tag);
		}

		if (categoryId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED, category);
		}

		if (tagId != null) {
			Tag tag = tagService.getTagById(tagId);
			return postRepository.findAllByStatusAndTagsContaining(PostStatus.PUBLISHED, tag);
		}

		return postRepository.findAllByStatus(PostStatus.PUBLISHED);
	}

	@Override
	public List<Post> getDraftPosts(User user) {
		return postRepository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
	}

	@Transactional
	@Override
	public Post createPost(User user, CreatePostRequest createPostRequest) {

		Post newPost = new Post();
		newPost.setTitle(createPostRequest.getTitle());
		newPost.setContent(createPostRequest.getContent());
		newPost.setStatus(createPostRequest.getStatus());
		newPost.setAuthor(user);
		newPost.setReadingTime(calculateReadingTime(createPostRequest.getContent()));
	
		Category category = categoryService.getCategoryById(createPostRequest.getCategoryId());
		newPost.setCategory(category);
		
		Set<UUID> tagIds = createPostRequest.getTagIds();
		List<Tag> tags = tagService.getTagByIds(tagIds);
		newPost.setTags(new HashSet<>(tags));
	
		return postRepository.save(newPost);
	}

	private Integer calculateReadingTime(String content) {
		if (content == null || content.isEmpty()) {
			return 0;
		}

		int wordCount = content.trim().split("\\s+").length;
		return (int) Math.ceil((double) wordCount / WORDS_PER_MINUTE);
	}
}
