package com.kruger.prueba.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kruger.prueba.modelo.infousuario;
import com.kruger.prueba.modelo.roles;
import com.kruger.prueba.modelo.usuario;


@Service
public class ConsultasRepositoryImpl implements ConsultasRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public usuario getUsuario(String identificacion) {	
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<usuario> criteriaQuery = criteriaBuilder.createQuery(usuario.class);
		Root<usuario> fRoot = criteriaQuery.from(usuario.class);
		Predicate FiltroTipoTipo=entityManager.getCriteriaBuilder().equal(fRoot.get("cedula"), identificacion);		
		criteriaQuery.where(FiltroTipoTipo);
		usuario user= entityManager.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);
		return user;
	}
	
	@Transactional
	public roles getRoles() {	
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<roles> criteriaQuery = criteriaBuilder.createQuery(roles.class);
		Root<roles> fRoot = criteriaQuery.from(roles.class);
		Predicate FiltroRol=entityManager.getCriteriaBuilder().equal(fRoot.get("descripcion"), "Empleado");		
		criteriaQuery.where(FiltroRol);
		roles rol= entityManager.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);
		return rol;
	}
	
	@Transactional
	public void saveInfoUser(infousuario infouser) {
		
	}
	
	@Transactional
	public infousuario getInfoUsuario(String identificacion) {	
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<infousuario> criteriaQuery = criteriaBuilder.createQuery(infousuario.class);
		Root<infousuario> fRoot = criteriaQuery.from(infousuario.class);
		Predicate FiltroTipoTipo=entityManager.getCriteriaBuilder().equal(fRoot.get("cedula"), identificacion);		
		criteriaQuery.where(FiltroTipoTipo);
		infousuario infouser= entityManager.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);
		return infouser;
	}
	
	@Transactional
	public List<infousuario> getListInfoUsuario(infousuario.Tipovacuna tipovacuna,String estado, String fecha){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<infousuario> criteriaQuery = criteriaBuilder.createQuery(infousuario.class);
		Root<infousuario> gRoot = criteriaQuery.from(infousuario.class);
		if(tipovacuna!=null) {
			Predicate Fidentificacion=entityManager.getCriteriaBuilder().equal(gRoot.get("tipovacuna"), tipovacuna);
			criteriaQuery.where(Fidentificacion);
		}
		if(estado!=null) {
			Predicate Festado=entityManager.getCriteriaBuilder().equal(gRoot.get("estadovacuna"), estado);
			criteriaQuery.where(Festado);
		}
		if(fecha!=null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date convertedCurrentDate = null;
			try {
				 convertedCurrentDate = sdf.parse(fecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Predicate Ffechavacuna=entityManager.getCriteriaBuilder().equal(gRoot.get("fechavacuna"), convertedCurrentDate!=null?convertedCurrentDate:fecha);
			criteriaQuery.where(Ffechavacuna);
		}
		
		return entityManager.createQuery(criteriaQuery).getResultList();		 
	}
	
}

