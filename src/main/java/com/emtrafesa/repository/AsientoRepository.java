package com.emtrafesa.repository;

import com.emtrafesa.model.entity.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Long> {
    // Este método busca los asientos por su estado (DISPONIBLE, RESERVADO, OCUPADO, etc.)
    List<Asiento> findByEstado(String estado);
    List<Asiento> findByBus_Id(Long busId);

}
