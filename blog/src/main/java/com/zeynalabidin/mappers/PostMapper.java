package com.zeynalabidin.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.zeynalabidin.domain.CreatePostRequest;
import com.zeynalabidin.domain.UpdatePostRequest;
import com.zeynalabidin.domain.dtos.CreatePostRequestDto;
import com.zeynalabidin.domain.dtos.PostDto;
import com.zeynalabidin.domain.dtos.UpdatePostRequestDto;
import com.zeynalabidin.domain.entities.Post;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);
    
    UpdatePostRequest updatePostRequest (UpdatePostRequestDto dto);
}
