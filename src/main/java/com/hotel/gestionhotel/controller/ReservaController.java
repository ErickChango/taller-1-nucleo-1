package com.hotel.gestionhotel.controller;

import com.hotel.gestionhotel.model.Reserva;
import com.hotel.gestionhotel.service.ReservaService;
import com.hotel.gestionhotel.service.HabitacionService;
import com.hotel.gestionhotel.service.HuespedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;
    
    @Autowired
    private HabitacionService habitacionService;
    
    @Autowired
    private HuespedService huespedService;
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("reservas", reservaService.listarTodas());
        return "reservas/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("habitaciones", habitacionService.listarDisponibles());
        model.addAttribute("huespedes", huespedService.listarTodos());
        return "reservas/formulario";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        reservaService.buscarPorId(id).ifPresent(reserva -> {
            model.addAttribute("reserva", reserva);
            model.addAttribute("habitaciones", habitacionService.listarTodas());
            model.addAttribute("huespedes", huespedService.listarTodos());
        });
        return "reservas/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Reserva reserva, RedirectAttributes redirectAttributes) {
        boolean disponible = reservaService.verificarDisponibilidad(
            reserva.getHabitacion().getId(),
            reserva.getFechaEntrada(),
            reserva.getFechaSalida()
        );
        
        if (!disponible && reserva.getId() == null) {
            redirectAttributes.addFlashAttribute("error", "La habitación no está disponible en esas fechas");
            return "redirect:/reservas/nuevo";
        }
        
        reservaService.guardar(reserva);
        redirectAttributes.addFlashAttribute("mensaje", "Reserva guardada exitosamente");
        return "redirect:/reservas";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        reservaService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Reserva eliminada exitosamente");
        return "redirect:/reservas";
    }
    
    @GetMapping("/confirmar/{id}")
    public String confirmar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        reservaService.confirmarReserva(id);
        redirectAttributes.addFlashAttribute("mensaje", "Reserva confirmada");
        return "redirect:/reservas";
    }
    
    @GetMapping("/cancelar/{id}")
    public String cancelar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        reservaService.cancelarReserva(id);
        redirectAttributes.addFlashAttribute("mensaje", "Reserva cancelada");
        return "redirect:/reservas";
    }
    
    @GetMapping("/completar/{id}")
    public String completar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        reservaService.completarReserva(id);
        redirectAttributes.addFlashAttribute("mensaje", "Reserva completada");
        return "redirect:/reservas";
    }
}
