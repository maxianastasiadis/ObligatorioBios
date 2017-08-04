/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.cursos;

import com.mibios.dto.cursos.ParamCursos;
import com.mibios.dto.cursos.ParamMisCursos;
import com.mibios.dto.cursos.ReturnCursos;
import com.mibios.dto.cursos.ReturnMisCursos;
import com.mibios.jpa.entidades.ClaseEstudiantes;
import com.mibios.jpa.peristencia.EstudiantesJpaPersitencia;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maxi
 */
@Stateless(mappedName="CursosBean")
public class CursosBean implements CursosBeanLocal {

    @PersistenceContext(unitName = "MiBiosJpaPU")
    private EntityManager em;
    
    @Override
    public List<ReturnMisCursos> ObtenerMisCursos(ParamMisCursos xParamMisCursos) throws Exception
    {    
        List<ReturnMisCursos> colReturnMisCursos = new ArrayList<>();
        try
        {
            List<ClaseEstudiantes> colClaseEstudiante = EstudiantesJpaPersitencia.ObtenerEstudiante(em, xParamMisCursos.getTipoDocumento(), xParamMisCursos.getDocumento()).getClaseEstudiantesList();
            for(ClaseEstudiantes clase: colClaseEstudiante)
            {
                ReturnMisCursos misCursos = new ReturnMisCursos();
                misCursos.setIdCurso(clase.getClases().getIdCurso().getIdCurso());
                misCursos.setNombre(clase.getClases().getIdCurso().getNombre());
                misCursos.setDescripcion(clase.getClases().getIdCurso().getDescripcion());
                misCursos.setFechaComienzo(clase.getClases().getFechaComienzo());
                misCursos.setFechaFin(clase.getClases().getFechaFin());
                misCursos.setHorario(clase.getClases().getHorarioComienzo() + " A " + clase.getClases().getDuracionHoras());
                misCursos.setDias(clase.getClases().getDiasClase());
                
                colReturnMisCursos.add(misCursos);
            }
        }
        catch(Exception e)
        {
            Logger.getLogger(CursosBean.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Beans--> " + e.getMessage() + " [" + this.getClass().getSimpleName() + "]");
        }
        finally{
        }
        return colReturnMisCursos;
    }
    
    @Override
    public List<ReturnCursos> ObtenerCursos(ParamCursos xParamCursos) throws Exception
    {    
        List<ReturnCursos> colReturnCursos = new ArrayList<>();
        try
        {
            
        }
        catch(Exception e)
        {
            Logger.getLogger(CursosBean.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Beans--> " + e.getMessage() + " [" + this.getClass().getSimpleName() + "]");
        }
        finally{
        }
        return colReturnCursos;
    } 
}
