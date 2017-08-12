/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.institutos.Instituto;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.funciones.FuncionesFecha;
import com.mibios.web.fachada.CursosFachada;
import com.mibios.web.fachada.PersonasFachada;
import com.mibios.webservice.servicio.Clase;
import com.mibios.webservice.servicio.ClaseDatos;
import com.mibios.webservice.servicio.Curso;
import com.mibios.webservice.servicio.Estudiante;
import com.mibios.webservice.servicio.ParamClasesEnDiaParaPersona;
import com.mibios.webservice.servicio.SexoCantidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
public class HomeBean implements Serializable{

    private List<Instituto> institutos;
    private Instituto instituto;
    private MapModel modeloParaMapaCentrosBios;
    private List<ClaseDatos> proximosComienzos; 
    private List<ClaseDatos> clasesUsuario;
    private List<SexoCantidad> listaSexoCantidad;
    private int cantidadCursos;
    private int cantidadClasesActivas;
    private int cantidadEstudiantesActivos;
    
    
    @PostConstruct
    public void init() {
        
        institutos = new ArrayList();
        Instituto instituto1 = new Instituto("Formación Técnico Laboral", "L.A.Herrera 1390 | 2623 24 89/90 | buceo@bios.edu.uy", "-34.901284", "-56.138317");
        Instituto instituto2 = new Instituto("Sistemas y Tecnologías", "18 de julio 1253 | 2902 92 84 - 2901 91 86 | sistemas@bios.edu.uy", "-34.905675", "-56.188999");
        Instituto instituto3 = new Instituto("Negocios y Administración", "Colonia 1001 | 2900 1010 | centro@bios.edu.uy", "-34.911244", "-56.165575");
        Instituto instituto4 = new Instituto("Diseño y Comunicación", "Bvar. España 2472 | 2710 33 73 | edco@bios.edu.uy", "-34.911682", "-56.161276");
        Instituto instituto5 = new Instituto("Economía y Finanzas", "Rivera 1936 | 2402 09 02 | finanzas@bios.edu.uy", "-34.902188", "-56.174043");
        Instituto instituto6 = new Instituto("Instituto Prado", "Millán 3807 | 2336 20 38 - 2336 79 15 | prado@bios.edu.uy", "-34.857230", "-56.196691");
        Instituto instituto7 = new Instituto("Instituto Tres Cruces", "Av. 8 de octubre 2550 | 2487 48 42 | trescruces@bios.edu.uy", "-34.892096", "-56.162462");
        Instituto instituto8 = new Instituto("Instituto Lagomar", "Av. Giannattasio Km 21.300 esq Rio de Janeiro | 2682 05 68 | lagomar@bios.edu.uy", "-34.836991", "-55.985171");
        Instituto instituto9 = new Instituto("Instituto Maldonado", "Rafael Pérez Del Puerto 665 | ( 42 ) 22 84 87 | maldonado@bios.edu.uy", "-34.910121", "-54.955754");

        institutos.add(instituto1);
        institutos.add(instituto2);
        institutos.add(instituto3);
        institutos.add(instituto4);
        institutos.add(instituto5);
        institutos.add(instituto6);
        institutos.add(instituto7);
        institutos.add(instituto8);
        institutos.add(instituto9);
        
        CursosFachada objCursosFachada = new CursosFachada();
        PersonasFachada objPersonasFachada = new PersonasFachada();
        proximosComienzos = objCursosFachada.ProximosComienzos().getListaClases();
        
        ReturnLogin usuarioLogueado = (ReturnLogin)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        
        ParamClasesEnDiaParaPersona objParamClasesEnDiaParaPersona = new ParamClasesEnDiaParaPersona();
        objParamClasesEnDiaParaPersona.setTipoPersona(usuarioLogueado.getTipoPersona());
        objParamClasesEnDiaParaPersona.setTipoDocumento(usuarioLogueado.getTipoDocumento());
        objParamClasesEnDiaParaPersona.setDocumento(usuarioLogueado.getDocumento());
        
        clasesUsuario = objCursosFachada.ClasesDelDia(objParamClasesEnDiaParaPersona).getListaClases();
        
        listaSexoCantidad = objPersonasFachada.CantidadAlumnosPorSexo().getLista();
        for(SexoCantidad dato : listaSexoCantidad)
        {
            if(dato.getSexo().equalsIgnoreCase("M"))
            {
                dato.setSexo("Hombre");
            }
            else if(dato.getSexo().equalsIgnoreCase("F"))
            {
                dato.setSexo("Mujer");
            }
        }
        
        List<Curso> listaCursos = objCursosFachada.ListaCursos().getListaCursos();
        
        cantidadCursos = listaCursos.size();
        cantidadClasesActivas = 0;
        cantidadEstudiantesActivos = 0;
        String fechaHoy = FuncionesFecha.mostrarFechaAAAAMMDDString(FuncionesFecha.getFechaSistema());
        List<String> estudiantesDistintosActivos = new ArrayList();
        for(Curso objCurso : listaCursos)
        {
            for(Clase objClase : objCurso.getListaClases())
            {
                if(FuncionesFecha.mostrarFechaAAAAMMDDString(objClase.getObjClaseDatos().getFechaComienzo()).compareTo(fechaHoy) <= 0 && 
                        FuncionesFecha.mostrarFechaAAAAMMDDString(objClase.getObjClaseDatos().getFechaFin()).compareToIgnoreCase(fechaHoy) >= 0)
                {
                    cantidadClasesActivas++;
                    for(Estudiante objEstudiante : objClase.getListaEstudiantes())
                    {
                        String claveEstudiante = objEstudiante.getTipoDocumento() + "_" + objEstudiante.getDocumento();
                        if(!estudiantesDistintosActivos.contains(claveEstudiante))
                        {
                            estudiantesDistintosActivos.add(claveEstudiante);
                        }
                    }
                }
            }
        }
        cantidadEstudiantesActivos = estudiantesDistintosActivos.size();        
        
    }

    public List<Instituto> getInstitutos() {
        return institutos;
    }

    public void setInstitutos(List<Instituto> institutos) {
        this.institutos = institutos;
    }

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        
        StringBuilder ruta = new StringBuilder();
        ruta.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
        ruta.append("/resources/img/instituto.png");
        
        this.modeloParaMapaCentrosBios = new DefaultMapModel();
          
        //Puntos
        LatLng centro = new LatLng(Double.valueOf(instituto.getLat()), Double.valueOf(instituto.getLng()));

        //Marcadores
        this.modeloParaMapaCentrosBios.addOverlay(new Marker(centro, instituto.getNombre(), instituto.getDireccion(), ruta.toString()));
        
        
        this.instituto = instituto;
    }
    
    public MapModel getModeloParaMapaCentrosBios() {
        return modeloParaMapaCentrosBios;
    }

    public List<ClaseDatos> getProximosComienzos() {
        return proximosComienzos;
    }

    public void setProximosComienzos(List<ClaseDatos> proximosComienzos) {
        this.proximosComienzos = proximosComienzos;
    }

    public List<ClaseDatos> getClasesUsuario() {
        return clasesUsuario;
    }

    public void setClasesUsuario(List<ClaseDatos> clasesUsuario) {
        this.clasesUsuario = clasesUsuario;
    }

    public List<SexoCantidad> getListaSexoCantidad() {
        return listaSexoCantidad;
    }

    public void setListaSexoCantidad(List<SexoCantidad> listaSexoCantidad) {
        this.listaSexoCantidad = listaSexoCantidad;
    }

    public int getCantidadCursos() {
        return cantidadCursos;
    }

    public void setCantidadCursos(int cantidadCursos) {
        this.cantidadCursos = cantidadCursos;
    }

    public int getCantidadClasesActivas() {
        return cantidadClasesActivas;
    }

    public void setCantidadClasesActivas(int cantidadClasesActivas) {
        this.cantidadClasesActivas = cantidadClasesActivas;
    }

    public int getCantidadEstudiantesActivos() {
        return cantidadEstudiantesActivos;
    }

    public void setCantidadEstudiantesActivos(int cantidadEstudiantesActivos) {
        this.cantidadEstudiantesActivos = cantidadEstudiantesActivos;
    }
    
    
    
    
    
}
