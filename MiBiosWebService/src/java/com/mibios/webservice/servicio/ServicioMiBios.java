/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.webservice.servicio;

import com.mibios.dto.cursos.ClaseDatos;
import com.mibios.dto.webservice.Clase;
import com.mibios.dto.webservice.Curso;
import com.mibios.dto.webservice.Estudiante;
import com.mibios.dto.webservice.ParamAgregarPersona;
import com.mibios.dto.webservice.ReturnAgregarPersona;
import com.mibios.dto.webservice.ReturnListaCursos;
import com.mibios.funciones.FuncionesFecha;
import com.mibios.jpa.entidades.ClaseEstudiantes;
import com.mibios.jpa.entidades.Clases;
import com.mibios.jpa.entidades.Cursos;
import com.mibios.jpa.entidades.Docentes;
import com.mibios.jpa.entidades.Estudiantes;
import com.mibios.jpa.entidades.Personas;
import com.mibios.jpa.entidades.PersonasPK;
import com.mibios.jpa.peristencia.CursosJpaPersistencia;
import com.mibios.jpa.peristencia.PersonasJpaPersistencia;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "ServicioMiBios")
public class ServicioMiBios {
    
    @PersistenceContext(unitName = "MiBiosJpaPU")
    private EntityManager em;
    
    @Resource
    UserTransaction transaccion;

        
    @WebMethod(operationName = "listaCursos")
    public ReturnListaCursos listaCursos()
    {
        ReturnListaCursos retorno = null;
        try
        {
            List<Cursos> colCursos = CursosJpaPersistencia.ObtenerCursos(em);
            
            retorno = new ReturnListaCursos();
            
            for(Cursos objCurso : colCursos)
            {               
                //armo el curso
                Curso cursoRetorno = new Curso();
                cursoRetorno.setIdCurso(objCurso.getIdCurso());
                cursoRetorno.setNombre(objCurso.getNombre());             
                
                for(Clases objClase : objCurso.getClasesList())
                {                    
                    //armo la clase
                    Clase claseRetorno = new Clase();                    
                    claseRetorno.setObjClaseDatos(obtenerClaseDatosDeEntidadClases(objClase));
                    
                    for(ClaseEstudiantes objClaseEstudiante : objClase.getClaseEstudiantesList())
                    {                        
                        //armo el estudiante
                        Estudiante estudianteRetorno = new Estudiante();
                        estudianteRetorno.setTipoDocumento(objClaseEstudiante.getEstudiantes().getPersonas().getPersonasPK().getTipoDocumento());
                        estudianteRetorno.setDocumento(objClaseEstudiante.getEstudiantes().getPersonas().getPersonasPK().getDocumento());
                        estudianteRetorno.setApellido1(objClaseEstudiante.getEstudiantes().getPersonas().getApellido1());
                        estudianteRetorno.setNombre1(objClaseEstudiante.getEstudiantes().getPersonas().getNombre1());
                        
                        claseRetorno.getListaEstudiantes().add(estudianteRetorno);
                    }
                    cursoRetorno.getListaClases().add(claseRetorno);
                }                
                retorno.getListaCursos().add(cursoRetorno);                
            }
            
        }
        catch(Exception e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);            
        }        
        
        return retorno;        
    }
    
    @WebMethod(operationName = "agregarPersona")
    public ReturnAgregarPersona agregarPersona(ParamAgregarPersona xParamAgregarPersona)    
    {
        ReturnAgregarPersona retorno = new ReturnAgregarPersona();
        try
        {
            PersonasPK objPersonasPK = new PersonasPK();
            objPersonasPK.setTipoDocumento(xParamAgregarPersona.getTipoDocumento());
            objPersonasPK.setDocumento(xParamAgregarPersona.getDocumento());
            
            if(PersonasJpaPersistencia.ExistePersona(em, objPersonasPK))
            {
                retorno.setAgregado(false);
                retorno.setMensaje("Ya existe una persona con ese tipo documento y documento.");
                
                return retorno;
            }
            
            Personas objPersonas = new Personas();
            objPersonas.setActivo("S");
            objPersonas.setApellido1(xParamAgregarPersona.getApellido1());
            objPersonas.setFechaNacimiento(xParamAgregarPersona.getFechaNacimiento());
            objPersonas.setNombre1(xParamAgregarPersona.getNombre1());
            
            objPersonas.setApellido2("");
            objPersonas.setCelular("");
            objPersonas.setCiudad("");
            objPersonas.setDepartamento("");
            objPersonas.setDireccion("");
            objPersonas.setFechaIngreso(FuncionesFecha.mostrarFechaAAAAMMDDString(FuncionesFecha.getFechaSistema()));
            objPersonas.setMail("");
            objPersonas.setNombre2("");
            objPersonas.setPais("");
            objPersonas.setSexo("");
            objPersonas.setTelefono("");
           
            
            objPersonas.setPersonasPK(objPersonasPK);
            
            if(xParamAgregarPersona.getTipoPersona().equalsIgnoreCase("A"))
            {
                Estudiantes objEstudiantes = new Estudiantes();
                objEstudiantes.setPersonas(objPersonas);      
                objPersonas.setEstudiantesList(new ArrayList());
                objPersonas.getEstudiantesList().add(objEstudiantes);
            }
            else if(xParamAgregarPersona.getTipoPersona().equalsIgnoreCase("D"))
            {
                Docentes objDocentes = new Docentes();
                objDocentes.setPersonas(objPersonas);
                objPersonas.setDocentesList(new ArrayList());
                objPersonas.getDocentesList().add(objDocentes);
            }
            
            transaccion.begin();
            em.persist(objPersonas);            
            transaccion.commit();
                        
            retorno.setAgregado(true);
            retorno.setMensaje("Persona ingresada correctamente.");
        }
        catch(Exception e)
        {
            try
            {
                transaccion.rollback();
            }
            catch(Exception ex)
            {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);            
            }
            
            retorno.setAgregado(false);
            retorno.setMensaje(e.getMessage());
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);            
        }   
        return retorno;
    }
    
    private ClaseDatos obtenerClaseDatosDeEntidadClases(Clases xObjClase)
    {        
        ClaseDatos retorno = new ClaseDatos();        
        retorno.setCuota(xObjClase.getImporteCuota().toString());
        retorno.setDias(xObjClase.getDiasClase());
        retorno.setDocente(xObjClase.getIdDocente().getPersonas().getApellido1() + " " + xObjClase.getIdDocente().getPersonas().getNombre1());
        retorno.setFechaComienzo(xObjClase.getFechaComienzo());
        retorno.setFechaFin(xObjClase.getFechaFin());
        retorno.setHorario(xObjClase.getHorarioComienzo());
        retorno.setModalidad(xObjClase.getModalidadClase());
        retorno.setNombreCurso(xObjClase.getIdCurso().getNombre());
        retorno.setSalon(xObjClase.getSalon());
        
        return retorno;
    }
}
