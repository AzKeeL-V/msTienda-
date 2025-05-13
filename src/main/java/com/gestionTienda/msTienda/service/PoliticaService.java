package com.gestionTienda.msTienda.service;

import com.gestionTienda.msTienda.model.PoliticaEmpresa;
import com.gestionTienda.msTienda.repository.PoliticaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PoliticaService {

    private final PoliticaRepository politicaRepository;

    public List<PoliticaEmpresa> listarPoliticas() {
        return politicaRepository.findAll();
    }

    public Optional<PoliticaEmpresa> obtenerPorId(int id) {
        return politicaRepository.findById(id);
    }

    public PoliticaEmpresa guardar(PoliticaEmpresa politica) {
        return politicaRepository.save(politica);
    }

    public Optional<PoliticaEmpresa> actualizar(int id, PoliticaEmpresa nuevaPolitica) {
        return politicaRepository.findById(id).map(p -> {
            p.setDescripcion(nuevaPolitica.getDescripcion());
            return politicaRepository.save(p);
        });
    }

    public boolean eliminar(int id) {
        if (politicaRepository.existsById(id)) {
            politicaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

