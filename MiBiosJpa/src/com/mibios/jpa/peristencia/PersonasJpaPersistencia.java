/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.dto.usuarios.ParamObtenerDatosPersonales;
import com.mibios.dto.usuarios.ReturnObtenerDatosPersonales;
import com.mibios.jpa.entidades.Personas;
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
    
}
