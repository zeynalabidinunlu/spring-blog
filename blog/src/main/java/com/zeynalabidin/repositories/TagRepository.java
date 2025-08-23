package com.zeynalabidin.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zeynalabidin.domain.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {

}
