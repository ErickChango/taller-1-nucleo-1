package com.hotel.gestionhotel.controller;

import com.hotel.gestionhotel.model.Huesped;
import com.hotel.gestionhotel.service.HuespedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/huespedes")
public class HuespedController {
    
    @Autowired
    private HuespedService huespedService;
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("huespedes", huespedService.listarTodos());
        return "huespedes/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("huesped", new Huesped());
        return "huespedes/formulario";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        huespedService.buscarPorId(id).ifPresent(huesped -> 
            model.addAttribute("huesped", huesped)
        );
        return "huespedes/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Huesped huesped, RedirectAttributes redirectAttributes) {
        huespedService.guardar(huesped);
        redirectAttributes.addFlashAttribute("mensaje", "Huésped guardado exitosamente");
        return "redirect:/huespedes";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        huespedService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Huésped eliminado exitosamente");
        return "redirect:/huespedes";
    }
    
    @GetMapping("/buscar")
    public String buscar(@RequestParam String termino, Model model) {
        model.addAttribute("huespedes", huespedService.buscarPorNombre(termino));
        return "huespedes/lista";
    }
}
