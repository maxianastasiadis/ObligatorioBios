/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.cuentaCorriente.CuentaCorrienteDatos;
import com.mibios.dto.personas.ParamIngresarPago;
import com.mibios.dto.personas.ParamModificarPago;
import com.mibios.dto.personas.ReturnIngresarPago;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.funciones.FuncionesFecha;
import com.mibios.web.fachada.PersonasFachada;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
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
            
            objReturnIngresarPago = personasFachada.IngresarPago(objParamIngresarPago);

            if(objReturnIngresarPago.getGuardado()){
                //mensaje Se ingreso el pago correctamente
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(objReturnIngresarPago.getRespuesta()));
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
    
    public void onRowEdit(RowEditEvent event) throws Exception {
        ReturnIngresarPago objReturnIngresarPago = new ReturnIngresarPago();
        PersonasFachada personasFachada = new PersonasFachada();
        CuentaCorrienteDatos objCorrienteDatos = (CuentaCorrienteDatos) event.getObject();
        ParamModificarPago objParamModificarPago = new ParamModificarPago();
        objParamModificarPago.setFecha(FuncionesFecha.mostrarFechaAAAAMMDDString(objCorrienteDatos.getFecha()));
        objParamModificarPago.setHora(objCorrienteDatos.getHora().replace(":", ""));
        objParamModificarPago.setConcepto(objCorrienteDatos.getConcepto());
        
        if(objCorrienteDatos.getDebe() != null && objCorrienteDatos.getDebe().doubleValue() > 0)
        {
            objParamModificarPago.setImporte(objCorrienteDatos.getDebe());
            objParamModificarPago.setTipoMovimiento("D");
        }
        else
        {
            objParamModificarPago.setImporte(objCorrienteDatos.getHaber());
            objParamModificarPago.setTipoMovimiento("H");
        }
        objReturnIngresarPago = personasFachada.ModificarPago(objParamModificarPago);
        
        if(objReturnIngresarPago.getGuardado()){
            FacesMessage msg = new FacesMessage("Pago editado correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(objReturnIngresarPago.getRespuesta()));
        }
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Editado cancelado", ((CuentaCorrienteDatos) event.getObject()).getFecha() + " " + ((CuentaCorrienteDatos) event.getObject()).getHora());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
