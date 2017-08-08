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
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Maxi
 */
@ManagedBean
@RequestScoped
public class CursosBean {

    private List<CursosDatos> listaCursos;
    private List<ClaseDatos> listaClases;
    private MapModel modeloParaMapaCentrosBios;
    
    public CursosBean() {
        try {
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
            
            modeloParaMapaCentrosBios = new DefaultMapModel();
          
            //Puntos
            LatLng centroTecnicoLaboral = new LatLng(-34.9014832, -56.1384523);
            LatLng centroSistemasTecnologias = new LatLng(-34.905768,-56.1911781);
            LatLng centroNegociosAdministracion = new LatLng(-34.9051878,-56.1969757);
            LatLng centroDisenioComunicacion = new LatLng(-34.9117959,-56.1634542);
            LatLng centroEconomiaFinanzas = new LatLng(-34.9022061,-56.1761806);

            //Marcadores
            modeloParaMapaCentrosBios.addOverlay(new Marker(centroTecnicoLaboral, "Formación Técnico Laboral"));
            modeloParaMapaCentrosBios.addOverlay(new Marker(centroSistemasTecnologias, "Sistemas y Tecnologías"));
            modeloParaMapaCentrosBios.addOverlay(new Marker(centroNegociosAdministracion, "Negocios y Administración"));
            modeloParaMapaCentrosBios.addOverlay(new Marker(centroDisenioComunicacion, "Diseño y Comunicación"));
            modeloParaMapaCentrosBios.addOverlay(new Marker(centroEconomiaFinanzas, "Economía y Finanzas"));
            
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

    public MapModel getModeloParaMapaCentrosBios() {
        return modeloParaMapaCentrosBios;
    }

  
    
    
    
}
