package com.kruger.prueba.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="cedula",nullable=false,length = 100,insertable=true, updatable=true)
	private String cedula;
	@Column(name="nombres",nullable=false,length = 100,insertable=true, updatable=true)
	private String nombres;
	@Column(name="apellidos",nullable=false,length = 100,insertable=true, updatable=true)
	private String apellidos;
	@Column(name="email",nullable=false,length = 100,insertable=true, updatable=true)
	private String email;
	
	@ManyToOne
    @JoinColumn(name="rol_id",insertable=true, updatable=false)
	private roles rol_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public roles getRol_id() {
		return rol_id;
	}
	public void setRol_id(roles rol_id) {
		this.rol_id = rol_id;
	}
	
	  
}
