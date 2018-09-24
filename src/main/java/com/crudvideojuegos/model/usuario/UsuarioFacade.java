package com.crudvideojuegos.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioFacade extends JpaRepository<Usuario, Long> {
    List<Usuario> findUsuarioByNombre(String name);
}
