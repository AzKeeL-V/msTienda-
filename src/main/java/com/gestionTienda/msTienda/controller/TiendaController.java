package com.gestionTienda.msTienda.controller;

import com.gestionTienda.msTienda.model.Tienda;
import com.gestionTienda.msTienda.service.TiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tiendas")
@RequiredArgsConstructor
public class TiendaController {

    private final TiendaService tiendaService;

    @GetMapping
    public ResponseEntity<List<Tienda>> listarTiendas() {
        return ResponseEntity.ok(tiendaService.listarTiendas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tienda> obtenerTiendaPorId(@PathVariable int id) {
        Optional<Tienda> tienda = tiendaService.obtenerTiendaPorId(id);
        return tienda.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tienda> crearTienda(@RequestBody Tienda tienda) {
        Tienda nuevaTienda = tiendaService.guardarTienda(tienda);
        return new ResponseEntity<>(nuevaTienda, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tienda> actualizarTienda(@PathVariable int id, @RequestBody Tienda tiendaActualizada) {
        Optional<Tienda> tienda = tiendaService.actualizarTienda(id, tiendaActualizada);
        return tienda.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTienda(@PathVariable int id) {
        if (tiendaService.eliminarTienda(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoints para las funcionalidades específicas (establecer horarios, asignar/sacar políticas)
    @PutMapping("/{id}/hora-entrada")
    public ResponseEntity<Tienda> establecerHoraEntrada(@PathVariable int id, @RequestBody String horaEntrada) {
        // y llamar al método del servicio
        return ResponseEntity.ok().build(); // Placeholder
    }

    @PutMapping("/{id}/hora-salida")
    public ResponseEntity<Tienda> establecerHoraSalida(@PathVariable int id, @RequestBody String horaSalida) {
        // Similar a establecerHoraEntrada
        return ResponseEntity.ok().build(); // Placeholder
    }

    @PostMapping("/{tiendaId}/politicas/{politicaId}")
    public ResponseEntity<Tienda> asignarPolitica(@PathVariable int tiendaId, @PathVariable int politicaId) {
        Optional<Tienda> tienda = tiendaService.asignarPolitica(tiendaId, politicaId);
        return tienda.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{tiendaId}/politicas/{politicaId}")
    public ResponseEntity<Tienda> sacarPolitica(@PathVariable int tiendaId, @PathVariable int politicaId) {
        Optional<Tienda> tienda = tiendaService.sacarPolitica(tiendaId, politicaId);
        return tienda.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}