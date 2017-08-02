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
import javax.persistence.JoinColumns;
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
@Table(name = "estudiantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiantes.existeEstudiante", query = "SELECT count(e) FROM Estudiantes e where e.personas.personasPK.tipoDocumento = :tipoDocumento and e.personas.personasPK.documento = :documento"),
    @NamedQuery(name = "Estudiantes.obtenerEstudiante", query = "SELECT e FROM Estudiantes e where e.personas.personasPK.tipoDocumento = :tipoDocumento and e.personas.personasPK.documento = :documento"),
    @NamedQuery(name = "Estudiantes.findAll", query = "SELECT e FROM Estudiantes e"),
    @NamedQuery(name = "Estudiantes.findByIdEstudiante", query = "SELECT e FROM Estudiantes e WHERE e.idEstudiante = :idEstudiante")})
public class Estudiantes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTUDIANTE")
    private Integer idEstudiante;
    @JoinColumns({
        @JoinColumn(name = "TIPO_DOCUMENTO", referencedColumnName = "TIPO_DOCUMENTO"),
        @JoinColumn(name = "DOCUMENTO", referencedColumnName = "DOCUMENTO")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas personas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiantes", fetch = FetchType.LAZY)
    private List<ClaseEstudiantes> claseEstudiantesList;

    public Estudiantes() {
    }

    public Estudiantes(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
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
        hash += (idEstudiante != null ? idEstudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiantes)) {
            return false;
        }
        Estudiantes other = (Estudiantes) object;
        if ((this.idEstudiante == null && other.idEstudiante != null) || (this.idEstudiante != null && !this.idEstudiante.equals(other.idEstudiante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mibios.jpa.entidades.Estudiantes[ idEstudiante=" + idEstudiante + " ]";
    }
    
}
