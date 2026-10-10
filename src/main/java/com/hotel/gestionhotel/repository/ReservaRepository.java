package com.hotel.gestionhotel.repository;

import com.hotel.gestionhotel.model.Reserva;
import com.hotel.gestionhotel.model.Habitacion;
import com.hotel.gestionhotel.model.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    List<Reserva> findByHuesped(Huesped huesped);
    
    List<Reserva> findByHabitacion(Habitacion habitacion);
    
    List<Reserva> findByEstado(String estado);
    
    @Query("SELECT r FROM Reserva r WHERE r.habitacion.id = :habitacionId " +
           "AND r.estado != 'Cancelada' " +
           "AND ((r.fechaEntrada <= :fechaSalida AND r.fechaSalida >= :fechaEntrada))")
    List<Reserva> findReservasConflictivas(
        @Param("habitacionId") Long habitacionId,
        @Param("fechaEntrada") LocalDate fechaEntrada,
        @Param("fechaSalida") LocalDate fechaSalida
    );
    
    List<Reserva> findByFechaEntradaBetween(LocalDate inicio, LocalDate fin);
}
