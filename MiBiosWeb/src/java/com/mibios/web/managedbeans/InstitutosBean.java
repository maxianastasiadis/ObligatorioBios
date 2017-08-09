/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
public class InstitutosBean {

    private MapModel modeloParaMapaCentrosBios;
     
    @PostConstruct
    public void init() {
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
        modeloParaMapaCentrosBios.addOverlay(new Marker(centroTecnicoLaboral, "Formación Técnico Laboral"));
        modeloParaMapaCentrosBios.addOverlay(new Marker(centroSistemasTecnologias, "Sistemas y Tecnologías"));
        modeloParaMapaCentrosBios.addOverlay(new Marker(centroNegociosAdministracion, "Negocios y Administración"));
        modeloParaMapaCentrosBios.addOverlay(new Marker(centroDisenioComunicacion, "Diseño y Comunicación"));
        modeloParaMapaCentrosBios.addOverlay(new Marker(centroEconomiaFinanzas, "Economía y Finanzas"));
        modeloParaMapaCentrosBios.addOverlay(new Marker(institutoPrado, "Instituto Prado"));
        modeloParaMapaCentrosBios.addOverlay(new Marker(institutoTresCruces, "Instituto Tres Cruces"));
        modeloParaMapaCentrosBios.addOverlay(new Marker(institutoLagomar, "Instituto Lagomar"));
        modeloParaMapaCentrosBios.addOverlay(new Marker(institutoMaldonado, "Instituto Maldonado"));
    }
  
    public MapModel getModeloParaMapaCentrosBios() {
        return modeloParaMapaCentrosBios;
    }
    
}
