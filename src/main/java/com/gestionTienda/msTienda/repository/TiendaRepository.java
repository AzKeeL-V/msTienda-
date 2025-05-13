package com.gestionTienda.msTienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestionTienda.msTienda.model.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Integer> {
    
}