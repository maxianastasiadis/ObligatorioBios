/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.dto.webservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class ReturnCantidadAlumnosSexo implements Serializable{
    private List<SexoCantidad> lista;

    public List<SexoCantidad> getLista() {
        return lista;
    }

    public void setLista(List<SexoCantidad> lista) {
        this.lista = lista;
    }

    public ReturnCantidadAlumnosSexo() 
    {
        this.lista = new ArrayList();
    }
    
    
    
}
