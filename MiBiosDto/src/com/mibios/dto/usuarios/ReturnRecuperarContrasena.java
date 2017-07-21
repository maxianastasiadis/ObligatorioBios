/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.dto.usuarios;

import java.io.Serializable;

/**
 *
 * @author Maxi
 */
public class ReturnRecuperarContrasena implements Serializable{
    
    private Boolean recuperar;
    private String respuesta;

    public Boolean getRecuperar() {
        return recuperar;
    }

    public void setRecuperar(Boolean recuperar) {
        this.recuperar = recuperar;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
     
     
}
