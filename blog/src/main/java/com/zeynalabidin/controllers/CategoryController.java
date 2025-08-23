package com.zeynalabidin.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeynalabidin.domain.dtos.CategoryDto;
import com.zeynalabidin.mappers.CategoryMapper;
import com.zeynalabidin.services.CategoryService;

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
}
