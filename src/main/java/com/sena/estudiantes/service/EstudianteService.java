package com.sena.estudiantes.service;

import com.sena.estudiantes.model.Estudiante;
import java.util.List;

public interface EstudianteService {

    List<Estudiante> listarTodos();

    Estudiante guardar(Estudiante estudiante);

    Estudiante buscarPorId(Long id);

    void eliminar(Long id);
}