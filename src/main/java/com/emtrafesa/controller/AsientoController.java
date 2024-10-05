package com.emtrafesa.controller;

import com.emtrafesa.dto.AsientoDTO;
import com.emtrafesa.service.AsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asientos")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;

    // Obtener todos los asientos por ID de bus
    @GetMapping("/itinerario/{busId}")
    public List<AsientoDTO> getAsientosByBusId(@PathVariable Long busId) {
        return asientoService.getAsientosByBusId(busId);
    }

    // Reservar un asiento
    @PutMapping("/reservar/{asientoId}")
    public AsientoDTO reservarAsiento(@PathVariable Long asientoId) {
        return asientoService.reservarAsiento(asientoId);
    }

    // Ocupar un asiento después de pago
    @PutMapping("/ocupar/{asientoId}")
    public AsientoDTO ocuparAsiento(@PathVariable Long asientoId) {
        return asientoService.ocuparAsiento(asientoId);
    }

    // Cancelar la reserva si no se completa el pago
    @PutMapping("/cancelarReserva/{asientoId}")
    public AsientoDTO cancelarReserva(@PathVariable Long asientoId) {
        return asientoService.cancelarReserva(asientoId);
    }
}
