package com.gestionTienda.msTienda.service;

import com.gestionTienda.msTienda.model.PoliticaEmpresa;
import com.gestionTienda.msTienda.repository.PoliticaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Genera un constructor con los atributos final
public class PoliticaService {

    private final PoliticaRepository politicaRepository;

    /**
     * Retorna todas las políticas registradas en la base de datos.
     */
    public List<PoliticaEmpresa> listarPoliticas() {
        return politicaRepository.findAll();
    }

    /**
     * Busca una política por su ID.
     * @param id identificador de la política.
     * @return un Optional que puede contener la política encontrada.
     */
    public Optional<PoliticaEmpresa> obtenerPorId(int id) {
        return politicaRepository.findById(id);
    }

    /**
     * Guarda una nueva política en la base de datos.
     * @param politica entidad a guardar.
     * @return la política ya guardada con su ID asignado.
     */
    public PoliticaEmpresa guardar(PoliticaEmpresa politica) {
        return politicaRepository.save(politica);
    }

    /**
     * Actualiza la descripción de una política existente.
     * @param id identificador de la política a actualizar.
     * @param nuevaPolitica objeto con los nuevos datos.
     * @return Optional con la política actualizada si existía.
     */
    public Optional<PoliticaEmpresa> actualizar(int id, PoliticaEmpresa nuevaPolitica) {
        return politicaRepository.findById(id).map(p -> {
            p.setDescripcion(nuevaPolitica.getDescripcion());
            return politicaRepository.save(p);
        });
    }

    /**
     * Elimina una política por su ID, si existe.
     * @param id identificador de la política.
     * @return true si fue eliminada, false si no existía.
     */
    public boolean eliminar(int id) {
        if (politicaRepository.existsById(id)) {
            politicaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
