package com.emtrafesa.model.entity;

import com.emtrafesa.model.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "user_emtraf")
public class UserEmtraf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_emtraf")
    private Long id;

    @Column(name = "correo",length = 180, nullable = false, unique = true)
    private String correo;

    @Column(name = "contrasena", length = 150, nullable = false)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", length = 10)
    private TipoUsuario tipoUsuario;

    @Column(name = "reset_token", length = 255)
    private String resetToken;

    @Column(name = "token_expiration")
    private LocalDateTime tokenExpiration;

}
