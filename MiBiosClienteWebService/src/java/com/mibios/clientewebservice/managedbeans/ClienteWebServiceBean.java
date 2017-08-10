/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.clientewebservice.managedbeans;

import com.mibios.clientewebservice.clientews.ReturnAgregarPersona;
import com.mibios.clientewebservice.clientews.ReturnListaCursos;
import com.mibios.clientewebservice.clientews.ServicioMiBios_Service;
import java.io.Serializable;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author usuario
 */
@ManagedBean
@RequestScoped
public class ClienteWebServiceBean implements Serializable{

    //@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/MiBiosWebService/ServicioMiBios.wsdl")
    private ServicioMiBios_Service service;
    
    private ReturnListaCursos objReturnListaCursos;
    private ReturnAgregarPersona objReturnAgregarPersona;

    public ReturnListaCursos getObjReturnListaCursos() {
        return objReturnListaCursos;
    }

    public void setObjReturnListaCursos(ReturnListaCursos objReturnListaCursos) {
        this.objReturnListaCursos = objReturnListaCursos;
    }

    public ReturnAgregarPersona getObjReturnAgregarPersona() {
        return objReturnAgregarPersona;
    }

    public void setObjReturnAgregarPersona(ReturnAgregarPersona objReturnAgregarPersona) {
        this.objReturnAgregarPersona = objReturnAgregarPersona;
    }

    public ClienteWebServiceBean() {
        try
        {
            //llamo a obtener los cursos        
            this.objReturnListaCursos = listaCursos();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }

    private com.mibios.clientewebservice.clientews.ReturnListaCursos listaCursos() throws Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        service = new ServicioMiBios_Service(new URL("http://localhost:8080/MiBiosWebService/ServicioMiBios?wsdl"));
        com.mibios.clientewebservice.clientews.ServicioMiBios port = service.getServicioMiBiosPort();
        return port.listaCursos();
    }
    
    
    
}
