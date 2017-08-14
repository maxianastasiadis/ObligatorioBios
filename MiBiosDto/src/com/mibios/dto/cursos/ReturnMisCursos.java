/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.dto.cursos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Maxi
 */
public class ReturnMisCursos implements Serializable{
    
    private int idCurso;
    private String nombre;
    private String descripcion;
    private String fechaComienzo;
    private String fechaFin;
    private String dias;
    private String horario;
    private String salon;
    private String docente;
    private String cuota;
    private String beca;
    private String aprobadoSn;
    private String modalidad;
    private List<AlumnoDatos> listaAlumnoDatos;
    
    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    public String getBeca() {
        return beca;
    }

    public void setBeca(String beca) {
        this.beca = beca;
    }

    public String getAprobadoSn() {
        return aprobadoSn;
    }

    public void setAprobadoSn(String aprobadoSn) {
        this.aprobadoSn = aprobadoSn;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public List<AlumnoDatos> getListaAlumnoDatos() {
        return listaAlumnoDatos;
    }

    public void setListaAlumnoDatos(List<AlumnoDatos> listaAlumnoDatos) {
        this.listaAlumnoDatos = listaAlumnoDatos;
    }
}
