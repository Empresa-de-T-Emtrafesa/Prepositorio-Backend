package com.emtrafesa.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AsientoDTO {

    private Long id;  // El ID puede ser útil para operaciones como actualizar o eliminar asientos

    @Min(value = 1, message = "El número de asiento debe ser mayor que 0.")
    private int numeroAsiento;

    @Min(value = 1, message = "El número de piso debe ser mayor que 0.")
    private int piso;

    @NotBlank(message = "El estado del asiento no puede estar vacío.")
    private String estado;

    @NotNull(message = "El ID del bus es obligatorio.")
    private Long busId;
}
