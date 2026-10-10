package com.hotel.gestionhotel.repository;

import com.hotel.gestionhotel.model.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Long> {
    
    Optional<Huesped> findByDocumento(String documento);
    
    Optional<Huesped> findByEmail(String email);
    
    List<Huesped> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);
}
