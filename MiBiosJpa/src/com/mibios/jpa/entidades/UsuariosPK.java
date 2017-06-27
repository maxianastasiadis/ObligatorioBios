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
public class UsuariosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "TIPO_PERSONA")
    private String tipoPersona;
    @Basic(optional = false)
    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;
    @Basic(optional = false)
    @Column(name = "DOCUMENTO")
    private String documento;

    public UsuariosPK() {
    }

    public UsuariosPK(String tipoPersona, String tipoDocumento, String documento) {
        this.tipoPersona = tipoPersona;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoPersona != null ? tipoPersona.hashCode() : 0);
        hash += (tipoDocumento != null ? tipoDocumento.hashCode() : 0);
        hash += (documento != null ? documento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosPK)) {
            return false;
        }
        UsuariosPK other = (UsuariosPK) object;
        if ((this.tipoPersona == null && other.tipoPersona != null) || (this.tipoPersona != null && !this.tipoPersona.equals(other.tipoPersona))) {
            return false;
        }
        if ((this.tipoDocumento == null && other.tipoDocumento != null) || (this.tipoDocumento != null && !this.tipoDocumento.equals(other.tipoDocumento))) {
            return false;
        }
        if ((this.documento == null && other.documento != null) || (this.documento != null && !this.documento.equals(other.documento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mibios.jpa.entidades.UsuariosPK[ tipoPersona=" + tipoPersona + ", tipoDocumento=" + tipoDocumento + ", documento=" + documento + " ]";
    }
    
}
