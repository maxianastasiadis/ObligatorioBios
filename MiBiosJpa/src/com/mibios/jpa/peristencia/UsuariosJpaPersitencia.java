/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.usuarios.ParamRegistro;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.jpa.entidades.Usuarios;
import com.mibios.jpa.entidades.UsuariosPK;
import javax.persistence.EntityManager;

/**
 *
 * @author Maxi
 */
public class UsuariosJpaPersitencia {
    
    public static ReturnLogin controlUsuarioLogin(EntityManager em, ParamLogin xParamLogin) 
    {

        ReturnLogin objReturnLogin = new ReturnLogin();
        long existe = (Long)em.createNamedQuery("Usuarios.controlUsuarioLogin")
                .setParameter("tipoPersona", xParamLogin.getTipoPersona())
                .setParameter("tipoDocumento", xParamLogin.getTipoDocumento())
                .setParameter("documento", xParamLogin.getDocumento())
                .setParameter("clave", xParamLogin.getClave())
                .getSingleResult();
        
        if(existe>0)
        {
            objReturnLogin.setLogin(true);
        }
        else
        {
            objReturnLogin.setLogin(false);
            objReturnLogin.setRespuesta("Usuario o Clave incorrectos");
        }
        return objReturnLogin;        
    }
    
    public static ReturnLogin obtenerUsuarioLogin(EntityManager em, ParamLogin xParamLogin) 
    {
        ReturnLogin objReturnLogin = new ReturnLogin();
        
        UsuariosPK objUsuariosPK = new UsuariosPK(xParamLogin.getTipoPersona(), xParamLogin.getTipoDocumento(), xParamLogin.getDocumento());
        Usuarios objUsuario = em.find(Usuarios.class, objUsuariosPK);
        /*Usuarios objUsuario = em.createNamedQuery("Usuarios.obtenerUsuarioLogin",Usuarios.class)
                .setParameter("tipoPersona", xParamLogin.getTipoPersona())
                .setParameter("tipoDocumento", xParamLogin.getTipoDocumento())
                .setParameter("documento", xParamLogin.getDocumento())
                .getSingleResult();*/
        
            objReturnLogin.setLogin(true);
            objReturnLogin.setTipoPersona(objUsuario.getUsuariosPK().getTipoPersona());
            objReturnLogin.setTipoDocumento(objUsuario.getUsuariosPK().getTipoDocumento());
            objReturnLogin.setDocumento(objUsuario.getUsuariosPK().getDocumento());
            objReturnLogin.setNombreUsuario(objUsuario.getPersonas().getNombre1() + " " + objUsuario.getPersonas().getApellido1());
        
        return objReturnLogin;        
    }
    
    public static Boolean existeUsuario(EntityManager em, UsuariosPK objUsuariosPK) 
    {
        Boolean existeUsuario = false;
        
        Usuarios objUsuario = em.find(Usuarios.class, objUsuariosPK);
        
        if(objUsuario!=null)
        {
            existeUsuario = true;
        }
        
        return existeUsuario;
    }
    
    public static void altaUsuario(EntityManager em, ParamRegistro xParamRegistro) 
    {
        UsuariosPK objUsuariosPK = new UsuariosPK(xParamRegistro.getTipoPersona(), xParamRegistro.getTipoDocumento(), xParamRegistro.getDocumento());
        Usuarios objUsuario = new Usuarios();
        objUsuario.setUsuariosPK(objUsuariosPK);
        objUsuario.setClave(xParamRegistro.getClave());
        objUsuario.setActivo("S");
        objUsuario.setFechaIngreso("20170719");
        
        em.persist(objUsuario);
    }
}
