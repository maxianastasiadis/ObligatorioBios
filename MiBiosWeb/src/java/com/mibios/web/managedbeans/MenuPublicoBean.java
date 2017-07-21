/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Maxi
 */
@ManagedBean
@RequestScoped
public class MenuPublicoBean implements Serializable {

    private String page;
    
    public MenuPublicoBean() {
    }
    
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
    
    public String cargarPagina(String pagina)
    {
        page = pagina;
        return page;
    }
    
}
