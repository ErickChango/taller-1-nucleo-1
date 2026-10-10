package com.hotel.gestionhotel.service;

import com.hotel.gestionhotel.model.Reserva;
import com.hotel.gestionhotel.model.Habitacion;
import com.hotel.gestionhotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;
    
    @Autowired
    private HabitacionService habitacionService;
    
    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }
    
    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }
    
    public List<Reserva> listarPorEstado(String estado) {
        return reservaRepository.findByEstado(estado);
    }
    
    public Reserva guardar(Reserva reserva) {
        reserva.setMontoTotal(reserva.calcularMontoTotal());
        
        if ("Confirmada".equals(reserva.getEstado())) {
            habitacionService.cambiarDisponibilidad(reserva.getHabitacion().getId(), false);
        }
        
        return reservaRepository.save(reserva);
    }
    
    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }
    
    public boolean verificarDisponibilidad(Long habitacionId, LocalDate fechaEntrada, LocalDate fechaSalida) {
        List<Reserva> conflictos = reservaRepository.findReservasConflictivas(habitacionId, fechaEntrada, fechaSalida);
        return conflictos.isEmpty();
    }
    
    public void confirmarReserva(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            reserva.get().setEstado("Confirmada");
            guardar(reserva.get());
        }
    }
    
    public void cancelarReserva(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            reserva.get().setEstado("Cancelada");
            habitacionService.cambiarDisponibilidad(reserva.get().getHabitacion().getId(), true);
            reservaRepository.save(reserva.get());
        }
    }
    
    public void completarReserva(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            reserva.get().setEstado("Completada");
            habitacionService.cambiarDisponibilidad(reserva.get().getHabitacion().getId(), true);
            reservaRepository.save(reserva.get());
        }
    }
    
    public double calcularIngresoTotal() {
        return listarTodas().stream()
            .filter(r -> !"Cancelada".equals(r.getEstado()))
            .mapToDouble(Reserva::getMontoTotal)
            .sum();
    }
}
