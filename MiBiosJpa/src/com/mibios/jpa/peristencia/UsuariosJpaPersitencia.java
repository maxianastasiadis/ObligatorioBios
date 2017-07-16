/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.jpa.entidades.Usuarios;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Maxi
 */
public class UsuariosJpaPersitencia {
    
    public static ReturnLogin ControlUsuarioLogin(EntityManager em, ParamLogin xParamLogin) {

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
    
    public static ReturnLogin ObtenerUsuarioLogin(EntityManager em, ParamLogin xParamLogin) {

        ReturnLogin objReturnLogin = new ReturnLogin();
        Usuarios objUsuario = em.createNamedQuery("Usuarios.obtenerUsuarioLogin",Usuarios.class)
                .setParameter("tipoPersona", xParamLogin.getTipoPersona())
                .setParameter("tipoDocumento", xParamLogin.getTipoDocumento())
                .setParameter("documento", xParamLogin.getDocumento())
                .getSingleResult();
        
            objReturnLogin.setLogin(true);
            objReturnLogin.setTipoPersona(objUsuario.getUsuariosPK().getTipoPersona());
            objReturnLogin.setTipoDocumento(objUsuario.getUsuariosPK().getTipoDocumento());
            objReturnLogin.setDocumento(objUsuario.getUsuariosPK().getDocumento());
            objReturnLogin.setNombreUsuario(objUsuario.getPersonas().getNombre1() + " " + objUsuario.getPersonas().getApellido1());
        
        return objReturnLogin;        
    }
}
