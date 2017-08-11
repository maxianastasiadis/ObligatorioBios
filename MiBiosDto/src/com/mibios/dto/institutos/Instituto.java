/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.dto.institutos;

import java.io.Serializable;

/**
 *
 * @author Maxi
 */
public class Instituto implements Serializable{
    
    private String nombre;
    private String direccion;
    private String lat;
    private String lng;
    private String coordenada;

    public Instituto(String nombre, String direccion, String lat, String lng) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.lat = lat;
        this.lng = lng;
        this.coordenada = lat + ", " + lng;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCoordenada() {
        return coordenada;
    }
    
}
