package org.fgarcia.mockitoapp.ejemplos.services;

import org.fgarcia.mockitoapp.ejemplos.Datos;
import org.fgarcia.mockitoapp.ejemplos.models.Examen;
import org.fgarcia.mockitoapp.ejemplos.repositories.ExamenRepository;
import org.fgarcia.mockitoapp.ejemplos.repositories.ExamenRepositoryImpl;
import org.fgarcia.mockitoapp.ejemplos.repositories.PreguntaRepository;
import org.fgarcia.mockitoapp.ejemplos.repositories.PreguntasRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamenServiceImplSpyTest {
    @Spy
    ExamenRepositoryImpl repository;

    @Spy
    PreguntasRepositoryImpl preguntaRepository;

    @InjectMocks
    ExamenServiceImpl service;

    @Test
    void testSpy() {
        List<String> preguntas = Arrays.asList("Aritmetica");
//        when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(preguntas);
        doReturn(preguntas).when(preguntaRepository).findPreguntasPorExamenId(anyLong());

        Examen examen = service.findExamenPorNombreConPreguntas("Matematicas");

        assertEquals(5L,examen.getId());
        assertEquals("Matematicas", examen.getNombre());
        assertEquals(1, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Aritmetica"));

        verify(repository).findAll();
        verify(preguntaRepository).findPreguntasPorExamenId(anyLong());
    }
}
