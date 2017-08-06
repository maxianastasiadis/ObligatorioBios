/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.dto.cursos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Maxi
 */
public class CursosDatos implements Serializable{
    
    private int idCurso;
    private String nombre;
    private List<ClaseDatos> listaClases;
    

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ClaseDatos> getListaClases() {
        return listaClases;
    }

    public void setListaClases(List<ClaseDatos> listaClases) {
        this.listaClases = listaClases;
    }

    
}
