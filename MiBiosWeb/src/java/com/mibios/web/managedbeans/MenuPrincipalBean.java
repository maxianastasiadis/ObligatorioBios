/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
    
    
    public MenuPrincipalBean() {
        this.page="background.xhtml";
    }
    
    public Boolean Logout() {
        //aca hay que hacer algo para cerrar la sesion
        return true;
    }
    
    public String cargarPagina(String pagina)
    {
        page = pagina;
        return page;
    }
}
