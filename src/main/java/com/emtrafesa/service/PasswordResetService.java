package com.emtrafesa.service;

import com.emtrafesa.dto.ResetPasswordRequestDTO;
import com.emtrafesa.model.entity.UserEmtraf;
import com.emtrafesa.repository.UserEmtrafRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserEmtrafRepository userEmtrafRepository;

    @Autowired
    public PasswordResetService(UserEmtrafRepository userEmtrafRepository) {
        this.userEmtrafRepository = userEmtrafRepository;
    }

    public String generateResetLink(ResetPasswordRequestDTO request) {
        String correo = request.getCorreo();
        Optional<UserEmtraf> userOpt = userEmtrafRepository.findByCorreo(correo);
        
        if (userOpt.isPresent()) {
            UserEmtraf user = userOpt.get();
            String token = UUID.randomUUID().toString(); // Genera un token único
            user.setResetToken(token);
            user.setTokenExpiration(LocalDateTime.now().plusHours(1)); // Token válido por 1 hora
            userEmtrafRepository.save(user);

            // Enviar correo al usuario con el token
            sendResetEmail(correo, token);

            return "Link de restablecimiento enviado a " + correo; // Incluye el token en la respuesta
        } else {
            return "El correo no está registrado."; // Manejo simple de error
        }
    }

    public String resetPassword(String token, String newPassword) {
        Optional<UserEmtraf> userOpt = userEmtrafRepository.findByResetToken(token);
        if (userOpt.isPresent() && userOpt.get().getTokenExpiration().isAfter(LocalDateTime.now())) {
            UserEmtraf user = userOpt.get();
            user.setContrasena(newPassword); // Actualiza la contraseña
            user.setResetToken(null); // Limpia el token
            user.setTokenExpiration(null); // Limpia la expiración del token
            userEmtrafRepository.save(user);
            return "Contraseña restablecida exitosamente.";
        } else {
            return "Token inválido o ha expirado.";
        }
    }

    private void sendResetEmail(String correo, String token) {
        // Aquí iría la lógica para enviar el correo electrónico
        // Puedes utilizar JavaMailSender o cualquier otra biblioteca para enviar correos
        System.out.println("Enviando correo a " + correo + " con el token: " + token);
    }
}
