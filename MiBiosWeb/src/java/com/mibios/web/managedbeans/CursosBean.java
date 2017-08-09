/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.cursos.ClaseDatos;
import com.mibios.dto.cursos.CursosDatos;
import com.mibios.dto.cursos.ReturnCursos;
import com.mibios.web.fachada.CursosFachada;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Maxi
 */
@ManagedBean
@RequestScoped
public class CursosBean {

    private List<CursosDatos> listaCursos;
    private List<ClaseDatos> listaClases;
    
    public CursosBean() 
    {
        try 
        {
            listaCursos = new ArrayList();
            CursosFachada cursosFachada = new CursosFachada();
            
            List<ReturnCursos> colReturnCursos = cursosFachada.ObtenerCursos();
            for(ReturnCursos cursos : colReturnCursos)
            {
                CursosDatos cursoDatos = new CursosDatos();
                
                cursoDatos.setIdCurso(cursos.getIdCurso());
                cursoDatos.setNombre(cursos.getNombre() + " - " + cursos.getDescripcion());
                cursoDatos.setListaClases(cursos.getListaClases());
                
                listaCursos.add(cursoDatos);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(CuentaCorrienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CursosDatos> getListaCursos() {
        return listaCursos;
    } 

    public List<ClaseDatos> getListaClases() {
        return listaClases;
    }

    public void setListaClases(List<ClaseDatos> listaClases) {
        this.listaClases = listaClases;
    }
}
