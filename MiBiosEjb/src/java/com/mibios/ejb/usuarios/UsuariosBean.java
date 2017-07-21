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
import com.mibios.dto.usuarios.ParamRecuperarContrasena;
import com.mibios.dto.usuarios.ParamRegistro;
import com.mibios.dto.usuarios.ReturnRecuperarContrasena;
import com.mibios.dto.usuarios.ReturnRegistro;
import com.mibios.jpa.conexion.ConexionJpa;
import com.mibios.jpa.entidades.UsuariosPK;
import com.mibios.jpa.peristencia.DocentesJpaPersitencia;
import com.mibios.jpa.peristencia.EstudiantesJpaPersitencia;
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
            login = UsuariosJpaPersitencia.controlUsuarioLogin(em, xParamLogin);
            
            //SI EXISTE LO OBTENGO
            if(login.getLogin())
            {
                //OBTENER DATOS DE USUARIO   
                login = UsuariosJpaPersitencia.obtenerUsuarioLogin(em, xParamLogin);
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
        Boolean existeComoTipoPersona = false;
        
        try
        {
            em.getTransaction().begin();

            if(xParamRegistro.getClave().equals(xParamRegistro.getConfirmaClave()))
            {
                //VERIFICO QUE EL USUARIO NO EXISTA
                UsuariosPK objUsuariosPK = new UsuariosPK(xParamRegistro.getTipoPersona(), xParamRegistro.getTipoDocumento(), xParamRegistro.getDocumento());
                if(!UsuariosJpaPersitencia.existeUsuario(em, objUsuariosPK))
                {
                    //VERIFICO QUE EXISTA COMO TIPO DE PERSONA, ES DECIR, COMO DOCENTE O ESTUDIANTE
                    if(xParamRegistro.getTipoPersona().equalsIgnoreCase("D"))
                    {
                        if(DocentesJpaPersitencia.existeDocente(em, xParamRegistro.getTipoDocumento(), xParamRegistro.getDocumento()))
                        {
                            existeComoTipoPersona = true;
                        }
                        else
                        {
                            registro.setRegistro(false);
                            registro.setRespuesta("No existe como Docente");
                        }
                    }
                    else
                    {
                        if(EstudiantesJpaPersitencia.existeEstudiante(em, xParamRegistro.getTipoDocumento(), xParamRegistro.getDocumento()))
                        {
                            existeComoTipoPersona = true;
                        }
                        else
                        {
                            registro.setRegistro(false);
                            registro.setRespuesta("No existe como Estudiante");
                        }
                    }
                    
                    //SI EXISTE REGISTRAMOS EL USUARIO
                    if(existeComoTipoPersona)
                    {
                        UsuariosJpaPersitencia.altaUsuario(em, xParamRegistro);
                        registro.setRegistro(true);
                        registro.setRespuesta("Se ha registrado correctamente");                        
                    }
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
    
    @Override
    public ReturnRecuperarContrasena RecuperarContrasena(ParamRecuperarContrasena xParamRecuperarContrasena) throws Exception
    {
        ReturnRecuperarContrasena recuperar = new ReturnRecuperarContrasena();
        EntityManager em = ConexionJpa.obtenerInstancia().obtenerConeccion();
        
        try
        {
            em.getTransaction().begin();

            // VERIFICAR SI EXISTE USUARIO PARA TIPO PERSONA, TIPO DOCUMENTO Y DOCUMENTO
            UsuariosPK objUsuariosPK = new UsuariosPK(xParamRecuperarContrasena.getTipoPersona(), xParamRecuperarContrasena.getTipoDocumento(), xParamRecuperarContrasena.getDocumento());
            if(UsuariosJpaPersitencia.existeUsuario(em, objUsuariosPK))
            {
                // SI EXISTE LE HAGO UPDATE A LA CLAVE, LE PONGO ingresarnueva
                recuperar.setRecuperar(true);
            }
            else
            {
                recuperar.setRecuperar(false);
                recuperar.setRespuesta("El Usuario no existe");
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
        return recuperar;
    }
   
}
