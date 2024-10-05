package com.emtrafesa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.emtrafesa.model.entity.PasswordResetToken;
import com.emtrafesa.model.entity.UserEmtraf;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    void deleteByUser(UserEmtraf user);
}