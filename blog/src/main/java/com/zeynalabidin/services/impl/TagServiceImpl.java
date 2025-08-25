package com.zeynalabidin.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zeynalabidin.domain.entities.Tag;
import com.zeynalabidin.repositories.TagRepository;
import com.zeynalabidin.services.TagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

	private final TagRepository tagRepository;

	@Override
	public List<Tag> getTags() {

		return tagRepository.findAllWithPostCount();
	}

}
