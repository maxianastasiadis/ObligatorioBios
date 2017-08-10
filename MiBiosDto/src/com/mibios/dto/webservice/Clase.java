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
public class Clase implements Serializable{
    private ClaseDatos objClaseDatos;
    private List<Estudiante> listaEstudiantes;

    public ClaseDatos getObjClaseDatos() {
        return objClaseDatos;
    }

    public void setObjClaseDatos(ClaseDatos objClaseDatos) {
        this.objClaseDatos = objClaseDatos;
    }

    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public Clase() {
        this.listaEstudiantes = new ArrayList();
    }
    
    
    
}
