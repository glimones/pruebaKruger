package com.kruger.prueba.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kruger.prueba.modelo.usuario;

@Repository
public interface usuarioRepository extends JpaRepository<usuario, Long>,ConsultasRepository {

}
