/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.cursos;

import com.mibios.dto.cursos.ClaseDatos;
import com.mibios.dto.cursos.ParamInscribirmeACurso;
import com.mibios.dto.cursos.ParamMisCursos;
import com.mibios.dto.cursos.ReturnCursos;
import com.mibios.dto.cursos.ReturnInscribirmeACurso;
import com.mibios.dto.cursos.ReturnMisCursos;
import com.mibios.funciones.FuncionesFecha;
import com.mibios.jpa.entidades.ClaseEstudiantes;
import com.mibios.jpa.entidades.ClaseEstudiantesPK;
import com.mibios.jpa.entidades.Clases;
import com.mibios.jpa.entidades.CuentaCorriente;
import com.mibios.jpa.entidades.Cursos;
import com.mibios.jpa.entidades.Estudiantes;
import com.mibios.jpa.entidades.Personas;
import com.mibios.jpa.entidades.PersonasPK;
import com.mibios.jpa.peristencia.ClaseEstudiantesJpaPersistencia;
import com.mibios.jpa.peristencia.ClasesJpaPersitencia;
import com.mibios.jpa.peristencia.CursosJpaPersistencia;
import com.mibios.jpa.peristencia.EstudiantesJpaPersitencia;
import com.mibios.jpa.peristencia.PersonasJpaPersistencia;
import java.math.BigDecimal;
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
                misCursos.setHorario(FuncionesFecha.mostrarHoraHHMM(clase.getClases().getHorarioComienzo()) + " A " + FuncionesFecha.mostrarHoraHHMM(FuncionesFecha.incrementarHora(clase.getClases().getHorarioComienzo(), clase.getClases().getDuracionHoras())));
                misCursos.setDias(clase.getClases().getDiasClase());
                misCursos.setDocente(clase.getClases().getIdDocente().getPersonas().getNombre1() + " " + clase.getClases().getIdDocente().getPersonas().getApellido1());
                misCursos.setSalon(clase.getClases().getSalon());
                misCursos.setBeca(clase.getPorcentajeBeca().toString());
                misCursos.setCuota(clase.getClases().getImporteCuota().toString());
                misCursos.setAprobadoSn(clase.getAprobadoSn());
                
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
    public List<ReturnCursos> ObtenerCursos() throws Exception
    {    
        List<ReturnCursos> colReturnCursos = new ArrayList<>();
        try
        {
            List<Cursos> colCursos = CursosJpaPersistencia.ObtenerCursos(em);
            for(Cursos curso: colCursos)
            {
                ReturnCursos cursos = new ReturnCursos();
                cursos.setIdCurso(curso.getIdCurso());
                cursos.setNombre(curso.getNombre());
                cursos.setDescripcion(curso.getDescripcion());
                List<ClaseDatos> listaClases = new ArrayList<>();
                
                for(Clases clase: curso.getClasesList())
                {
                    ClaseDatos claseDatos = new ClaseDatos();
                    claseDatos.setNombreCurso(cursos.getNombre() + " - " + cursos.getDescripcion());
                    if(clase.getModalidadClase().equalsIgnoreCase("P"))
                    {
                        claseDatos.setModalidad("Presencial");
                    }
                    else
                    {
                        claseDatos.setModalidad("A Distancia");
                    }
                    claseDatos.setCuota(clase.getImporteCuota().toString());
                    claseDatos.setDias(clase.getDiasClase());
                    claseDatos.setFechaComienzo(FuncionesFecha.mostrarFechaDDMMAAAAString(clase.getFechaComienzo()));
                    claseDatos.setFechaFin(FuncionesFecha.mostrarFechaDDMMAAAAString(clase.getFechaFin()));
                    claseDatos.setHorario(FuncionesFecha.mostrarHoraHHMM(clase.getHorarioComienzo()) + " A " + FuncionesFecha.mostrarHoraHHMM(FuncionesFecha.incrementarHora(clase.getHorarioComienzo(), clase.getDuracionHoras())));
                    claseDatos.setDocente(clase.getIdDocente().getPersonas().getNombre1() + " " + clase.getIdDocente().getPersonas().getApellido1());
                    claseDatos.setSalon(clase.getSalon());
                    claseDatos.setIdClase(clase.getIdClase());
                    listaClases.add(claseDatos);
                }
                cursos.setListaClases(listaClases);
                colReturnCursos.add(cursos);
            }
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
    
    @Override
    public ReturnInscribirmeACurso InscribirmeACurso(ParamInscribirmeACurso xParamInscribirmeACurso) throws Exception
    {    
        ReturnInscribirmeACurso objReturnInscribirmeACurso = new ReturnInscribirmeACurso();
        try
        {
            ClaseEstudiantes objClaseEstudiantes = new ClaseEstudiantes();
            Estudiantes objEstudiantes = EstudiantesJpaPersitencia.ObtenerEstudiante(em, xParamInscribirmeACurso.getTipoDocumento(), xParamInscribirmeACurso.getDocumento());
            
            ClaseEstudiantesPK objClaseEstudiantesPK = new ClaseEstudiantesPK(xParamInscribirmeACurso.getIdClase(), objEstudiantes.getIdEstudiante());
            
            if(!ClaseEstudiantesJpaPersistencia.existeClaseEstudiante(em, objClaseEstudiantesPK))
            {
                objClaseEstudiantes.setClaseEstudiantesPK(objClaseEstudiantesPK);
                objClaseEstudiantes.setAprobadoSn("");
                objClaseEstudiantes.setPorcentajeBeca(new BigDecimal(xParamInscribirmeACurso.getBeca()));

                ClaseEstudiantesJpaPersistencia.InscribirmeACurso(em, objClaseEstudiantes);
                
                Clases objClase = ClasesJpaPersitencia.ObtenerClase(em, xParamInscribirmeACurso.getIdClase());
                String fechaComienzo = objClase.getFechaComienzo();
                String fechaFin = objClase.getFechaFin();
                int mesesDuracion = FuncionesFecha.getCantidadMeses(fechaComienzo, fechaFin);
                
                if(mesesDuracion==0)
                {
                    mesesDuracion=1;
                }
                
                for(int i=1; i<=mesesDuracion; i++)
                {
                    PersonasPK objPersonasPK = new PersonasPK(objEstudiantes.getPersonas().getPersonasPK().getTipoDocumento(), objEstudiantes.getPersonas().getPersonasPK().getDocumento());
                    Personas objPersonas = PersonasJpaPersistencia.ObtenerPersona(em, objPersonasPK);

                    CuentaCorriente objCuentaCorriente = new CuentaCorriente();

                    objCuentaCorriente.setPersonas(objPersonas);
                    objCuentaCorriente.setConcepto("Cuota " + i + " Curso " + objClase.getIdCurso().getNombre());
                    objCuentaCorriente.setTipoMovimiento("D");
                    objCuentaCorriente.setFecha(FuncionesFecha.mostrarFechaAAAAMMDDString(FuncionesFecha.getFechaSistema()));
                    objCuentaCorriente.setHora(FuncionesFecha.getHoraSistema().replace(":", ""));

                    BigDecimal importeCuota = objClase.getImporteCuota();
                    BigDecimal porcentajeBeca = BigDecimal.valueOf(Double.parseDouble(String.valueOf(xParamInscribirmeACurso.getBeca())));
                    BigDecimal total = importeCuota.subtract(importeCuota.multiply(porcentajeBeca.divide(new BigDecimal(100))));
                    
                    objCuentaCorriente.setImporte(total);

                    PersonasJpaPersistencia.IngresarPago(em, objCuentaCorriente);   
                }

                objReturnInscribirmeACurso.setGuardado(true);
                objReturnInscribirmeACurso.setRespuesta("Se inscribio correctamente");
            }
            else
            {
                objReturnInscribirmeACurso.setGuardado(false);
                objReturnInscribirmeACurso.setRespuesta("Ya esta inscripto a este curso");
            }   
        }
        catch(Exception e)
        {
            objReturnInscribirmeACurso.setGuardado(false);
            objReturnInscribirmeACurso.setRespuesta("Error al inscribirse al curso");
            Logger.getLogger(CursosBean.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Beans--> " + e.getMessage() + " [" + this.getClass().getSimpleName() + "]");
        }
        finally{
        }
        return objReturnInscribirmeACurso;
    }
}
