/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "ClaseEstudiantes.findByIdClase", query = "SELECT c FROM ClaseEstudiantes c WHERE c.claseEstudiantesPK.idClase = :idClase"),
    @NamedQuery(name = "ClaseEstudiantes.findByIdEstudiante", query = "SELECT c FROM ClaseEstudiantes c WHERE c.claseEstudiantesPK.idEstudiante = :idEstudiante"),
    @NamedQuery(name = "ClaseEstudiantes.findByImporteCuota", query = "SELECT c FROM ClaseEstudiantes c WHERE c.importeCuota = :importeCuota"),
    @NamedQuery(name = "ClaseEstudiantes.findByPorcentajeBeca", query = "SELECT c FROM ClaseEstudiantes c WHERE c.porcentajeBeca = :porcentajeBeca"),
    @NamedQuery(name = "ClaseEstudiantes.findByAprobadoSn", query = "SELECT c FROM ClaseEstudiantes c WHERE c.aprobadoSn = :aprobadoSn")})
public class ClaseEstudiantes implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClaseEstudiantesPK claseEstudiantesPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "IMPORTE_CUOTA")
    private BigDecimal importeCuota;
    @Column(name = "PORCENTAJE_BECA")
    private BigDecimal porcentajeBeca;
    @Column(name = "APROBADO_SN")
    private String aprobadoSn;
    @JoinColumn(name = "ID_ESTUDIANTE", referencedColumnName = "ID_ESTUDIANTE", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiantes estudiantes;
    @JoinColumn(name = "ID_CLASE", referencedColumnName = "ID_CLASE", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Clases clases;

    public ClaseEstudiantes() {
    }

    public ClaseEstudiantes(ClaseEstudiantesPK claseEstudiantesPK) {
        this.claseEstudiantesPK = claseEstudiantesPK;
    }

    public ClaseEstudiantes(int idClase, int idEstudiante) {
        this.claseEstudiantesPK = new ClaseEstudiantesPK(idClase, idEstudiante);
    }

    public ClaseEstudiantesPK getClaseEstudiantesPK() {
        return claseEstudiantesPK;
    }

    public void setClaseEstudiantesPK(ClaseEstudiantesPK claseEstudiantesPK) {
        this.claseEstudiantesPK = claseEstudiantesPK;
    }

    public BigDecimal getImporteCuota() {
        return importeCuota;
    }

    public void setImporteCuota(BigDecimal importeCuota) {
        this.importeCuota = importeCuota;
    }

    public BigDecimal getPorcentajeBeca() {
        return porcentajeBeca;
    }

    public void setPorcentajeBeca(BigDecimal porcentajeBeca) {
        this.porcentajeBeca = porcentajeBeca;
    }

    public String getAprobadoSn() {
        return aprobadoSn;
    }

    public void setAprobadoSn(String aprobadoSn) {
        this.aprobadoSn = aprobadoSn;
    }

    public Estudiantes getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Estudiantes estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Clases getClases() {
        return clases;
    }

    public void setClases(Clases clases) {
        this.clases = clases;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claseEstudiantesPK != null ? claseEstudiantesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClaseEstudiantes)) {
            return false;
        }
        ClaseEstudiantes other = (ClaseEstudiantes) object;
        if ((this.claseEstudiantesPK == null && other.claseEstudiantesPK != null) || (this.claseEstudiantesPK != null && !this.claseEstudiantesPK.equals(other.claseEstudiantesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mibios.jpa.entidades.ClaseEstudiantes[ claseEstudiantesPK=" + claseEstudiantesPK + " ]";
    }
    
}
