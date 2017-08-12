/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.funciones.FuncionesFecha;
import com.mibios.web.fachada.PersonasFachada;
import com.mibios.webservice.servicio.ParamAgregarPersona;
import com.mibios.webservice.servicio.ReturnAgregarPersona;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author usuario
 */
@ManagedBean
@RequestScoped
public class AgregarPersonaBean implements Serializable{

    private String tipoPersona;
    private String tipoDocumento;
    private String documento;
    private String nombre1;
    private String apellido1;
    private Date fechaNacimiento;

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public void AgregarPersona() {        
                
        try {
            
            ParamAgregarPersona objParamAgregarPersona = new ParamAgregarPersona();
            objParamAgregarPersona.setApellido1(apellido1);
            objParamAgregarPersona.setDocumento(documento);
            objParamAgregarPersona.setFechaNacimiento(FuncionesFecha.guardarFechaAAAAMMDD(fechaNacimiento));
            objParamAgregarPersona.setNombre1(nombre1);
            objParamAgregarPersona.setTipoDocumento(tipoDocumento);
            objParamAgregarPersona.setTipoPersona(tipoPersona);


            PersonasFachada fachada = new PersonasFachada();
            ReturnAgregarPersona objReturnAgregarPersona = fachada.AgregarPersonaViaWS(objParamAgregarPersona);    

            FacesMessage message;
            if(objReturnAgregarPersona.isAgregado()){
                limpiarControles();
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, objReturnAgregarPersona.getMensaje(), "");                 
            }
            else
            {
               message = new FacesMessage(FacesMessage.SEVERITY_ERROR, objReturnAgregarPersona.getMensaje(), "");                
            }
            
            FacesContext.getCurrentInstance().addMessage(null, message);

        } 
        catch (Exception ex) 
        {
            Logger.getLogger(DatosPersonalesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpiarControles()
    {
        this.tipoPersona = "";
        this.tipoDocumento = "";
        this.documento = "";
        this.nombre1 = "";
        this.apellido1 = "";
        this.fechaNacimiento = null;
    }

    
    
    
}
