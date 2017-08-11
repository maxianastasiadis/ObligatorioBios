/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.institutos.Instituto;
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
}
