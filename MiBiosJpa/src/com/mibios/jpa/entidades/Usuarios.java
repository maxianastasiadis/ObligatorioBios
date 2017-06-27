/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maxi
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.login", query = "SELECT u FROM Usuarios u WHERE u.usuariosPK.tipoPersona = :tipoPersona and u.usuariosPK.tipoDocumento = :tipoDocumento and u.usuariosPK.documento = :documento and u.clave = :clave"),
    @NamedQuery(name = "Usuarios.controlLogin", query = "SELECT count(u) FROM Usuarios u WHERE u.usuariosPK.tipoPersona = :tipoPersona and u.usuariosPK.tipoDocumento = :tipoDocumento and u.usuariosPK.documento = :documento and u.clave = :clave"),
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByTipoPersona", query = "SELECT u FROM Usuarios u WHERE u.usuariosPK.tipoPersona = :tipoPersona"),
    @NamedQuery(name = "Usuarios.findByTipoDocumento", query = "SELECT u FROM Usuarios u WHERE u.usuariosPK.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Usuarios.findByDocumento", query = "SELECT u FROM Usuarios u WHERE u.usuariosPK.documento = :documento"),
    @NamedQuery(name = "Usuarios.findByClave", query = "SELECT u FROM Usuarios u WHERE u.clave = :clave"),
    @NamedQuery(name = "Usuarios.findByFechaIngreso", query = "SELECT u FROM Usuarios u WHERE u.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Usuarios.findByActivo", query = "SELECT u FROM Usuarios u WHERE u.activo = :activo")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuariosPK usuariosPK;
    @Column(name = "CLAVE")
    private String clave;
    @Column(name = "FECHA_INGRESO")
    private String fechaIngreso;
    @Column(name = "ACTIVO")
    private String activo;

    public Usuarios() {
    }

    public Usuarios(UsuariosPK usuariosPK) {
        this.usuariosPK = usuariosPK;
    }

    public Usuarios(String tipoPersona, String tipoDocumento, String documento) {
        this.usuariosPK = new UsuariosPK(tipoPersona, tipoDocumento, documento);
    }

    public UsuariosPK getUsuariosPK() {
        return usuariosPK;
    }

    public void setUsuariosPK(UsuariosPK usuariosPK) {
        this.usuariosPK = usuariosPK;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariosPK != null ? usuariosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuariosPK == null && other.usuariosPK != null) || (this.usuariosPK != null && !this.usuariosPK.equals(other.usuariosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mibios.jpa.entidades.Usuarios[ usuariosPK=" + usuariosPK + " ]";
    }
    
}
