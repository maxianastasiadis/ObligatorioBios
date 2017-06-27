/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maxi
 */
@Entity
@Table(name = "clase_estudiantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClaseEstudiantes.findAll", query = "SELECT c FROM ClaseEstudiantes c"),
    @NamedQuery(name = "ClaseEstudiantes.findByIdClase", query = "SELECT c FROM ClaseEstudiantes c WHERE c.idClase = :idClase"),
    @NamedQuery(name = "ClaseEstudiantes.findByIdEstudiante", query = "SELECT c FROM ClaseEstudiantes c WHERE c.idEstudiante = :idEstudiante"),
    @NamedQuery(name = "ClaseEstudiantes.findByImporteCuota", query = "SELECT c FROM ClaseEstudiantes c WHERE c.importeCuota = :importeCuota"),
    @NamedQuery(name = "ClaseEstudiantes.findByPorcentajeBeca", query = "SELECT c FROM ClaseEstudiantes c WHERE c.porcentajeBeca = :porcentajeBeca"),
    @NamedQuery(name = "ClaseEstudiantes.findByAprobadoSn", query = "SELECT c FROM ClaseEstudiantes c WHERE c.aprobadoSn = :aprobadoSn")})
public class ClaseEstudiantes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CLASE")
    private Integer idClase;
    @Column(name = "ID_ESTUDIANTE")
    private Integer idEstudiante;
    @Column(name = "IMPORTE_CUOTA")
    private Long importeCuota;
    @Column(name = "PORCENTAJE_BECA")
    private Long porcentajeBeca;
    @Column(name = "APROBADO_SN")
    private String aprobadoSn;

    public ClaseEstudiantes() {
    }

    public ClaseEstudiantes(Integer idClase) {
        this.idClase = idClase;
    }

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Long getImporteCuota() {
        return importeCuota;
    }

    public void setImporteCuota(Long importeCuota) {
        this.importeCuota = importeCuota;
    }

    public Long getPorcentajeBeca() {
        return porcentajeBeca;
    }

    public void setPorcentajeBeca(Long porcentajeBeca) {
        this.porcentajeBeca = porcentajeBeca;
    }

    public String getAprobadoSn() {
        return aprobadoSn;
    }

    public void setAprobadoSn(String aprobadoSn) {
        this.aprobadoSn = aprobadoSn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClase != null ? idClase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClaseEstudiantes)) {
            return false;
        }
        ClaseEstudiantes other = (ClaseEstudiantes) object;
        if ((this.idClase == null && other.idClase != null) || (this.idClase != null && !this.idClase.equals(other.idClase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mibios.jpa.entidades.ClaseEstudiantes[ idClase=" + idClase + " ]";
    }
    
}
