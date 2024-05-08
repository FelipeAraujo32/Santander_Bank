package com.felipearaujo.bank_santander.domain.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipearaujo.bank_santander.domain.model.User;
import com.felipearaujo.bank_santander.domain.repository.UserRepository;
import com.felipearaujo.bank_santander.domain.service.UserService;
import com.felipearaujo.bank_santander.domain.service.exception.BusinessException;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public User create(User userToCreate) {
        ofNullable(userToCreate).orElseThrow(() -> new BusinessException("User to create must not be null."));
        ofNullable(userToCreate.getAccount())
                .orElseThrow(() -> new BusinessException("User account must not be null."));
        ofNullable(userToCreate.getCard()).orElseThrow(() -> new BusinessException("User card must not be null."));

        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new BusinessException("This Account number already exists.");
        }

        if (userRepository.existsByCardNumber(userToCreate.getCard().getNumber())) {
            throw new BusinessException("This card number already exists.");
        }

        return userRepository.save(userToCreate);
    }

    @Override
    @Transactional
    public User update(UUID uuid, User userToUpdate) {
        User dbUser = this.findById(uuid);

        // Validation to check if the IDs are the same.
        if (!dbUser.getId().equals(userToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }

        dbUser.setName(userToUpdate.getName());
        dbUser.setAccount(userToUpdate.getAccount());
        dbUser.setCard(userToUpdate.getCard());
        dbUser.setFeature(userToUpdate.getFeature());
        dbUser.setNews(userToUpdate.getNews());

        return this.userRepository.save(dbUser);
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        User dbUser = this.findById(uuid);
        this.userRepository.delete(dbUser);
    }

}
