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
public class ReturnListaCursos implements Serializable{
    private List<Curso> listaCursos;

    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public ReturnListaCursos() {
        this.listaCursos = new ArrayList();
    }
    
    
}
