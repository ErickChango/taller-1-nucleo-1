package com.hotel.gestionhotel.service;

import com.hotel.gestionhotel.model.Habitacion;
import com.hotel.gestionhotel.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {
    
    @Autowired
    private HabitacionRepository habitacionRepository;
    
    public List<Habitacion> listarTodas() {
        return habitacionRepository.findAll();
    }
    
    public Optional<Habitacion> buscarPorId(Long id) {
        return habitacionRepository.findById(id);
    }
    
    public List<Habitacion> listarDisponibles() {
        return habitacionRepository.findByDisponible(true);
    }
    
    public List<Habitacion> buscarPorTipo(String tipo) {
        return habitacionRepository.findByTipo(tipo);
    }
    
    public Habitacion guardar(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }
    
    public void eliminar(Long id) {
        habitacionRepository.deleteById(id);
    }
    
    public void cambiarDisponibilidad(Long id, Boolean disponible) {
        Optional<Habitacion> habitacion = habitacionRepository.findById(id);
        if (habitacion.isPresent()) {
            habitacion.get().setDisponible(disponible);
            habitacionRepository.save(habitacion.get());
        }
    }
    
    public long contarDisponibles() {
        return listarDisponibles().size();
    }
    
    public double calcularValorTotalHabitaciones() {
        return listarTodas().stream()
            .mapToDouble(Habitacion::getPrecioPorNoche)
            .sum();
    }
}
