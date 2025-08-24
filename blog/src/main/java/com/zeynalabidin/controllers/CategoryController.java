package com.zeynalabidin.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeynalabidin.domain.dtos.CategoryDto;
import com.zeynalabidin.domain.dtos.CreateCategoryRequest;
import com.zeynalabidin.domain.entities.Category;
import com.zeynalabidin.mappers.CategoryMapper;
import com.zeynalabidin.services.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	private final CategoryMapper categoryMapper;

	@GetMapping
	public ResponseEntity<List<CategoryDto>> listCategories() {
		List<CategoryDto> categories = categoryService.listCategories().stream()
				.map(category -> categoryMapper.toDto(category)).toList();

		return ResponseEntity.ok(categories);
	}

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Category categoryToCreate = categoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(categoryToCreate);
        return new ResponseEntity<>(
                categoryMapper.toDto(savedCategory),
                HttpStatus.CREATED
        );
    }
}
