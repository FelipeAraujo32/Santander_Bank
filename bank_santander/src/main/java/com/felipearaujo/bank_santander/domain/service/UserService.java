package com.felipearaujo.bank_santander.domain.service;

import java.util.UUID;

import com.felipearaujo.bank_santander.domain.model.User;

public interface UserService {
    
    public User findById(UUID uuid);

    public User create(User userToCreate);
}
