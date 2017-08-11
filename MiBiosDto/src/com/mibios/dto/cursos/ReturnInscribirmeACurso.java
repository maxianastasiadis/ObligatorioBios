/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.dto.cursos;

import com.mibios.dto.usuarios.ReturnLogin;
import java.io.Serializable;

/**
 *
 * @author General
 */
public class ReturnInscribirmeACurso implements Serializable {
 
    private Boolean guardado;
    private ReturnLogin datosUsuario;
    private String respuesta;

    public Boolean getGuardado() {
        return guardado;
    }

    public void setGuardado(Boolean guardado) {
        this.guardado = guardado;
    }

    public ReturnLogin getDatosUsuario() {
        return datosUsuario;
    }

    public void setDatosUsuario(ReturnLogin datosUsuario) {
        this.datosUsuario = datosUsuario;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    
}
