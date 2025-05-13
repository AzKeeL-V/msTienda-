package com.gestionTienda.msTienda.controller;

import com.gestionTienda.msTienda.model.PoliticaEmpresa;
import com.gestionTienda.msTienda.service.PoliticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/politicas")
@RequiredArgsConstructor
public class PoliticaController {

    private final PoliticaService politicaService;

    @GetMapping
    public List<PoliticaEmpresa> listar() {
        return politicaService.listarPoliticas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoliticaEmpresa> obtener(@PathVariable int id) {
        return politicaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PoliticaEmpresa crear(@RequestBody PoliticaEmpresa politica) {
        return politicaService.guardar(politica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoliticaEmpresa> actualizar(@PathVariable int id, @RequestBody PoliticaEmpresa nueva) {
        return politicaService.actualizar(id, nueva)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        return politicaService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
