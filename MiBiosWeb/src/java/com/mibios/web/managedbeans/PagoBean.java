/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.personas.ParamIngresarPago;
import com.mibios.dto.personas.ParamObtenerDatosPersonales;
import com.mibios.dto.personas.ReturnIngresarPago;
import com.mibios.dto.personas.ReturnObtenerDatosPersonales;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.web.fachada.PersonasFachada;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author General
 */
@ManagedBean
@RequestScoped
public class PagoBean implements Serializable {
    
    private String concepto;
    private String fecha;
    private String hora;
    private String tipoMovimiento;
    private BigDecimal importe;

    /**
     * Creates a new instance of PagoBean
     */
    public PagoBean() {
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
    
    public Boolean IngresarPago() {
        ParamIngresarPago objParamIngresarPago = new ParamIngresarPago();
        ReturnIngresarPago objReturnIngresarPago = new ReturnIngresarPago();
        PersonasFachada personasFachada = new PersonasFachada();
        try {
    
            ReturnLogin objReturnSesion = (ReturnLogin)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
            objParamIngresarPago.setTipoDocumento(objReturnSesion.getTipoDocumento());
            objParamIngresarPago.setDocumento(objReturnSesion.getDocumento());
            objParamIngresarPago.setConcepto(concepto);
            objParamIngresarPago.setTipoMovimiento(tipoMovimiento);
            objParamIngresarPago.setImporte(importe);
            System.out.println("concepto = " + concepto);
            System.out.println("tipoMovimiento = " + tipoMovimiento);
            System.out.println("importe = " + importe);
            objReturnIngresarPago = personasFachada.IngresarPago(objParamIngresarPago);

            if(objReturnIngresarPago.getGuardado()){
                //mensaje Se ingreso el pago correctamente
            }
            else
            {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(objReturnIngresarPago.getRespuesta()));
            }

        } 
        catch (Exception ex) 
        {
            Logger.getLogger(DatosPersonalesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objReturnIngresarPago.getGuardado();
    }
    
}
