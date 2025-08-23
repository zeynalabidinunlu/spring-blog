package com.zeynalabidin.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zeynalabidin.domain.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID>{

}
