/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maxi
 */
@Entity
@Table(name = "clases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clases.findAll", query = "SELECT c FROM Clases c"),
    @NamedQuery(name = "Clases.findByIdClase", query = "SELECT c FROM Clases c WHERE c.idClase = :idClase"),
    @NamedQuery(name = "Clases.findByModalidadClase", query = "SELECT c FROM Clases c WHERE c.modalidadClase = :modalidadClase"),
    @NamedQuery(name = "Clases.findByFechaComienzo", query = "SELECT c FROM Clases c WHERE c.fechaComienzo = :fechaComienzo"),
    @NamedQuery(name = "Clases.findByFechaFin", query = "SELECT c FROM Clases c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "Clases.findByDiasClase", query = "SELECT c FROM Clases c WHERE c.diasClase = :diasClase"),
    @NamedQuery(name = "Clases.findByHorarioComienzo", query = "SELECT c FROM Clases c WHERE c.horarioComienzo = :horarioComienzo"),
    @NamedQuery(name = "Clases.findByDuracionHoras", query = "SELECT c FROM Clases c WHERE c.duracionHoras = :duracionHoras"),
    @NamedQuery(name = "Clases.findBySalon", query = "SELECT c FROM Clases c WHERE c.salon = :salon")})
public class Clases implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CLASE")
    private Integer idClase;
    @Column(name = "MODALIDAD_CLASE")
    private String modalidadClase;
    @Column(name = "FECHA_COMIENZO")
    private String fechaComienzo;
    @Column(name = "FECHA_FIN")
    private String fechaFin;
    @Column(name = "DIAS_CLASE")
    private String diasClase;
    @Column(name = "HORARIO_COMIENZO")
    private String horarioComienzo;
    @Column(name = "DURACION_HORAS")
    private String duracionHoras;
    @Column(name = "SALON")
    private String salon;
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cursos idCurso;
    @JoinColumn(name = "ID_DOCENTE", referencedColumnName = "ID_DOCENTE")
    @ManyToOne(fetch = FetchType.LAZY)
    private Docentes idDocente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clases", fetch = FetchType.LAZY)
    private List<ClaseEstudiantes> claseEstudiantesList;

    public Clases() {
    }

    public Clases(Integer idClase) {
        this.idClase = idClase;
    }

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
    }

    public String getModalidadClase() {
        return modalidadClase;
    }

    public void setModalidadClase(String modalidadClase) {
        this.modalidadClase = modalidadClase;
    }

    public String getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(String fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDiasClase() {
        return diasClase;
    }

    public void setDiasClase(String diasClase) {
        this.diasClase = diasClase;
    }

    public String getHorarioComienzo() {
        return horarioComienzo;
    }

    public void setHorarioComienzo(String horarioComienzo) {
        this.horarioComienzo = horarioComienzo;
    }

    public String getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(String duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public Cursos getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Cursos idCurso) {
        this.idCurso = idCurso;
    }

    public Docentes getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Docentes idDocente) {
        this.idDocente = idDocente;
    }

    @XmlTransient
    public List<ClaseEstudiantes> getClaseEstudiantesList() {
        return claseEstudiantesList;
    }

    public void setClaseEstudiantesList(List<ClaseEstudiantes> claseEstudiantesList) {
        this.claseEstudiantesList = claseEstudiantesList;
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
        if (!(object instanceof Clases)) {
            return false;
        }
        Clases other = (Clases) object;
        if ((this.idClase == null && other.idClase != null) || (this.idClase != null && !this.idClase.equals(other.idClase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mibios.jpa.entidades.Clases[ idClase=" + idClase + " ]";
    }
    
}
