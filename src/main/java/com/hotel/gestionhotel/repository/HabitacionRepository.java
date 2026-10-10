package com.hotel.gestionhotel.repository;

import com.hotel.gestionhotel.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    
    Optional<Habitacion> findByNumeroHabitacion(String numeroHabitacion);
    
    List<Habitacion> findByDisponible(Boolean disponible);
    
    List<Habitacion> findByTipo(String tipo);
    
    List<Habitacion> findByDisponibleAndTipo(Boolean disponible, String tipo);
}
