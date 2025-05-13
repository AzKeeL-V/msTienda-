package com.gestionTienda.msTienda.repository;

import com.gestionTienda.msTienda.model.PoliticaEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticaRepository extends JpaRepository<PoliticaEmpresa, Integer> {
}