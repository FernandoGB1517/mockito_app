package org.fgarcia.mockitoapp.ejemplos.repositories;

import org.fgarcia.mockitoapp.ejemplos.models.Examen;

import java.util.Arrays;
import java.util.List;

public interface ExamenRepository {
    Examen guardar(Examen examen);
    List<Examen> findAll();
}
