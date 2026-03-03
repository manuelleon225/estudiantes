package com.sena.estudiantes.controller;

import com.sena.estudiantes.model.Estudiante;
import com.sena.estudiantes.service.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    // Inyección por constructor (buena práctica)
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // Método encargado de listar todos los estudiantes registrados
    @GetMapping
    public String listarEstudiantes(Model model) {
        model.addAttribute("listaEstudiantes", estudianteService.listarTodos());
        return "lista";
    }

    // Método encargado de mostrar el formulario con los datos del estudiante a editar
    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteService.buscarPorId(id);
        model.addAttribute("estudiante", estudiante);
        return "formulario";
    }

    // Método encargado de eliminar un estudiante por su ID
    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return "redirect:/estudiantes";
    }

    // Método encargado de mostrar el formulario para registrar un nuevo estudiante
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "formulario";
    }

    // Método encargado de guardar un estudiante (crear o actualizar)
    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteService.guardar(estudiante);
        return "redirect:/estudiantes";
    }
}