package com.gestionTienda.msTienda.controller;

import com.gestionTienda.msTienda.model.PoliticaEmpresa;
import com.gestionTienda.msTienda.service.PoliticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/politicas")
@RequiredArgsConstructor
public class PoliticaController {

    private final PoliticaService politicaService;

    @GetMapping
    public ResponseEntity<List<PoliticaEmpresa>> listar() {
        List<PoliticaEmpresa> politicas = politicaService.listarPoliticas();
        if (politicas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content si no hay políticas
        }
        return new ResponseEntity<>(politicas, HttpStatus.OK); // 200 OK con la lista de políticas
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoliticaEmpresa> obtener(@PathVariable int id) {
        return politicaService.obtenerPorId(id)
                .map(politica -> new ResponseEntity<>(politica, HttpStatus.OK)) // 200 OK si se encuentra la política
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found si no se encuentra la política
    }

    @PostMapping
    public ResponseEntity<PoliticaEmpresa> crear(@RequestBody PoliticaEmpresa politica) {
        PoliticaEmpresa nuevaPolitica = politicaService.guardar(politica);
        return new ResponseEntity<>(nuevaPolitica, HttpStatus.CREATED); // 201 Created con la política creada
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoliticaEmpresa> actualizar(@PathVariable int id, @RequestBody PoliticaEmpresa nueva) {
        return politicaService.actualizar(id, nueva)
                .map(politicaActualizada -> new ResponseEntity<>(politicaActualizada, HttpStatus.OK)) // 200 OK si se actualiza la política
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found si no se encuentra la política a actualizar
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        boolean eliminada = politicaService.eliminar(id);
        if (eliminada) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content si la política se eliminó exitosamente
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found si no se encontró la política a eliminar
        }
    }
}