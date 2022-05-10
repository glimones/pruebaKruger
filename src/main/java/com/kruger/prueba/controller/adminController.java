package com.kruger.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.prueba.modelo.infousuario;
import com.kruger.prueba.modelo.roles;
import com.kruger.prueba.modelo.usuario;
import com.kruger.prueba.repository.usuarioRepository;

import java.lang.reflect.Array;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@RestController
public class adminController {
	 @Autowired
	 private usuarioRepository consultas;
	 @GetMapping("/getUser")
	 public String obtenerUser(@RequestParam String cedula)  {
		 usuario user = consultas.getUsuario(cedula);
		 if(user!=null) {			 
	        return "{cedula:"+user.getCedula()+",nombres:"+user.getNombres()+",apellidos:"+user.getApellidos()+",email:"+user.getEmail()+"}";// objCupon.toString();
		 }else {
			 return "No existe Usuario con esa cedula";
		 }
	 }
	 
	 @GetMapping("/getInfoUser")
	 public List<infousuario> getListInfoUsuario(@RequestParam(required=false) infousuario.Tipovacuna tipovacuna,@RequestParam(required=false) String estado, @RequestParam(required=false) String fecha){
		 return consultas.getListInfoUsuario(tipovacuna!=null?tipovacuna:null, estado!=null?estado:null, fecha!=null?fecha:null);
	 }
	 
	 @PostMapping("/createUser")
	 public String createEmployee(@RequestBody usuario user) {
		 roles rol = consultas.getRoles();
		 usuario userInserta= new usuario();
		 if((!("").equals(user.getApellidos()) && user.getApellidos()!=null) && contieneSoloLetras(user.getApellidos())) {
			 userInserta.setApellidos(user.getApellidos());
		 }else {
			 return "No puede enviar apellidos vacios o contiene caracteres diferentes a letras";
		 }
		 if((!("").equals(user.getCedula()) && user.getCedula()!=null) && (user.getCedula().length()==10 && contieneSoloNumeros(user.getCedula()))) {
			 userInserta.setCedula(user.getCedula());
		 }else {
			 return "No puede enviar Cedula vacia , contiene caracteres no numericos o el tamaño enviado no es de 10 caracteres";
		 }
		 if((!("").equals(user.getEmail()) && user.getEmail()!=null) && validaEmail(user.getEmail())) {
			 userInserta.setEmail(user.getEmail());
		 }else {
			 return "No puede enviar email vacio o el email es no valido";
		 }
		 if((!("").equals(user.getNombres()) && user.getNombres()!=null) && contieneSoloLetras(user.getNombres())) {
			 userInserta.setNombres(user.getNombres());
		 }else {
			 return "No puede enviar Nombres vacios o contiene caracteres diferentes a letras";
		 }
		 userInserta.setRol_id(rol);
		 consultas.save(userInserta);
	  return "Registro guardado exitosamente" ;
	 }
	 
	 @PostMapping("/updateUser")
	 public String updateEmployee(@RequestBody usuario usuarioin) {
		 if(!("").equals(usuarioin.getCedula()) && (usuarioin.getCedula().length()==10 && contieneSoloNumeros(usuarioin.getCedula()))) {
			 usuario user = consultas.getUsuario(usuarioin.getCedula());
			 if(user!=null) {
				 if(!("").equals(user.getApellidos())) {
					 user.setApellidos(user.getApellidos());
				 }
				 if(!("").equals(user.getCedula())) {
					 user.setCedula(user.getCedula());
				 }
				 if(!("").equals(user.getEmail())) {
					 user.setEmail(user.getEmail());
				 }
				 if(!("").equals(user.getNombres())) {
					 user.setNombres(user.getNombres());
				 }				 
				 consultas.save(user);
				 return "Realizo la actualizacion con exito";
			 }else {
				 return "No existe usuario con esa cedula";
			 }
		 }else {
			 return "La cedula es vacia o no posee la 10 digitos";
		 }
	 }
	 
	 @GetMapping("/deleteUser")
	 public String deleteEmployee(@RequestParam String cedula)  {
		 if(!("").equals(cedula) && (cedula.length()==10 && contieneSoloNumeros(cedula))) {
			 usuario user = consultas.getUsuario(cedula);
			 if(user!=null) {
				 consultas.delete(user);
				 return "Eliminado el usuario con identificacion "+cedula;	 
			 }else {
				 return "No existe usuario registrado con la cedula "+cedula;
			 }
			 	 
		 }else {
			 return "La cedula es vacia o no posee la 10 digitos";
		 }
	 }
	 
	 public static boolean contieneSoloLetras(String cadena) {
		    for (int x = 0; x < cadena.length(); x++) {
		        char c = cadena.charAt(x);
		        // Si no está entre a y z, ni entre A y Z, ni es un espacio
		        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
		            return false;
		        }
		    }
		    return true;
	 }
	
	 public static boolean contieneSoloNumeros(String cadena) {
		    // Si la cadena está vacía, debemos devolver false
		    if (cadena.length() == 0) {
		        return false;
		    }
		    for (int x = 0; x < cadena.length(); x++) {
		        char c = cadena.charAt(x);
		        // Si no está entre 0 y 9
		        if (!(c >= '0' && c <= '9')) {
		            return false;
		        }
		    }
		    return true;
	}

	 public static boolean validaEmail(String email) {
		 Pattern pattern = Pattern
	                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	     Matcher mather = pattern.matcher(email);
         return mather.find();
	 }
}
