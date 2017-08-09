/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Maxi
 */
@ManagedBean
@ViewScoped
public class InstitutosBean implements Serializable{

    private MapModel modeloParaMapaCentrosBios;
    private Marker centroBios;
     
    @PostConstruct
    public void init() {
        
        StringBuilder ruta = new StringBuilder();
        ruta.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
        ruta.append("/resources/img/instituto.png");
        
        modeloParaMapaCentrosBios = new DefaultMapModel();
          
        //Puntos
        LatLng centroTecnicoLaboral = new LatLng(-34.901284, -56.138317);
        LatLng centroSistemasTecnologias = new LatLng(-34.905675, -56.188999);
        LatLng centroNegociosAdministracion = new LatLng(-34.911244, -56.165575);
        LatLng centroDisenioComunicacion = new LatLng(-34.911682, -56.161276);
        LatLng centroEconomiaFinanzas = new LatLng(-34.902188, -56.174043);
        LatLng institutoPrado = new LatLng(-34.857230, -56.196691);
        LatLng institutoTresCruces = new LatLng(-34.892096, -56.162462);
        LatLng institutoLagomar = new LatLng(-34.836991, -55.985171);
        LatLng institutoMaldonado = new LatLng(-34.910121, -54.955754);

        //Marcadores
        modeloParaMapaCentrosBios.addOverlay(new Marker(centroTecnicoLaboral, "Formación Técnico Laboral", "L.A.Herrera 1390 | 2623 24 89/90 | buceo@bios.edu.uy",ruta.toString()));
        modeloParaMapaCentrosBios.addOverlay(new Marker(centroSistemasTecnologias, "Sistemas y Tecnologías", "18 de julio 1253 | 2902 92 84 - 2901 91 86 | sistemas@bios.edu.uy",ruta.toString()));
        modeloParaMapaCentrosBios.addOverlay(new Marker(centroNegociosAdministracion, "Negocios y Administración", "Colonia 1001 | 2900 1010 | centro@bios.edu.uy",ruta.toString()));
        modeloParaMapaCentrosBios.addOverlay(new Marker(centroDisenioComunicacion, "Diseño y Comunicación", "Bvar. España 2472 | 2710 33 73 | edco@bios.edu.uy",ruta.toString()));
        modeloParaMapaCentrosBios.addOverlay(new Marker(centroEconomiaFinanzas, "Economía y Finanzas", "Rivera 1936 | 2402 09 02 | finanzas@bios.edu.uy",ruta.toString()));
        modeloParaMapaCentrosBios.addOverlay(new Marker(institutoPrado, "Instituto Prado", "Millán 3807 | 2336 20 38 - 2336 79 15 | prado@bios.edu.uy",ruta.toString()));
        modeloParaMapaCentrosBios.addOverlay(new Marker(institutoTresCruces, "Instituto Tres Cruces", "Av. 8 de octubre 2550 | 2487 48 42 | trescruces@bios.edu.uy",ruta.toString()));
        modeloParaMapaCentrosBios.addOverlay(new Marker(institutoLagomar, "Instituto Lagomar", "Av. Giannattasio Km 21.300 esq Rio de Janeiro | 2682 05 68 | lagomar@bios.edu.uy",ruta.toString()));
        modeloParaMapaCentrosBios.addOverlay(new Marker(institutoMaldonado, "Instituto Maldonado", "Rafael Pérez Del Puerto 665 | ( 42 ) 22 84 87 | maldonado@bios.edu.uy",ruta.toString()));
    }
  
    public MapModel getModeloParaMapaCentrosBios() {
        return modeloParaMapaCentrosBios;
    }
    
    public void onCentroBiosSelect(OverlaySelectEvent event) {
        centroBios = (Marker) event.getOverlay();
    }
      
    public Marker getCentroBios() {
        return centroBios;
    }
    
}
