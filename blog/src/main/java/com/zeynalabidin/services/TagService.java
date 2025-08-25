package com.zeynalabidin.services;

import java.util.List;
import java.util.Set;

import com.zeynalabidin.domain.entities.Tag;

public interface TagService {

	List<Tag> getTags();
	
	List<Tag> createTags(Set<String> tagNames);
}
