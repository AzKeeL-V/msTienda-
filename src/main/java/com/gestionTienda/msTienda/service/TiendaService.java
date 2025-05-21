package com.gestionTienda.msTienda.service;

import com.gestionTienda.msTienda.model.PoliticaEmpresa;
import com.gestionTienda.msTienda.model.Tienda;
import com.gestionTienda.msTienda.repository.PoliticaRepository;
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
    private final PoliticaRepository politicaRepository;

    /**
     * Asigna una política a una tienda, siempre que ambas existan y la política no esté repetida.
     */
    @Transactional
    public Optional<Tienda> asignarPolitica(int tiendaId, int politicaId) {
        // Busca la tienda y la política en sus respectivos repositorios
        Optional<Tienda> tiendaOptional = tiendaRepository.findById(tiendaId);
        Optional<PoliticaEmpresa> politicaOptional = politicaRepository.findById(politicaId);

        // Verifica si ambas entidades fueron encontradas
        if (tiendaOptional.isPresent() && politicaOptional.isPresent()) {
            Tienda tienda = tiendaOptional.get();
            PoliticaEmpresa politica = politicaOptional.get();

            // Solo agrega la política si aún no está en la lista de la tienda
            if (!tienda.getListaPoliticas().contains(politica)) {
                tienda.getListaPoliticas().add(politica);
                return Optional.of(tiendaRepository.save(tienda)); // Guarda y retorna la tienda actualizada
            } else {
                return Optional.of(tienda); // Ya existe, retorna la tienda sin cambios
            }
        } else {
            return Optional.empty(); // Una o ambas entidades no existen
        }
    }

    /**
     * Elimina una política asociada a una tienda.
     */
    @Transactional
    public void sacarPolitica(Integer idTienda, Integer idPolitica) {
        // Busca la tienda, lanza excepción si no existe
        Tienda tienda = tiendaRepository.findById(idTienda)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));

        // Busca la política, lanza excepción si no existe
        PoliticaEmpresa politica = politicaRepository.findById(idPolitica)
                .orElseThrow(() -> new RuntimeException("Política no encontrada"));

        // Elimina la política de la lista de la tienda y guarda los cambios
        tienda.getListaPoliticas().remove(politica);
        tiendaRepository.save(tienda);
    }

    /**
     * Retorna todas las tiendas registradas.
     */
    public List<Tienda> listarTiendas() {
        return tiendaRepository.findAll();
    }

    /**
     * Retorna una tienda por su ID, si existe.
     */
    public Optional<Tienda> obtenerTiendaPorId(int id) {
        return tiendaRepository.findById(id);
    }

    /**
     * Guarda una nueva tienda.
     */
    public Tienda guardarTienda(Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    /**
     * Actualiza los datos de una tienda si existe.
     */
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

    /**
     * Elimina una tienda por ID, si existe.
     */
    public boolean eliminarTienda(int id) {
        if (tiendaRepository.existsById(id)) {
            tiendaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
