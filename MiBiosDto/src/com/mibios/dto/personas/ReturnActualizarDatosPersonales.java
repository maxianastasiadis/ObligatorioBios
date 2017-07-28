/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.dto.personas;

import com.mibios.dto.usuarios.ReturnLogin;
import java.io.Serializable;

/**
 *
 * @author Maxi
 */
public class ReturnActualizarDatosPersonales implements Serializable {
    
    private Boolean guardado;
    private ReturnLogin datosUsuario;
    private String respuesta;

    public Boolean getGuardado() {
        return guardado;
    }

    public void setGuardado(Boolean guardado) {
        this.guardado = guardado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public ReturnLogin getDatosUsuario() {
        return datosUsuario;
    }

    public void setDatosUsuario(ReturnLogin datosUsuario) {
        this.datosUsuario = datosUsuario;
    }
    
    
}
