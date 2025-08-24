package com.zeynalabidin.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.zeynalabidin.domain.PostStatus;
import com.zeynalabidin.domain.dtos.CategoryDto;
import com.zeynalabidin.domain.dtos.CreateCategoryRequest;
import com.zeynalabidin.domain.entities.Category;
import com.zeynalabidin.domain.entities.Post;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

	@Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
	CategoryDto toDto(Category category);

	
	Category toEntity(CreateCategoryRequest createCategoryRequest);
	
	
	@Named("calculatePostCount")
	default long calculatePostCount(List<Post> posts) {
		if (null == posts) {
			return 0;
		}
		return posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();
	}
}
