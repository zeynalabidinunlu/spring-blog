package com.zeynalabidin.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zeynalabidin.domain.entities.Category;
import com.zeynalabidin.repositories.CategoryRepository;
import com.zeynalabidin.services.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public List<Category> listCategories() {
		return categoryRepository.findAllWithPostCount();
	}

}
