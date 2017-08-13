/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.cursos.ClaseDatos;
import com.mibios.dto.cursos.CursosDatos;
import com.mibios.dto.cursos.ParamInscribirmeACurso;
import com.mibios.dto.cursos.ReturnCursos;
import com.mibios.dto.cursos.ReturnInscribirmeACurso;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.web.fachada.CursosFachada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Maxi
 */
@ManagedBean
@ViewScoped
public class CursosBean implements Serializable {

    private List<CursosDatos> listaCursos;
    private List<ClaseDatos> listaClases;
    private int idClaseInscripcion;
    private int becaInscripcion;
    
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

    public int getIdClaseInscripcion() {
        return idClaseInscripcion;
    }

    public void setIdClaseInscripcion(int idClaseInscripcion) {
        this.idClaseInscripcion = idClaseInscripcion;
    }

    public int getBecaInscripcion() {
        return becaInscripcion;
    }

    public void setBecaInscripcion(int becaInscripcion) {
        this.becaInscripcion = becaInscripcion;
    }

    public void inscribirmeACurso(int xIdClase) 
    {       
        ParamInscribirmeACurso objParamInscribirmeACurso = new ParamInscribirmeACurso();
        ReturnInscribirmeACurso objReturnInscribirmeACurso = new ReturnInscribirmeACurso();
        CursosFachada cursosFachada = new CursosFachada();
        
        try {
            
            RequestContext context = RequestContext.getCurrentInstance();
            boolean inscipcionCorrecta = false;
            ReturnLogin objReturnSesion = (ReturnLogin)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
            objParamInscribirmeACurso.setTipoDocumento(objReturnSesion.getTipoDocumento());
            objParamInscribirmeACurso.setDocumento(objReturnSesion.getDocumento());
            objParamInscribirmeACurso.setIdClase(xIdClase);
            
            objParamInscribirmeACurso.setBeca(becaInscripcion);

            objReturnInscribirmeACurso = cursosFachada.InscribirmeACurso(objParamInscribirmeACurso);

            if(objReturnInscribirmeACurso.getGuardado()){
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(objReturnInscribirmeACurso.getRespuesta()));
                inscipcionCorrecta = true;
            }
            else
            {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(objReturnInscribirmeACurso.getRespuesta()));
                inscipcionCorrecta = false;
            }
            context.addCallbackParam("inscipcionCorrecta", inscipcionCorrecta);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(DatosPersonalesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
