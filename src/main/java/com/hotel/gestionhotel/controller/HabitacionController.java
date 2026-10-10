package com.hotel.gestionhotel.controller;

import com.hotel.gestionhotel.model.Habitacion;
import com.hotel.gestionhotel.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {
    
    @Autowired
    private HabitacionService habitacionService;
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("habitaciones", habitacionService.listarTodas());
        return "habitaciones/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("habitacion", new Habitacion());
        return "habitaciones/formulario";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        habitacionService.buscarPorId(id).ifPresent(habitacion -> 
            model.addAttribute("habitacion", habitacion)
        );
        return "habitaciones/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Habitacion habitacion, RedirectAttributes redirectAttributes) {
        habitacionService.guardar(habitacion);
        redirectAttributes.addFlashAttribute("mensaje", "Habitación guardada exitosamente");
        return "redirect:/habitaciones";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        habitacionService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Habitación eliminada exitosamente");
        return "redirect:/habitaciones";
    }
    
    @GetMapping("/disponibilidad/{id}/{disponible}")
    public String cambiarDisponibilidad(@PathVariable Long id, @PathVariable Boolean disponible, RedirectAttributes redirectAttributes) {
        habitacionService.cambiarDisponibilidad(id, disponible);
        redirectAttributes.addFlashAttribute("mensaje", "Disponibilidad actualizada");
        return "redirect:/habitaciones";
    }
}
