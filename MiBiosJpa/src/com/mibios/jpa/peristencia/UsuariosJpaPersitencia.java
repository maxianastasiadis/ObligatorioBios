/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.usuarios.ReturnLogin;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Maxi
 */
public class UsuariosJpaPersitencia {
    
    public static ReturnLogin Login(EntityManager em, ParamLogin xParamLogin) {

        ReturnLogin objReturnLogin = new ReturnLogin();
        long existe = (Long)em.createNamedQuery("Usuarios.controlLogin")
                .setParameter("tipoPersona", xParamLogin.getTipoPersona())
                .setParameter("tipoDocumento", xParamLogin.getTipoDocumento())
                .setParameter("documento", xParamLogin.getDocumento())
                .setParameter("clave", xParamLogin.getClave())
                .getSingleResult();
        
        if(existe>0)
        {
            objReturnLogin.setLogin(true);
            objReturnLogin.setNombreUsuario("maxi");
            objReturnLogin.setRespuesta("");
        }
        else
        {
            objReturnLogin.setLogin(false);
            objReturnLogin.setNombreUsuario("");
            objReturnLogin.setRespuesta("Usuario o Clave incorrectos");
        }
        return objReturnLogin;
        
    }
}
