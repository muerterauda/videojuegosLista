package com.crudvideojuegos.model.videojuego;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideojuegoFacade extends JpaRepository<Videojuego, Long>{
    List<Videojuego> findVideojuegoByName(String name);
}
