/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.cursos.MisCursosDatos;
import com.mibios.dto.cursos.ParamMisCursos;
import com.mibios.dto.cursos.ReturnMisCursos;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.web.fachada.CursosFachada;
import com.mibios.web.fachada.PersonasFachada;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Maxi
 */
@ManagedBean
@RequestScoped
public class MisCursosBean {

    private List<MisCursosDatos> misCursos;
    
    public MisCursosBean() {
        try {
            misCursos = new ArrayList();
            CursosFachada cursosFachada = new CursosFachada();
            ReturnLogin obj = (ReturnLogin)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
            
            ParamMisCursos xParamMisCursos = new ParamMisCursos();
            xParamMisCursos.setTipoDocumento(obj.getTipoDocumento());
            xParamMisCursos.setDocumento(obj.getDocumento());
            
            List<ReturnMisCursos> colReturnMisCursos = cursosFachada.ObtenerMisCursos(xParamMisCursos);
            for(ReturnMisCursos cursos : colReturnMisCursos)
            {
                MisCursosDatos cursoDatos = new MisCursosDatos();
                
                cursoDatos.setIdCurso(cursos.getIdCurso());
                cursoDatos.setNombre(cursos.getNombre());
                cursoDatos.setDescripcion(cursos.getDescripcion());
                
                misCursos.add(cursoDatos);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(CuentaCorrienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<MisCursosDatos> getMisCursos() {
        return misCursos;
    }    
}
