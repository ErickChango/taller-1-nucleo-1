package com.hotel.gestionhotel.controller;

import com.hotel.gestionhotel.service.HabitacionService;
import com.hotel.gestionhotel.service.HuespedService;
import com.hotel.gestionhotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private HabitacionService habitacionService;
    
    @Autowired
    private HuespedService huespedService;
    
    @Autowired
    private ReservaService reservaService;
    
    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("totalHabitaciones", habitacionService.listarTodas().size());
        model.addAttribute("habitacionesDisponibles", habitacionService.contarDisponibles());
        model.addAttribute("totalHuespedes", huespedService.listarTodos().size());
        model.addAttribute("totalReservas", reservaService.listarTodas().size());
        model.addAttribute("ingresoTotal", reservaService.calcularIngresoTotal());
        
        return "index";
    }
}
