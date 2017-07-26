/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personas.obtenerPersona", query = "SELECT p FROM Personas p where p.personasPK.tipoDocumento = :tipoDocumento and p.personasPK.documento = :documento"),
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p"),
    @NamedQuery(name = "Personas.findByTipoDocumento", query = "SELECT p FROM Personas p WHERE p.personasPK.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Personas.findByDocumento", query = "SELECT p FROM Personas p WHERE p.personasPK.documento = :documento"),
    @NamedQuery(name = "Personas.findByApellido1", query = "SELECT p FROM Personas p WHERE p.apellido1 = :apellido1"),
    @NamedQuery(name = "Personas.findByApellido2", query = "SELECT p FROM Personas p WHERE p.apellido2 = :apellido2"),
    @NamedQuery(name = "Personas.findByNombre1", query = "SELECT p FROM Personas p WHERE p.nombre1 = :nombre1"),
    @NamedQuery(name = "Personas.findByNombre2", query = "SELECT p FROM Personas p WHERE p.nombre2 = :nombre2"),
    @NamedQuery(name = "Personas.findByFechaNacimiento", query = "SELECT p FROM Personas p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Personas.findBySexo", query = "SELECT p FROM Personas p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Personas.findByMail", query = "SELECT p FROM Personas p WHERE p.mail = :mail"),
    @NamedQuery(name = "Personas.findByTelefono", query = "SELECT p FROM Personas p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Personas.findByCelular", query = "SELECT p FROM Personas p WHERE p.celular = :celular"),
    @NamedQuery(name = "Personas.findByDireccion", query = "SELECT p FROM Personas p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Personas.findByCiudad", query = "SELECT p FROM Personas p WHERE p.ciudad = :ciudad"),
    @NamedQuery(name = "Personas.findByDepartamento", query = "SELECT p FROM Personas p WHERE p.departamento = :departamento"),
    @NamedQuery(name = "Personas.findByPais", query = "SELECT p FROM Personas p WHERE p.pais = :pais"),
    @NamedQuery(name = "Personas.findByFechaIngreso", query = "SELECT p FROM Personas p WHERE p.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Personas.findByActivo", query = "SELECT p FROM Personas p WHERE p.activo = :activo")})
public class Personas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonasPK personasPK;
    @Column(name = "APELLIDO_1")
    private String apellido1;
    @Column(name = "APELLIDO_2")
    private String apellido2;
    @Column(name = "NOMBRE_1")
    private String nombre1;
    @Column(name = "NOMBRE_2")
    private String nombre2;
    @Column(name = "FECHA_NACIMIENTO")
    private String fechaNacimiento;
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "MAIL")
    private String mail;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "CELULAR")
    private String celular;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Column(name = "PAIS")
    private String pais;
    @Column(name = "FECHA_INGRESO")
    private String fechaIngreso;
    @Column(name = "ACTIVO")
    private String activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personas", fetch = FetchType.LAZY)
    private List<Docentes> docentesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personas", fetch = FetchType.LAZY)
    private List<Estudiantes> estudiantesList;
    @OneToMany(mappedBy = "personas", fetch = FetchType.LAZY)
    private List<CuentaCorriente> cuentaCorrienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personas", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;

    public Personas() {
    }

    public Personas(PersonasPK personasPK) {
        this.personasPK = personasPK;
    }

    public Personas(String tipoDocumento, String documento) {
        this.personasPK = new PersonasPK(tipoDocumento, documento);
    }

    public PersonasPK getPersonasPK() {
        return personasPK;
    }

    public void setPersonasPK(PersonasPK personasPK) {
        this.personasPK = personasPK;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    @XmlTransient
    public List<Docentes> getDocentesList() {
        return docentesList;
    }

    public void setDocentesList(List<Docentes> docentesList) {
        this.docentesList = docentesList;
    }

    @XmlTransient
    public List<Estudiantes> getEstudiantesList() {
        return estudiantesList;
    }

    public void setEstudiantesList(List<Estudiantes> estudiantesList) {
        this.estudiantesList = estudiantesList;
    }

    @XmlTransient
    public List<CuentaCorriente> getCuentaCorrienteList() {
        return cuentaCorrienteList;
    }

    public void setCuentaCorrienteList(List<CuentaCorriente> cuentaCorrienteList) {
        this.cuentaCorrienteList = cuentaCorrienteList;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personasPK != null ? personasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.personasPK == null && other.personasPK != null) || (this.personasPK != null && !this.personasPK.equals(other.personasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mibios.jpa.entidades.Personas[ personasPK=" + personasPK + " ]";
    }
    
}
