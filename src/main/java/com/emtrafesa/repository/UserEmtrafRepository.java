package com.emtrafesa.repository;

import com.emtrafesa.model.entity.UserEmtraf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEmtrafRepository extends JpaRepository<UserEmtraf, Long> {
    Optional<UserEmtraf> findByCorreo(String correo);
    Optional<UserEmtraf> findByResetToken(String token);
}
