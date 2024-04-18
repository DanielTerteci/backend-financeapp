package com.finance.app.repository;

import com.finance.app.models.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register,Integer> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Register> findByUsernameAndPassword(String username, String password);
    Optional<Register> findByEmail(String email);
    Optional<Register> findByUsername(String userName);
}
