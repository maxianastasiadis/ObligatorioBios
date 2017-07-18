/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.dto.usuarios.ParamActualizarDatosPersonales;
import com.mibios.dto.usuarios.ParamObtenerDatosPersonales;
import com.mibios.dto.usuarios.ReturnActualizarDatosPersonales;
import com.mibios.dto.usuarios.ReturnObtenerDatosPersonales;
import com.mibios.jpa.entidades.Personas;
import com.mibios.jpa.entidades.PersonasPK;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;

/**
 *
 * @author Maxi
 */
public class PersonasJpaPersistencia {
    
    public static ReturnObtenerDatosPersonales ObtenerDatosPersonales(EntityManager em, ParamObtenerDatosPersonales xParamObtenerDatosPersonales){
     
        ReturnObtenerDatosPersonales objReturnObtenerDatosPersonales = new ReturnObtenerDatosPersonales();
        
        Personas objPersonas = em.createNamedQuery("Personas.obtenerPersona",Personas.class)
                .setParameter("tipoDocumento", xParamObtenerDatosPersonales.getTipoDocumento())
                .setParameter("documento", xParamObtenerDatosPersonales.getDocumento())
                .getSingleResult();
        
        objReturnObtenerDatosPersonales.setTipoDocumento(objPersonas.getPersonasPK().getTipoDocumento());
        objReturnObtenerDatosPersonales.setDocumento(objPersonas.getPersonasPK().getDocumento());
        objReturnObtenerDatosPersonales.setApellido1(objPersonas.getApellido1());
        objReturnObtenerDatosPersonales.setApellido2(objPersonas.getApellido2());
        objReturnObtenerDatosPersonales.setNombre1(objPersonas.getNombre1());
        objReturnObtenerDatosPersonales.setNombre2(objPersonas.getNombre2());
        objReturnObtenerDatosPersonales.setFechaNacimiento(objPersonas.getFechaNacimiento());
        objReturnObtenerDatosPersonales.setSexo(objPersonas.getSexo());
        objReturnObtenerDatosPersonales.setCelular(objPersonas.getCelular());
        objReturnObtenerDatosPersonales.setTelefono(objPersonas.getTelefono());
        objReturnObtenerDatosPersonales.setMail(objPersonas.getMail());
        objReturnObtenerDatosPersonales.setPais(objPersonas.getPais());
        objReturnObtenerDatosPersonales.setDepartamento(objPersonas.getDepartamento());
        objReturnObtenerDatosPersonales.setCiudad(objPersonas.getCiudad());
        objReturnObtenerDatosPersonales.setDireccion(objPersonas.getDireccion());
        
        return objReturnObtenerDatosPersonales;    
    }
    
    public static ReturnActualizarDatosPersonales ActualizarDatosPersonales(EntityManager em, ParamActualizarDatosPersonales xParamActualizarDatosPersonales) throws Exception {
     
        ReturnActualizarDatosPersonales objReturnActualizarDatosPersonales = new ReturnActualizarDatosPersonales();
        
        Personas objPersonas = new Personas();
        PersonasPK objPersonasPK = new PersonasPK();
        
        SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
	String fechaNacimiento = sdFormat.format(xParamActualizarDatosPersonales.getFechaNacimiento()).replace("/", "");
        String a = fechaNacimiento.substring(4, 8);
        String m = fechaNacimiento.substring(2, 4);
        String d = fechaNacimiento.substring(0, 2);
        fechaNacimiento = a+m+d;
        
        objPersonas.setActivo(xParamActualizarDatosPersonales.getActivo());
        objPersonas.setApellido1(xParamActualizarDatosPersonales.getApellido1());
        objPersonas.setApellido2(xParamActualizarDatosPersonales.getApellido2());
        objPersonas.setCelular(xParamActualizarDatosPersonales.getCelular());
        objPersonas.setCiudad(xParamActualizarDatosPersonales.getCiudad());
        objPersonas.setDepartamento(xParamActualizarDatosPersonales.getDepartamento());
        objPersonas.setDireccion(xParamActualizarDatosPersonales.getDireccion());
        objPersonas.setFechaIngreso(xParamActualizarDatosPersonales.getFechaIngreso());
        objPersonas.setFechaNacimiento(fechaNacimiento);
        objPersonas.setMail(xParamActualizarDatosPersonales.getMail());
        objPersonas.setNombre1(xParamActualizarDatosPersonales.getNombre1());
        objPersonas.setNombre2(xParamActualizarDatosPersonales.getNombre2());
        objPersonas.setPais(xParamActualizarDatosPersonales.getPais());
        objPersonasPK.setTipoDocumento(xParamActualizarDatosPersonales.getTipoDocumento());
        objPersonasPK.setDocumento(xParamActualizarDatosPersonales.getDocumento());
        objPersonas.setPersonasPK(objPersonasPK);
        objPersonas.setSexo(xParamActualizarDatosPersonales.getSexo());
        objPersonas.setTelefono(xParamActualizarDatosPersonales.getTelefono());
        
        objPersonas = em.merge(objPersonas);
        
        Boolean guardadoOk = false;
        String respuesta = "No se han podido guardar los datos";
        
        if(objPersonas.getPersonasPK().getTipoDocumento().equalsIgnoreCase(xParamActualizarDatosPersonales.getTipoDocumento()) && 
           objPersonas.getPersonasPK().getDocumento().equalsIgnoreCase(xParamActualizarDatosPersonales.getDocumento()))
        {
            guardadoOk = true;
            respuesta = "";
        }
            
        objReturnActualizarDatosPersonales.setGuardado(guardadoOk);
        objReturnActualizarDatosPersonales.setRespuesta(respuesta);
        
        return objReturnActualizarDatosPersonales;    
    }
}
