/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.cuentaCorriente.CuentaCorrienteDatos;
import com.mibios.dto.cuentaCorriente.ParamCuentaCorriente;
import com.mibios.dto.cuentaCorriente.ReturnCuentaCorriente;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.funciones.FuncionesFecha;
import com.mibios.web.fachada.PersonasFachada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Maxi
 */
@ManagedBean
@RequestScoped
public class CuentaCorrienteBean implements Serializable{

    private List<CuentaCorrienteDatos> transacciones;
    
    public CuentaCorrienteBean() {
        try {
            transacciones = new ArrayList();
            PersonasFachada personasFachada = new PersonasFachada();
            ReturnLogin obj = (ReturnLogin)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
            
            ParamCuentaCorriente xParamCuentaCorriente = new ParamCuentaCorriente();
            xParamCuentaCorriente.setTipoDocumento(obj.getTipoDocumento());
            xParamCuentaCorriente.setDocumento(obj.getDocumento());
            
            List<ReturnCuentaCorriente> colReturnCuentaCorriente = personasFachada.obtenerCuentaCorriente(xParamCuentaCorriente);
            for(ReturnCuentaCorriente cuentaCorriente : colReturnCuentaCorriente)
            {
                CuentaCorrienteDatos ctaCte = new CuentaCorrienteDatos();
                
                ctaCte.setConcepto(cuentaCorriente.getConcepto());
                ctaCte.setFecha(FuncionesFecha.mostrarFechaDDMMAAAAString(cuentaCorriente.getFecha()));
                ctaCte.setHora(FuncionesFecha.mostrarHoraHHMMSS(cuentaCorriente.getHora()));
                ctaCte.setDebe(cuentaCorriente.getDebe());
                ctaCte.setHaber(cuentaCorriente.getHaber());
                ctaCte.setSaldo(cuentaCorriente.getSaldo());
                
                transacciones.add(ctaCte);
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(CuentaCorrienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CuentaCorrienteDatos> getTransacciones() {
        return transacciones;
    }
}
