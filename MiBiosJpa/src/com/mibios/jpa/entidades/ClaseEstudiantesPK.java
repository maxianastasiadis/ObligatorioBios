/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Maxi
 */
@Embeddable
public class ClaseEstudiantesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_CLASE")
    private int idClase;
    @Basic(optional = false)
    @Column(name = "ID_ESTUDIANTE")
    private int idEstudiante;

    public ClaseEstudiantesPK() {
    }

    public ClaseEstudiantesPK(int idClase, int idEstudiante) {
        this.idClase = idClase;
        this.idEstudiante = idEstudiante;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idClase;
        hash += (int) idEstudiante;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClaseEstudiantesPK)) {
            return false;
        }
        ClaseEstudiantesPK other = (ClaseEstudiantesPK) object;
        if (this.idClase != other.idClase) {
            return false;
        }
        if (this.idEstudiante != other.idEstudiante) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mibios.jpa.entidades.ClaseEstudiantesPK[ idClase=" + idClase + ", idEstudiante=" + idEstudiante + " ]";
    }
    
}
