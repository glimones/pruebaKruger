package com.kruger.prueba.repository;

import java.util.List;
import com.kruger.prueba.modelo.infousuario;
import com.kruger.prueba.modelo.roles;
import com.kruger.prueba.modelo.usuario;

public interface ConsultasRepository {
	public usuario getUsuario(String identificacion);
	public infousuario getInfoUsuario(String identificacion);
	public List<infousuario> getListInfoUsuario(infousuario.Tipovacuna tipovacuna,String estado, String fecha);
	public roles getRoles();
	public void saveInfoUser(infousuario infouser);
}
