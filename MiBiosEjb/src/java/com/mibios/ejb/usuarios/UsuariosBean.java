/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.usuarios;


import com.mibios.dto.usuarios.ParamCambiarContrasena;
import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.dto.usuarios.ParamRecuperarContrasena;
import com.mibios.dto.usuarios.ParamRegistro;
import com.mibios.dto.usuarios.ReturnRecuperarContrasena;
import com.mibios.dto.usuarios.ReturnRegistro;
import com.mibios.jpa.entidades.Usuarios;
import com.mibios.jpa.entidades.UsuariosPK;
import com.mibios.jpa.peristencia.DocentesJpaPersitencia;
import com.mibios.jpa.peristencia.EstudiantesJpaPersitencia;
import com.mibios.jpa.peristencia.UsuariosJpaPersitencia;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maxi
 */
@Stateless(mappedName="UsuariosBean")
public class UsuariosBean implements UsuariosBeanLocal {

    @PersistenceContext(unitName = "MiBiosJpaPU")
    private EntityManager em;
    
    @Override
    public ReturnLogin Login(ParamLogin xParamLogin) throws Exception
    {
        ReturnLogin login = new ReturnLogin();
        
        try
        {
            //VERIFICO QUE EL USUARIO EXISTA PARA EL LOGIN
            if(UsuariosJpaPersitencia.existeUsuarioLogin(em, xParamLogin))
            {
                UsuariosPK objUsuariosPk = new UsuariosPK(xParamLogin.getTipoPersona(), xParamLogin.getTipoDocumento(), xParamLogin.getDocumento());
                //OBTENER DATOS DE USUARIO   
                Usuarios objUsuario = UsuariosJpaPersitencia.obtenerUsuario(em, objUsuariosPk);
                
                login.setLogin(true);
                login.setTipoPersona(objUsuario.getUsuariosPK().getTipoPersona());
                login.setTipoDocumento(objUsuario.getUsuariosPK().getTipoDocumento());
                login.setDocumento(objUsuario.getUsuariosPK().getDocumento());
                login.setNombreUsuario(objUsuario.getPersonas().getNombre1() + " " + objUsuario.getPersonas().getApellido1());
            }
            else
            {
                login.setLogin(false);
                login.setRespuesta("Usuario o Clave incorrectos");
            }
        }
        catch(Exception e)
        {
            Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Beans--> " + e.getMessage() + " [" + this.getClass().getSimpleName() + "]");
        }
        finally{
        }
        return login;
    }
    
    @Override
    public ReturnRegistro Registro(ParamRegistro xParamRegistro) throws Exception
    {
        ReturnRegistro registro = new ReturnRegistro();

        Boolean existeComoTipoPersona = false;
        
        try
        {
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
        }
        catch(Exception e)
        {
            Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Beans--> " + e.getMessage() + " [" + this.getClass().getSimpleName() + "]");
        }
        finally{
        }
        
        return registro;
    }
    
    @Override
    public ReturnRecuperarContrasena RecuperarContrasena(ParamRecuperarContrasena xParamRecuperarContrasena) throws Exception
    {
        ReturnRecuperarContrasena recuperar = new ReturnRecuperarContrasena();
        
        try
        {
            // VERIFICAR SI EXISTE USUARIO PARA TIPO PERSONA, TIPO DOCUMENTO Y DOCUMENTO
            UsuariosPK objUsuariosPK = new UsuariosPK(xParamRecuperarContrasena.getTipoPersona(), xParamRecuperarContrasena.getTipoDocumento(), xParamRecuperarContrasena.getDocumento());
            if(UsuariosJpaPersitencia.existeUsuario(em, objUsuariosPK))
            {
                // SI EXISTE LE HAGO UPDATE A LA CLAVE, LE PONGO ingresarnueva
                Usuarios objUsuario = UsuariosJpaPersitencia.obtenerUsuario(em, objUsuariosPK);
                objUsuario.setClave("ingresarnueva");
                UsuariosJpaPersitencia.modificarUsuario(em, objUsuario);
                recuperar.setRecuperar(true);
            }
            else
            {
                recuperar.setRecuperar(false);
                recuperar.setRespuesta("El Usuario no existe");
            }
        }
        catch(Exception e)
        {
            Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Beans--> " + e.getMessage() + " [" + this.getClass().getSimpleName() + "]");
        }
        finally{
        }
        
        return recuperar;
    }
    
    @Override
    public ReturnRecuperarContrasena CambiarContrasena(ParamCambiarContrasena xParamCambiarContrasena) throws Exception
    {
        ReturnRecuperarContrasena recuperar = new ReturnRecuperarContrasena();
        
        try
        {
            // VERIFICAR SI EXISTE USUARIO PARA TIPO PERSONA, TIPO DOCUMENTO Y DOCUMENTO
            UsuariosPK objUsuariosPK = new UsuariosPK(xParamCambiarContrasena.getTipoPersona(), xParamCambiarContrasena.getTipoDocumento(), xParamCambiarContrasena.getDocumento());
            if(UsuariosJpaPersitencia.existeUsuario(em, objUsuariosPK))
            {
                // SI EXISTE LE HAGO UPDATE A LA CLAVE, LE PONGO ingresarnueva
                Usuarios objUsuario = UsuariosJpaPersitencia.obtenerUsuario(em, objUsuariosPK);
                if(objUsuario.getClave().equals(xParamCambiarContrasena.getClave()))
                {
                    objUsuario.setClave(xParamCambiarContrasena.getClaveNueva());
                    UsuariosJpaPersitencia.modificarUsuario(em, objUsuario);
                    recuperar.setRecuperar(true);
                }
                else
                {
                    recuperar.setRecuperar(false);
                    recuperar.setRespuesta("La clave introducida no coincide con la vigente");
                }
            }
            else
            {
                recuperar.setRecuperar(false);
                recuperar.setRespuesta("El Usuario no existe");
            }
        }
        catch(Exception e)
        {
            Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Beans--> " + e.getMessage() + " [" + this.getClass().getSimpleName() + "]");
        }
        finally{
        }
        
        return recuperar;
    }
}
