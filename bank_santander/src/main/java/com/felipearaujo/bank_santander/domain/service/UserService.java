package com.felipearaujo.bank_santander.domain.service;

import java.util.List;
import java.util.UUID;

import com.felipearaujo.bank_santander.domain.model.User;

public interface UserService {

    public List<User> findAll();
    
    public User findById(UUID uuid);

    public User create(User userToCreate);

    public User update(UUID uuid, User user);

    public void delete(UUID uuid);
}
