/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author General
 */
@ManagedBean
@SessionScoped
public class MenuPrincipalBean implements Serializable {

    public MenuPrincipalBean() {
    }
    
    public Boolean Logout() {
        //aca hay que hacer algo para cerrar la sesion
        return true;
    }
    
}
