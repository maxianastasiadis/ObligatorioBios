/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.usuarios.ReturnLogin;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author General
 */
@ManagedBean
@RequestScoped
public class MenuPrincipalBean implements Serializable {

    private String page;
    private String nombreUsuario;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public MenuPrincipalBean() {
        ReturnLogin obj = (ReturnLogin)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        nombreUsuario = obj.getNombreUsuario();
    }
    
    public Boolean Logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Usuario");
        return true;
    }
    
    public String cargarPagina(String pagina)
    {
        page = pagina;
        return page;
    }
}
