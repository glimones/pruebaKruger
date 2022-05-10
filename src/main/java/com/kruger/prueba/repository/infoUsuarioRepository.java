package com.kruger.prueba.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kruger.prueba.modelo.infousuario;

@Repository
public interface infoUsuarioRepository extends JpaRepository<infousuario, Long>,ConsultasRepository {

}
