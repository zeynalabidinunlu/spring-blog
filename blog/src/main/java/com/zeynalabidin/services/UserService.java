package com.zeynalabidin.services;



import java.util.UUID;

import com.zeynalabidin.domain.entities.User;

public interface UserService {
    User getUserById(UUID id);
}
