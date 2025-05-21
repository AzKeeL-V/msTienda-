package com.gestionTienda.msTienda.service;

import com.gestionTienda.msTienda.model.PoliticaEmpresa;
import com.gestionTienda.msTienda.model.Tienda;
import com.gestionTienda.msTienda.repository.PoliticaRepository; // Cambiado el nombre a PoliticaRepository
import com.gestionTienda.msTienda.repository.TiendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TiendaService {

    private final TiendaRepository tiendaRepository;
    private final PoliticaRepository politicaRepository; // Inyecta el PoliticaRepository

    @Transactional
    public Optional<Tienda> asignarPolitica(int tiendaId, int politicaId) {
        Optional<Tienda> tiendaOptional = tiendaRepository.findById(tiendaId);
        Optional<PoliticaEmpresa> politicaOptional = politicaRepository.findById(politicaId);

        if (tiendaOptional.isPresent() && politicaOptional.isPresent()) {
            Tienda tienda = tiendaOptional.get();
            PoliticaEmpresa politica = politicaOptional.get();
            tienda.getListaPoliticas().add(politica);
            return Optional.of(tiendaRepository.save(tienda));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<Tienda> sacarPolitica(int tiendaId, int politicaId) {
        Optional<Tienda> tiendaOptional = tiendaRepository.findById(tiendaId);
        Optional<PoliticaEmpresa> politicaOptional = politicaRepository.findById(politicaId);

        if (tiendaOptional.isPresent() && politicaOptional.isPresent()) {
            Tienda tienda = tiendaOptional.get();
            PoliticaEmpresa politica = politicaOptional.get();
            tienda.getListaPoliticas().remove(politica);
            return Optional.of(tiendaRepository.save(tienda));
        } else {
            return Optional.empty();
        }
    }

    public List<Tienda> listarTiendas() {
        return tiendaRepository.findAll();
    }

    public Optional<Tienda> obtenerTiendaPorId(int id) {
        return tiendaRepository.findById(id);
    }

    public Tienda guardarTienda(Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    public Optional<Tienda> actualizarTienda(int id, Tienda tiendaDatosActualizados) {
        return tiendaRepository.findById(id)
                .map(t -> {
                    t.setNomTienda(tiendaDatosActualizados.getNomTienda());
                    t.setDirTienda(tiendaDatosActualizados.getDirTienda());
                    t.setHoraEntrada(tiendaDatosActualizados.getHoraEntrada());
                    t.setHoraSalida(tiendaDatosActualizados.getHoraSalida());
                    return tiendaRepository.save(t);
                });
    }

    public boolean eliminarTienda(int id) {
        if (tiendaRepository.existsById(id)) {
            tiendaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}