/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.usuarios.ParamRegistro;
import com.mibios.jpa.entidades.Usuarios;
import com.mibios.jpa.entidades.UsuariosPK;
import javax.persistence.EntityManager;

/**
 *
 * @author Maxi
 */
public class UsuariosJpaPersitencia {

    public static Boolean existeUsuarioLogin(EntityManager em, ParamLogin xParamLogin) throws Exception
    {
        Boolean existeUsuarioLogin = false;
        try
        {
            long existe = (Long)em.createNamedQuery("Usuarios.controlUsuarioLogin")
                    .setParameter("tipoPersona", xParamLogin.getTipoPersona())
                    .setParameter("tipoDocumento", xParamLogin.getTipoDocumento())
                    .setParameter("documento", xParamLogin.getDocumento())
                    .setParameter("clave", xParamLogin.getClave())
                    .getSingleResult();

            if(existe>0)
            {
                existeUsuarioLogin = true;
            }
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        
        return existeUsuarioLogin;
    }
    
    public static Boolean existeUsuario(EntityManager em, UsuariosPK objUsuariosPK) throws Exception 
    {
        Boolean existeUsuario = false;
        try
        {
            Usuarios objUsuario = em.find(Usuarios.class, objUsuariosPK);

            if(objUsuario!=null)
            {
                existeUsuario = true;
            }
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return existeUsuario;
    }
    
    public static void altaUsuario(EntityManager em, ParamRegistro xParamRegistro) throws Exception 
    {
        try
        {
            UsuariosPK objUsuariosPK = new UsuariosPK(xParamRegistro.getTipoPersona(), xParamRegistro.getTipoDocumento(), xParamRegistro.getDocumento());
            Usuarios objUsuario = new Usuarios();
            objUsuario.setUsuariosPK(objUsuariosPK);
            objUsuario.setClave(xParamRegistro.getClave());
            objUsuario.setActivo("S");
            objUsuario.setFechaIngreso("20170719");

            em.persist(objUsuario);
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
    }
    
    public static void modificarUsuario(EntityManager em, Usuarios objUsuarios) throws Exception 
    {   
        try
        {
            em.merge(objUsuarios);
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
    }
    
    public static Usuarios obtenerUsuario(EntityManager em, UsuariosPK objUsuariosPK) throws Exception 
    {   
        Usuarios objUsuario = new Usuarios();
    
        try
        {
            objUsuario = em.find(Usuarios.class, objUsuariosPK);
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        } 
        
        return objUsuario;
    }
}
