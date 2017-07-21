/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.usuarios.ParamRecuperarContrasena;
import com.mibios.dto.usuarios.ReturnRecuperarContrasena;
import com.mibios.web.fachada.UsuariosFachada;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Maxi
 */
@ManagedBean
@RequestScoped
public class RecuperarContrasenaBean implements Serializable {

    private String tipoPersona;
    private String tipoDocumento;
    private String documento;
    
    public RecuperarContrasenaBean() {
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    public Boolean RecuperarContrasena() {
        ParamRecuperarContrasena paramRecuperarContrasena = new ParamRecuperarContrasena();
        ReturnRecuperarContrasena returnRecuperarContrasena = new ReturnRecuperarContrasena();
        UsuariosFachada usuarioFachada = new UsuariosFachada();
        
        try {
    
            paramRecuperarContrasena.setTipoPersona(tipoPersona);
            paramRecuperarContrasena.setTipoDocumento(tipoDocumento);
            paramRecuperarContrasena.setDocumento(documento);
            
            returnRecuperarContrasena = usuarioFachada.RecuperarContrasena(paramRecuperarContrasena);
            
            if(returnRecuperarContrasena.getRecuperar())
            {
                
            }
            else
            {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(returnRecuperarContrasena.getRespuesta()));
            }
        } catch (Exception ex) {
            Logger.getLogger(RecuperarContrasenaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnRecuperarContrasena.getRecuperar();
    }    
}
