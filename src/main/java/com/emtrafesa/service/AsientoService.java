package com.emtrafesa.service;

import com.emtrafesa.dto.AsientoDTO;
import com.emtrafesa.model.entity.Asiento;
import com.emtrafesa.repository.AsientoRepository;
import com.emtrafesa.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepository;

    // Obtener todos los asientos por bus ID
    public List<AsientoDTO> getAsientosByBusId(Long busId) {
        List<Asiento> asientos = asientoRepository.findByBus_Id(busId);
        if (asientos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron asientos para el bus con ID " + busId);
        }
        return asientos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Reservar un asiento
    public AsientoDTO reservarAsiento(Long asientoId) {
        Asiento asiento = asientoRepository.findById(asientoId)
                .orElseThrow(() -> new ResourceNotFoundException("Asiento no encontrado con ID " + asientoId));

        if ("DISPONIBLE".equals(asiento.getEstado())) {
            asiento.setEstado("RESERVADO");
            asientoRepository.save(asiento);
        } else {
            throw new IllegalStateException("El asiento no está disponible para reservar");
        }
        return convertToDto(asiento);
    }

    // Ocupar un asiento después del pago
    public AsientoDTO ocuparAsiento(Long asientoId) {
        Asiento asiento = asientoRepository.findById(asientoId)
                .orElseThrow(() -> new ResourceNotFoundException("Asiento no encontrado con ID " + asientoId));

        if ("RESERVADO".equals(asiento.getEstado())) {
            asiento.setEstado("OCUPADO");
            asientoRepository.save(asiento);
        } else {
            throw new IllegalStateException("El asiento no puede ser ocupado en su estado actual");
        }
        return convertToDto(asiento);
    }

    // Cancelar la reserva si no se completa el pago
    public AsientoDTO cancelarReserva(Long asientoId) {
        Asiento asiento = asientoRepository.findById(asientoId)
                .orElseThrow(() -> new ResourceNotFoundException("Asiento no encontrado con ID " + asientoId));

        if ("RESERVADO".equals(asiento.getEstado())) {
            asiento.setEstado("DISPONIBLE");
            asientoRepository.save(asiento);
        } else {
            throw new IllegalStateException("Solo se pueden cancelar reservas");
        }
        return convertToDto(asiento);
    }

    // Convertir la entidad a DTO
    private AsientoDTO convertToDto(Asiento asiento) {
        try {
            AsientoDTO asientoDTO = new AsientoDTO();
            asientoDTO.setId(asiento.getId());
            asientoDTO.setNumeroAsiento(asiento.getNumeroAsiento());
            asientoDTO.setPiso(asiento.getPiso());
            asientoDTO.setEstado(asiento.getEstado());

            if (asiento.getBus() == null) {
                throw new ResourceNotFoundException("El asiento con ID " + asiento.getId() + " no está asociado a ningún bus.");
            } else {
                asientoDTO.setBusId(asiento.getBus().getId());
            }
            return asientoDTO;
        } catch (Exception ex) {
            ex.printStackTrace();  // Esto imprimirá el error en los logs del servidor
            throw ex;  // Vuelve a lanzar la excepción para que sea manejada por el GlobalExceptionHandler
        }
    }



}
