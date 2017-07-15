/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.usuarios;


import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.usuarios.ParamObtenerDatosPersonales;
import com.mibios.dto.usuarios.ReturnActualizarDatosPersonales;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.dto.usuarios.ReturnObtenerDatosPersonales;
import com.mibios.jpa.conexion.ConexionJpa;
import com.mibios.jpa.peristencia.UsuariosJpaPersitencia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Maxi
 */
@Stateless(mappedName="UsuariosBean")
public class UsuariosBean implements UsuariosBeanLocal {

    @Override
    public ReturnLogin Login(ParamLogin xParamLogin) {
        
        ReturnLogin login = new ReturnLogin();
        EntityManager em = ConexionJpa.obtenerInstancia().obtenerConeccion();
        try
        {
            em.getTransaction().begin();

            //VERIFICO QUE EL USUARIO EXISTA PARA EL LOGIN
            login = UsuariosJpaPersitencia.ControlUsuarioLogin(em, xParamLogin);
            
            //SI EXISTE LO OBTENGO
            if(login.getLogin())
            {
                //OBTENER DATOS DE USUARIO   
                login = UsuariosJpaPersitencia.ObtenerUsuarioLogin(em, xParamLogin);
            }

            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            if(em.getTransaction()!=null && em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally{
            em.close();
        }
        return login;
    }
   
}
