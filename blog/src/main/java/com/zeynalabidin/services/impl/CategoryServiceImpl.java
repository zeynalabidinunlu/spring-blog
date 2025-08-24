package com.zeynalabidin.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.zeynalabidin.domain.entities.Category;
import com.zeynalabidin.repositories.CategoryRepository;
import com.zeynalabidin.services.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public List<Category> listCategories() {
		return categoryRepository.findAllWithPostCount();
	}

	@Override
	@Transactional
	public Category createCategory(Category category) {
		String categoryName = category.getName();
		if (categoryRepository.existsByNameIgnoreCase(categoryName)) {
			throw new IllegalArgumentException("Category already exists with name: " + categoryName);
		}
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(UUID id) {
		Optional<Category> category = categoryRepository.findById(id);

		if (category.isPresent()) {
			if (!category.get().getPosts().isEmpty()) {
				throw new IllegalStateException("Category has posts associated with it");
			}
			categoryRepository.deleteById(id);
		}
	}

}
