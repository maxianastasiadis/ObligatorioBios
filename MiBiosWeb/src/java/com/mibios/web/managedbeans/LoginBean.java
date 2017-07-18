/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.web.fachada.UsuariosFachada;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Maxi
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

    private String tipoPersona;
    private String tipoDocumento;
    private String documento;
    private String clave;
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public Boolean Login() {
        ParamLogin paramLogin = new ParamLogin();
        ReturnLogin returnLogin = new ReturnLogin();
        UsuariosFachada usuarioFachada = new UsuariosFachada();
        
        try {
    
            paramLogin.setTipoPersona(tipoPersona);
            paramLogin.setTipoDocumento(tipoDocumento);
            paramLogin.setDocumento(documento);
            paramLogin.setClave(clave);
            returnLogin = usuarioFachada.Login(paramLogin);
            if(returnLogin.getLogin())
            {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario",returnLogin);
            }
            else
            {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(returnLogin.getRespuesta()));
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnLogin.getLogin();
    }    
}
