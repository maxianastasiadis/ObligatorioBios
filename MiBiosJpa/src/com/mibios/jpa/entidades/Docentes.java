/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "docentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docentes.existeDocente", query = "SELECT count(d) FROM Docentes d where d.personas.personasPK.tipoDocumento = :tipoDocumento and d.personas.personasPK.documento = :documento"),
    @NamedQuery(name = "Docentes.findAll", query = "SELECT d FROM Docentes d"),
    @NamedQuery(name = "Docentes.findByIdDocente", query = "SELECT d FROM Docentes d WHERE d.idDocente = :idDocente")})
public class Docentes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DOCENTE")
    private Integer idDocente;
    @JoinColumns({
        @JoinColumn(name = "TIPO_DOCUMENTO", referencedColumnName = "TIPO_DOCUMENTO"),
        @JoinColumn(name = "DOCUMENTO", referencedColumnName = "DOCUMENTO")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas personas;
    @OneToMany(mappedBy = "idDocente", fetch = FetchType.LAZY)
    private List<Clases> clasesList;

    public Docentes() {
    }

    public Docentes(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    @XmlTransient
    public List<Clases> getClasesList() {
        return clasesList;
    }

    public void setClasesList(List<Clases> clasesList) {
        this.clasesList = clasesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocente != null ? idDocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docentes)) {
            return false;
        }
        Docentes other = (Docentes) object;
        if ((this.idDocente == null && other.idDocente != null) || (this.idDocente != null && !this.idDocente.equals(other.idDocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mibios.jpa.entidades.Docentes[ idDocente=" + idDocente + " ]";
    }
    
}
