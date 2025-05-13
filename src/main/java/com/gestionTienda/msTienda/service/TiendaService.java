package com.gestionTienda.msTienda.service;

import com.gestionTienda.msTienda.model.Tienda;
import com.gestionTienda.msTienda.repository.TiendaRepository; // Asegúrate de importar el repositorio
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TiendaService {

    private final TiendaRepository tiendaRepository; // Inyecta el repositorio usando Lombok

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