/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.dto.cursos;

import java.io.Serializable;

/**
 *
 * @author Maxi
 */
public class MisCursosDetalles implements Serializable{
    private String detalleNombre;
    private String detalleSalon;
    private String detalleDocente;
    private String detalleCuota;
    private String detalleBeca;
    private String detalleAprobado;

    public String getDetalleNombre() {
        return detalleNombre;
    }

    public void setDetalleNombre(String detalleNombre) {
        this.detalleNombre = detalleNombre;
    }

    public String getDetalleSalon() {
        return detalleSalon;
    }

    public void setDetalleSalon(String detalleSalon) {
        this.detalleSalon = detalleSalon;
    }

    public String getDetalleDocente() {
        return detalleDocente;
    }

    public void setDetalleDocente(String detalleDocente) {
        this.detalleDocente = detalleDocente;
    }

    public String getDetalleCuota() {
        return detalleCuota;
    }

    public void setDetalleCuota(String detalleCuota) {
        this.detalleCuota = detalleCuota;
    }

    public String getDetalleBeca() {
        return detalleBeca;
    }

    public void setDetalleBeca(String detalleBeca) {
        this.detalleBeca = detalleBeca;
    }

    public String getDetalleAprobado() {
        return detalleAprobado;
    }

    public void setDetalleAprobado(String detalleAprobado) {
        this.detalleAprobado = detalleAprobado;
    }

}
