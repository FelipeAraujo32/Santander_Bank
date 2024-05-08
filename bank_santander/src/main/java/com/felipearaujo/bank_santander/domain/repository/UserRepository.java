package com.felipearaujo.bank_santander.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipearaujo.bank_santander.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    
    boolean existsByAccountNumber(String accountNumber);

    boolean existsByCardNumber(String number);
}
