/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.dto.webservice;

import com.mibios.dto.cursos.ClaseDatos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class ReturnClases implements Serializable{
    List<ClaseDatos> listaClases;

    public List<ClaseDatos> getListaClases() {
        return listaClases;
    }

    public void setListaClases(List<ClaseDatos> listaClases) {
        this.listaClases = listaClases;
    }

    public ReturnClases() 
    {
        this.listaClases = new ArrayList();
    }
    
    
    
}
