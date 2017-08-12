/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.dto.webservice;

import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class SexoCantidad implements Serializable{
    private String sexo;
    private int cantidad;

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public SexoCantidad(String sexo, int cantidad) {
        this.sexo = sexo;
        this.cantidad = cantidad;
    }
    
    
}
