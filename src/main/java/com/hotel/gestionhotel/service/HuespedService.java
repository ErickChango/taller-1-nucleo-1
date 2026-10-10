package com.hotel.gestionhotel.service;

import com.hotel.gestionhotel.model.Huesped;
import com.hotel.gestionhotel.repository.HuespedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HuespedService {
    
    @Autowired
    private HuespedRepository huespedRepository;
    
    public List<Huesped> listarTodos() {
        return huespedRepository.findAll();
    }
    
    public Optional<Huesped> buscarPorId(Long id) {
        return huespedRepository.findById(id);
    }
    
    public Optional<Huesped> buscarPorDocumento(String documento) {
        return huespedRepository.findByDocumento(documento);
    }
    
    public Huesped guardar(Huesped huesped) {
        return huespedRepository.save(huesped);
    }
    
    public void eliminar(Long id) {
        huespedRepository.deleteById(id);
    }
    
    public List<Huesped> buscarPorNombre(String termino) {
        return huespedRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(termino, termino);
    }
    
    public boolean existeDocumento(String documento) {
        return huespedRepository.findByDocumento(documento).isPresent();
    }
}
