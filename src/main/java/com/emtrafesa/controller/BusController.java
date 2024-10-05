package com.emtrafesa.controller;

import com.emtrafesa.model.entity.Bus;
import com.emtrafesa.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buses")
public class BusController {

    private final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping
    public ResponseEntity<String> createBus(@RequestBody Bus bus) {
        busService.saveBus(bus);
        return ResponseEntity.ok("El bus fue registrado exitosamente.");
    }

    // Nuevo endpoint para listar todos los buses
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }
}



