/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.usuarios.ParamRegistro;
import com.mibios.dto.usuarios.ReturnRegistro;
import com.mibios.ejb.usuarios.UsuariosBean;
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
public class RegsitroUsuarioBean implements Serializable{

    private String tipoPersona;
    private String tipoDocumento;
    private String documento;
    private String clave;
    private String confirmaClave;
    
    public RegsitroUsuarioBean() {
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

    public String getConfirmaClave() {
        return confirmaClave;
    }

    public void setConfirmaClave(String confirmaClave) {
        this.confirmaClave = confirmaClave;
    }
    
     public Boolean Registro() {
        ParamRegistro paramRegistro = new ParamRegistro();
        ReturnRegistro returnRegistro = new ReturnRegistro();
        UsuariosFachada usuarioFachada = new UsuariosFachada();
        
        try {
    
            paramRegistro.setTipoPersona(tipoPersona);
            paramRegistro.setTipoDocumento(tipoDocumento);
            paramRegistro.setDocumento(documento);
            paramRegistro.setClave(clave);
            paramRegistro.setConfirmaClave(confirmaClave);
            returnRegistro = usuarioFachada.Registro(paramRegistro);
            
            if(returnRegistro.getRegistro())
            {
                //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario",returnRegistro);
            }
            else
            {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(returnRegistro.getRespuesta()));
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnRegistro.getRegistro();
    }    
}
