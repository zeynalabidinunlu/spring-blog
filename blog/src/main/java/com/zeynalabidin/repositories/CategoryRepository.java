package com.zeynalabidin.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeynalabidin.domain.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID>{

}
