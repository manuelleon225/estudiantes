package com.sena.estudiantes.controller;

import com.sena.estudiantes.model.Estudiante;
import com.sena.estudiantes.repository.EstudianteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteRepository estudianteRepository;

    // Inyección por constructor (buena práctica)
    public EstudianteController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    // LISTAR ESTUDIANTES
    @GetMapping
    public String listarEstudiantes(Model model) {
        model.addAttribute("listaEstudiantes", estudianteRepository.findAll());
        return "lista";
    }
    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("estudiante", estudiante);
        return "formulario";
    }

    // MOSTRAR FORMULARIO
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "formulario";
    }

    // GUARDAR ESTUDIANTE
    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes";
    }
}