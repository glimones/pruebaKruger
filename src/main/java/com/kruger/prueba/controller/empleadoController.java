package com.kruger.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.prueba.modelo.infousuario;
import com.kruger.prueba.modelo.usuario;
import com.kruger.prueba.repository.infoUsuarioRepository;
import com.kruger.prueba.repository.usuarioRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@RestController
public class empleadoController {
	 @Autowired
	 private infoUsuarioRepository consultas;
	 
	 @PostMapping("/createInfoUser")
	 public String createEmployee(@RequestBody infousuario infou) {
		 infousuario info= new infousuario();
		 info.setCedula(infou.getUsuario_id().getCedula());
		 info.setDireccion(infou.getDireccion());		 
		 info.setEstadovacuna(infou.getEstadovacuna());
		 info.setFechanacimiento(infou.getFechanacimiento());
		 info.setTelefono(infou.getTelefono());
		 
		 if(infou.getEstadovacuna().equals("S")) {
			 if(infou.getFechavacuna()!=null && infou.getTipovacuna()!=null && (infou.getDosis()>0)) {
				 info.setFechavacuna(infou.getFechavacuna());
				 info.setTipovacuna(infou.getTipovacuna());
				 info.setDosis(infou.getDosis());
			 }else {
				 return "No envio la fecha de vacuna, numero de dosis, el tipo de vacuna o el numero de dosis no es el correcto";
			 }
		 }
		 info.setUsuario_id(consultas.getUsuario(infou.getUsuario_id().getCedula()));
		 consultas.save(info);
		
	    return "Registro guardado exitosamente" ;
	 }
	 
	 @PostMapping("/updateInfoUser")
	 public String updateEmployee(@RequestBody infousuario infou) {
		 infousuario info=consultas.getInfoUsuario(infou.getUsuario_id().getCedula());
		 if(infou.getCedula()!=null)
			 info.setCedula(infou.getUsuario_id().getCedula());
		 
		 if(infou.getDireccion()!=null)
			 info.setDireccion(infou.getDireccion());		 
		 
		 if(infou.getEstadovacuna()!=null)
			 info.setEstadovacuna(infou.getEstadovacuna());
		 
		 if(infou.getFechanacimiento()!=null)
			 info.setFechanacimiento(infou.getFechanacimiento());
		 
		 if(infou.getTelefono()!=null)
			 info.setTelefono(infou.getTelefono());
		 
		 if(infou.getEstadovacuna().equals("S")) {
			 if(infou.getFechavacuna()!=null && infou.getTipovacuna()!=null && (infou.getDosis()>0)) {
				 info.setFechavacuna(infou.getFechavacuna());
				 info.setTipovacuna(infou.getTipovacuna());
				 info.setDosis(infou.getDosis());
			 }else {
				 return "No envio la fecha de vacuna, numero de dosis, el tipo de vacuna o el numero de dosis no es el correcto";
			 }
		 }
		 info.setUsuario_id(consultas.getUsuario(infou.getUsuario_id().getCedula()));
		 consultas.save(info);
		
	    return "Registro actualizado exitosamente" ;
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
