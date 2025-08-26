package com.zeynalabidin.services.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.zeynalabidin.domain.entities.User;
import com.zeynalabidin.repositories.UserRepository;
import com.zeynalabidin.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public User getUserById(UUID id) {

		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id" + id));
	}

}
