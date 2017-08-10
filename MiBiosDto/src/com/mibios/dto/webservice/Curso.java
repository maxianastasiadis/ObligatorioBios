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
public class Curso implements Serializable{
    private int idCurso;
    private String nombre;
    private List<Clase> listaClases;

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

    public List<Clase> getListaClases() {
        return listaClases;
    }

    public void setListaClases(List<Clase> listaClases) {
        this.listaClases = listaClases;
    }

   

    public Curso() {
        this.listaClases = new ArrayList();
    }
    
    
    
}
