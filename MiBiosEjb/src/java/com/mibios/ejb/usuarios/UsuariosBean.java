/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.usuarios;


import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.personas.ParamObtenerDatosPersonales;
import com.mibios.dto.personas.ReturnActualizarDatosPersonales;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.dto.personas.ReturnObtenerDatosPersonales;
import com.mibios.dto.usuarios.ParamRegistro;
import com.mibios.dto.usuarios.ReturnRegistro;
import com.mibios.jpa.conexion.ConexionJpa;
import com.mibios.jpa.peristencia.UsuariosJpaPersitencia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

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
    
    @Override
    public ReturnRegistro Registro(ParamRegistro xParamRegistro)
    {
        ReturnRegistro registro = new ReturnRegistro();
        EntityManager em = ConexionJpa.obtenerInstancia().obtenerConeccion();
        try
        {
            em.getTransaction().begin();

            if(xParamRegistro.getClave().equals(xParamRegistro.getConfirmaClave()))
            {
                //VERIFICO QUE EL USUARIO NO EXISTA
                if(!UsuariosJpaPersitencia.existeUsuario(em, xParamRegistro))
                {
                    //VERIFICO QUE EXISTA COMO TIPO DE PERSONA, ES DECIR, COMO ALUMNO O DOCENTE

                    //SI SE DAN AMBAS CONDICIONES REGISTRAMOS EL USUARIO
                    UsuariosJpaPersitencia.altaUsuario(em, xParamRegistro);
                    registro.setRegistro(true);
                    registro.setRespuesta("");
                }
                else
                {
                    registro.setRegistro(false);
                    registro.setRespuesta("El Usuario ya existe");
                }
            }
            else
            {
                registro.setRegistro(false);
                registro.setRespuesta("La clave no coincide con la confirmacion");
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
        return registro;
    }
   
}
