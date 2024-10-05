package com.emtrafesa.service;

import com.emtrafesa.model.entity.Asiento;
import com.emtrafesa.repository.AsientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

//
@Service
public class AsientoReservationScheduler {

    @Autowired
    private AsientoRepository asientoRepository;

    // Revisa cada 10 minutos si hay asientos reservados que no se han pagado
    @Scheduled(fixedRate = 600000)  // 10 minutos
    public void liberarReservas() {
        List<Asiento> asientosReservados = asientoRepository.findByEstado("RESERVADO");
        for (Asiento asiento : asientosReservados) {
            // Lógica para verificar si la reserva ha expirado y liberar el asiento si es necesario
            if (haExpirado(asiento)) {
                asiento.setEstado("DISPONIBLE");
                asientoRepository.save(asiento);
            }
        }
    }

    private boolean haExpirado(Asiento asiento) {
        // Lógica para determinar si la reserva ha pasado el tiempo permitido
        return true;  // Simplificación para el ejemplo
    }
}
