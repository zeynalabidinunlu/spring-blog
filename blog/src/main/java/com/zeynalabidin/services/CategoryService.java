package com.zeynalabidin.services;

import java.util.List;
import java.util.UUID;

import com.zeynalabidin.domain.entities.Category;


public interface CategoryService {

	List<Category> listCategories();
	Category createCategory(Category category);
	
	void deleteCategory(UUID id);
}
