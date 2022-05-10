package com.kruger.prueba.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="infousuario")
public class infousuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "fechanacimiento",insertable=true, updatable=true)
	@Temporal(TemporalType.DATE)
	private java.util.Date fechanacimiento;
	
	@Column(name="direccion",nullable=false,length = 300,insertable=true, updatable=true)
	private String direccion;
	
	@Column(name="telefono",nullable=false,length = 50,insertable=true, updatable=true)
	private String telefono;
	
	@Column(name="estadovacuna",nullable=false,length = 50,insertable=true, updatable=true)
	private String estadovacuna;


	@Enumerated(EnumType.STRING)
	@Column(name="tipovacuna",nullable=true,length = 50,insertable=true, updatable=true)
	private Tipovacuna tipovacuna;
	public enum Tipovacuna {
		Ninguna,
		Sputnik, 
		AstraZeneca, 
		Pfizer,
		Jhonson    
	}	
	@Column(name = "fechavacuna",nullable=true)
	@Temporal(TemporalType.DATE)
	private java.util.Date fechavacuna;
	
	@Column(name="dosis",nullable=true,length = 50)
	private int dosis;
	
	@ManyToOne
    @JoinColumn(name="usuario_id",insertable=true, updatable=false)
	private usuario usuario_id;
	
	@Column(name="cedula",nullable=false,length = 10)
	private String cedula;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.util.Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(java.util.Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEstadovacuna() {
		return estadovacuna;
	}

	public void setEstadovacuna(String estadovacuna) {
		this.estadovacuna = estadovacuna;
	}

	public Tipovacuna getTipovacuna() {
		return tipovacuna;
	}

	public void setTipovacuna(Tipovacuna tipovacuna) {
		this.tipovacuna = tipovacuna;
	}

	public java.util.Date getFechavacuna() {
		return fechavacuna;
	}

	public void setFechavacuna(java.util.Date fechavacuna) {
		this.fechavacuna = fechavacuna;
	}

	public int getDosis() {
		return dosis;
	}

	public void setDosis(int dosis) {
		this.dosis = dosis;
	}

	public usuario getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(usuario usuario_id) {
		this.usuario_id = usuario_id;
	}
	
	
}
