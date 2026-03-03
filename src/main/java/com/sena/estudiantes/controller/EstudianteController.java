package com.sena.estudiantes.controller;

import com.sena.estudiantes.model.Estudiante;
import com.sena.estudiantes.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    // Inyección por constructor (Buena práctica - Principio de Inversión de Dependencias)
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // ===============================
    // LISTAR TODOS LOS ESTUDIANTES
    // ===============================
    @GetMapping
    public String listarEstudiantes(Model model) {
        model.addAttribute("listaEstudiantes", estudianteService.listarTodos());
        return "lista";
    }

    // ===============================
    // MOSTRAR FORMULARIO NUEVO
    // ===============================
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "formulario";
    }

    // ===============================
    // EDITAR ESTUDIANTE
    // ===============================
    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteService.buscarPorId(id);
        model.addAttribute("estudiante", estudiante);
        return "formulario";
    }

    // ===============================
    // ELIMINAR ESTUDIANTE
    // ===============================
    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return "redirect:/estudiantes";
    }

    // ===============================
    // GUARDAR (CREAR O ACTUALIZAR)
    // ===============================
    @PostMapping("/guardar")
    public String guardarEstudiante(
            @Valid @ModelAttribute Estudiante estudiante, // Activa las validaciones de la entidad
            BindingResult result,                         // Contiene los errores de validación
            Model model) {

        // Si existen errores de validación
        if (result.hasErrors()) {
            // Regresa al formulario mostrando los errores
            return "formulario";
        }

        // Si no hay errores, guarda el estudiante
        estudianteService.guardar(estudiante);

        // Redirige a la lista
        return "redirect:/estudiantes";
    }
}