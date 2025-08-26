package com.zeynalabidin.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostRequest {

	private String title;
	
	
	private String content;
	
	private UUID categoryId;
	
	private Set<UUID> tagIds= new HashSet<>();
	
	private PostStatus status;
}
