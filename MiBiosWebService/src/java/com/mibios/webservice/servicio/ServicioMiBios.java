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
import com.mibios.dto.webservice.ParamClasesEnDiaParaPersona;
import com.mibios.dto.webservice.ReturnAgregarPersona;
import com.mibios.dto.webservice.ReturnCantidadAlumnosSexo;
import com.mibios.dto.webservice.ReturnListaCursos;
import com.mibios.dto.webservice.ReturnClases;
import com.mibios.dto.webservice.SexoCantidad;
import com.mibios.funciones.FuncionesFecha;
import com.mibios.jpa.entidades.ClaseEstudiantes;
import com.mibios.jpa.entidades.Clases;
import com.mibios.jpa.entidades.Cursos;
import com.mibios.jpa.entidades.Docentes;
import com.mibios.jpa.entidades.Estudiantes;
import com.mibios.jpa.entidades.Personas;
import com.mibios.jpa.entidades.PersonasPK;
import com.mibios.jpa.peristencia.ClasesJpaPersitencia;
import com.mibios.jpa.peristencia.CursosJpaPersistencia;
import com.mibios.jpa.peristencia.EstudiantesJpaPersitencia;
import com.mibios.jpa.peristencia.PersonasJpaPersistencia;
import java.util.ArrayList;
import java.util.Calendar;
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
            String fechaIngreso = FuncionesFecha.mostrarFechaAAAAMMDDString(FuncionesFecha.getFechaSistema());
            
            
            if(!FuncionesFecha.validarDistanciaFechas(xParamAgregarPersona.getFechaNacimiento(), fechaIngreso))
            {
                retorno.setAgregado(false);
                retorno.setMensaje("La Fecha de Nacimiento no puede ser mayor a la Fecha Actual.");
                
                return retorno;
            }
            
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
            objPersonas.setFechaIngreso(fechaIngreso);
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
    
    @WebMethod(operationName = "proximosComienzos")
    public ReturnClases proximosComienzos()
    {
        ReturnClases retorno = new ReturnClases();
        try
        {
            String fechaHoy = FuncionesFecha.mostrarFechaAAAAMMDDString(FuncionesFecha.getFechaSistema());
            String fechaTresMesesDelante = FuncionesFecha.incrementarFecha(fechaHoy, "M", 3);
            List<Clases> colClases = ClasesJpaPersitencia.ObtenerClasesPorFechaComienzo(em, fechaHoy, fechaTresMesesDelante);
            
            for(Clases objClase : colClases)
            {               
                //armo la clase                
                ClaseDatos objClaseDatos = obtenerClaseDatosDeEntidadClases(objClase);
                retorno.getListaClases().add(objClaseDatos);
            }
            
        }
        catch(Exception e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);            
        }        
        
        return retorno;  
    }
    
    @WebMethod(operationName = "clasesDelDia")
    public ReturnClases clasesDelDia(ParamClasesEnDiaParaPersona xParamClasesDelDia)    
    {
        ReturnClases retorno = new ReturnClases();
        try
        {
            //obtengo a la persona, le pido sus clases y veo:
                // hoy cae entre fecha inicio y fin
                // día hoy es uno de los n días en los que hay clases
            
            PersonasPK objPersonasPK = new PersonasPK(xParamClasesDelDia.getTipoDocumento(), xParamClasesDelDia.getDocumento());
            Personas objPersona = PersonasJpaPersistencia.ObtenerPersona(em, objPersonasPK);
            
            List<Clases> clasesParaBuscar = null;
            if(xParamClasesDelDia.getTipoPersona().equalsIgnoreCase("A"))
            {
                clasesParaBuscar = new ArrayList();
                for(ClaseEstudiantes objClaseEstudiantes : objPersona.getEstudiantesList().get(0).getClaseEstudiantesList())
                {
                    clasesParaBuscar.add(objClaseEstudiantes.getClases());
                }                
            }
            else if(xParamClasesDelDia.getTipoPersona().equalsIgnoreCase("D"))
            {
                clasesParaBuscar = objPersona.getDocentesList().get(0).getClasesList();
            }
            
            String fechaHoy = FuncionesFecha.mostrarFechaAAAAMMDDString(FuncionesFecha.getFechaSistema());
            int numeroHoyEnDiaSemana = FuncionesFecha.convertirStringACalendaryyyyMMdd(fechaHoy).get(Calendar.DAY_OF_WEEK); //1 es domingo
            
            for(Clases objClase : clasesParaBuscar)
            {               
                if(objClase.getFechaComienzo().compareTo(fechaHoy) <= 0 && objClase.getFechaFin().compareToIgnoreCase(fechaHoy) >= 0)
                {
                    List<Integer> diasEnLosQueSeDaCurso = obtenerNumeroDiasSemana(objClase.getDiasClase());
                    if(diasEnLosQueSeDaCurso.contains(numeroHoyEnDiaSemana))
                    {
                        //armo la clase                
                        ClaseDatos objClaseDatos = obtenerClaseDatosDeEntidadClases(objClase);
                        retorno.getListaClases().add(objClaseDatos);
                    }
                }
                
            }
            
        }
        catch(Exception e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);            
        }        
        
        return retorno;  
    }
    
    @WebMethod(operationName = "cantidadAlumnosPorSexo")
    public ReturnCantidadAlumnosSexo cantidadAlumnosPorSexo()
    {
        ReturnCantidadAlumnosSexo retorno = new ReturnCantidadAlumnosSexo();
        try
        {
            String fechaHoy = FuncionesFecha.mostrarFechaAAAAMMDDString(FuncionesFecha.getFechaSistema());
            retorno.getLista().add(new SexoCantidad("M", EstudiantesJpaPersitencia.ObtenerCantidadEstudiantesActivosPorSexo(em, "M", fechaHoy)));
            retorno.getLista().add(new SexoCantidad("F", EstudiantesJpaPersitencia.ObtenerCantidadEstudiantesActivosPorSexo(em, "F", fechaHoy)));
            
        }
        catch(Exception e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);            
        }        
        
        return retorno;  
    }
    
    // Funciones auxiliares
    private ClaseDatos obtenerClaseDatosDeEntidadClases(Clases xObjClase)
    {        
        ClaseDatos retorno = new ClaseDatos();        
        retorno.setCuota(xObjClase.getImporteCuota().toString());
        retorno.setDias(xObjClase.getDiasClase());
        retorno.setDocente(xObjClase.getIdDocente().getPersonas().getApellido1() + " " + xObjClase.getIdDocente().getPersonas().getNombre1());
        retorno.setFechaComienzo(FuncionesFecha.mostrarFechaDDMMAAAAString(xObjClase.getFechaComienzo()));
        retorno.setFechaFin(FuncionesFecha.mostrarFechaDDMMAAAAString(xObjClase.getFechaFin()));
        retorno.setHorario(FuncionesFecha.mostrarHoraHHMM(xObjClase.getHorarioComienzo()) + " A " + FuncionesFecha.mostrarHoraHHMM(FuncionesFecha.incrementarHora(xObjClase.getHorarioComienzo(), xObjClase.getDuracionHoras())));
        String modalidad = "Presencial";
        if(xObjClase.getModalidadClase().equalsIgnoreCase("D"))
        {
            modalidad = "A Distancia";
        }
        retorno.setModalidad(modalidad);
        retorno.setNombreCurso(xObjClase.getIdCurso().getNombre() + " " + xObjClase.getIdCurso().getDescripcion());
        retorno.setSalon(xObjClase.getSalon());
        retorno.setIdClase(xObjClase.getIdClase());
        retorno.setCantidadEstudiantesInscriptos(xObjClase.getClaseEstudiantesList().size());
        
        return retorno;
    }
    
    private List<Integer> obtenerNumeroDiasSemana(String xDias)
    {
        List<Integer> retorno = new ArrayList();
        
        
        if(xDias.toLowerCase().contains("domingo")) retorno.add(1);
        if(xDias.toLowerCase().contains("lunes")) retorno.add(2);
        if(xDias.toLowerCase().contains("martes")) retorno.add(3);
        if(xDias.toLowerCase().contains("miercoles") || xDias.toLowerCase().contains("miércoles")) retorno.add(4);
        if(xDias.toLowerCase().contains("jueves")) retorno.add(5);
        if(xDias.toLowerCase().contains("viernes")) retorno.add(6);
        if(xDias.toLowerCase().contains("sabado") || xDias.toLowerCase().contains("sábado")) retorno.add(7);
        
        return retorno;
    }
}
