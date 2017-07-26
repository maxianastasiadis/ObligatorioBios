/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maxi
 */
@Entity
@Table(name = "cuenta_corriente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaCorriente.findAll", query = "SELECT c FROM CuentaCorriente c"),
    @NamedQuery(name = "CuentaCorriente.findByTransaccion", query = "SELECT c FROM CuentaCorriente c WHERE c.transaccion = :transaccion"),
    @NamedQuery(name = "CuentaCorriente.findByImporte", query = "SELECT c FROM CuentaCorriente c WHERE c.importe = :importe"),
    @NamedQuery(name = "CuentaCorriente.findByConcepto", query = "SELECT c FROM CuentaCorriente c WHERE c.concepto = :concepto"),
    @NamedQuery(name = "CuentaCorriente.findByTipoMovimiento", query = "SELECT c FROM CuentaCorriente c WHERE c.tipoMovimiento = :tipoMovimiento"),
    @NamedQuery(name = "CuentaCorriente.findByFecha", query = "SELECT c FROM CuentaCorriente c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "CuentaCorriente.findByHora", query = "SELECT c FROM CuentaCorriente c WHERE c.hora = :hora")})
public class CuentaCorriente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRANSACCION")
    private Integer transaccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "IMPORTE")
    private BigDecimal importe;
    @Column(name = "CONCEPTO")
    private String concepto;
    @Column(name = "TIPO_MOVIMIENTO")
    private String tipoMovimiento;
    @Column(name = "FECHA")
    private String fecha;
    @Column(name = "HORA")
    private String hora;
    @JoinColumns({
        @JoinColumn(name = "TIPO_DOCUMENTO", referencedColumnName = "TIPO_DOCUMENTO"),
        @JoinColumn(name = "DOCUMENTO", referencedColumnName = "DOCUMENTO")})
    @ManyToOne(fetch = FetchType.LAZY)
    private Personas personas;

    public CuentaCorriente() {
    }

    public CuentaCorriente(Integer transaccion) {
        this.transaccion = transaccion;
    }

    public Integer getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Integer transaccion) {
        this.transaccion = transaccion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transaccion != null ? transaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaCorriente)) {
            return false;
        }
        CuentaCorriente other = (CuentaCorriente) object;
        if ((this.transaccion == null && other.transaccion != null) || (this.transaccion != null && !this.transaccion.equals(other.transaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mibios.jpa.entidades.CuentaCorriente[ transaccion=" + transaccion + " ]";
    }
    
}
