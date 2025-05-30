package com.gestionTienda.msTienda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode; // Importa la anotación
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "politicas_empresa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idPolitica") // Añade esta línea
public class PoliticaEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPolitica;

    private String descripcion;

}