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
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Maxi
 */
@ManagedBean
@ViewScoped
public class CursosBean {

    private List<CursosDatos> listaCursos;
    private List<ClaseDatos> listaClases;
    private int idClaseInscripcion;
    
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
    
    public void InscribirmeACurso() {
        System.out.println("llegaasdfasdfadsfasdfasdf = ");
//        System.out.println("xObjClases = " + xObjClases.getIdClase());
//        ParamInscribirmeACurso objParamInscribirmeACurso = new ParamInscribirmeACurso();
//        ReturnInscribirmeACurso objReturnInscribirmeACurso = new ReturnInscribirmeACurso();
//        CursosFachada cursosFachada = new CursosFachada();
//        try {
//            System.out.println("xObjClases = ");
//            ReturnLogin objReturnSesion = (ReturnLogin)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
//            objParamInscribirmeACurso.setTipoDocumento(objReturnSesion.getTipoDocumento());
//            objParamInscribirmeACurso.setDocumento(objReturnSesion.getDocumento());
//            objParamInscribirmeACurso.setIdClase(xObjClases.getIdClase());
//            
//            objReturnInscribirmeACurso = cursosFachada.InscribirmeACurso(objParamInscribirmeACurso);
//
//            if(objReturnInscribirmeACurso.getGuardado()){
//                //mensaje Se ingreso el pago correctamente
//                FacesContext facesContext = FacesContext.getCurrentInstance();
//                facesContext.addMessage(null, new FacesMessage(objReturnInscribirmeACurso.getRespuesta()));
//            }
//            else
//            {
//                FacesContext facesContext = FacesContext.getCurrentInstance();
//                facesContext.addMessage(null, new FacesMessage(objReturnInscribirmeACurso.getRespuesta()));
//            }
//
//        } 
//        catch (Exception ex) 
//        {
//            Logger.getLogger(DatosPersonalesBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public void cargarClaseActual(ClaseDatos xClaseActual)
    {
        this.idClaseInscripcion = xClaseActual.getIdClase();
    }
}
