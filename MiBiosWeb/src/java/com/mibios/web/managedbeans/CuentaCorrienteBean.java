/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.cuentaCorriente.CuentaCorrienteDatos;
import com.mibios.dto.cuentaCorriente.ParamCuentaCorriente;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.ejb.personas.PersonasBeanLocal;
import com.mibios.web.fachada.PersonasFachada;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
        PersonasFachada personasFachada = new PersonasFachada();
        ReturnLogin obj = (ReturnLogin)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        ParamCuentaCorriente xParamCuentaCorriente = new ParamCuentaCorriente();
        
        xParamCuentaCorriente.setTipoDocumento(obj.getTipoDocumento());
        xParamCuentaCorriente.setDocumento(obj.getDocumento());
        transacciones = personasFachada.obtenerCuentaCorriente(xParamCuentaCorriente);
    }
    
}
