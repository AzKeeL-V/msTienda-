package com.gestionTienda.msTienda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalTime; // ¡Importa esto!
import java.util.List;

@Entity
@Table(name = "tiendas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTienda;

    private String nomTienda;
    private String dirTienda;
    private LocalTime horaEntrada; 
    private LocalTime horaSalida;  
    @ManyToMany
    @JoinTable(
            name = "tienda_politica",
            joinColumns = @JoinColumn(name = "tienda_id"),
            inverseJoinColumns = @JoinColumn(name = "politica_id")
    )
    private List<PoliticaEmpresa> listaPoliticas;
}