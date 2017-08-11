/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.fachada;

import com.mibios.dto.cuentaCorriente.ParamCuentaCorriente;
import com.mibios.dto.cuentaCorriente.ReturnCuentaCorriente;
import com.mibios.dto.personas.ParamActualizarDatosPersonales;
import com.mibios.dto.personas.ParamIngresarPago;
import com.mibios.dto.personas.ParamModificarPago;
import com.mibios.dto.personas.ParamObtenerDatosPersonales;
import com.mibios.dto.personas.ReturnActualizarDatosPersonales;
import com.mibios.dto.personas.ReturnIngresarPago;
import com.mibios.dto.personas.ReturnObtenerDatosPersonales;
import com.mibios.ejb.personas.PersonasBeanLocal;
import com.mibios.webservice.servicio.ParamAgregarPersona;
import com.mibios.webservice.servicio.ReturnAgregarPersona;
import com.mibios.webservice.servicio.ServicioMiBios;
import com.mibios.webservice.servicio.ServicioMiBios_Service;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Maxi
 */
public class PersonasFachada {
 
    public ReturnActualizarDatosPersonales ActualizarDatosPersonales(ParamActualizarDatosPersonales xParamActualizarDatosPersonales) throws Exception
    {
        return lookupPersonasBean().ActualizarDatosPersonales(xParamActualizarDatosPersonales);
    }
    
    public ReturnObtenerDatosPersonales ObtenerDatosPersonales(ParamObtenerDatosPersonales xParamObtenerDatosPersonales) throws Exception
    {
        return lookupPersonasBean().ObtenerDatosPersonales(xParamObtenerDatosPersonales);
    }
    
    public List<ReturnCuentaCorriente> ObtenerCuentaCorriente(ParamCuentaCorriente xParamCuentaCorriente) throws Exception
    {
        return lookupPersonasBean().ObtenerCuentaCorriente(xParamCuentaCorriente);
    }
    
    public ReturnIngresarPago IngresarPago(ParamIngresarPago xParamIngresarPago) throws Exception
    {
        return lookupPersonasBean().IngresarPago(xParamIngresarPago);
    }
    
    public ReturnIngresarPago ModificarPago(ParamModificarPago xParamModificarPago) throws Exception
    {
        return lookupPersonasBean().ModificarPago(xParamModificarPago);
    }
    
    public ReturnAgregarPersona AgregarPersonaViaWS(ParamAgregarPersona xParamAgregarPersona)
    {
        return this.agregarPersona(xParamAgregarPersona);
    }
        
    /**********************************/
    /*ACA ESTAN LAS LLAMADAS A LOS EJB*/
    /**********************************/
    
    //PersonasBean 
    private PersonasBeanLocal lookupPersonasBean()
    {
        try
        {
            Context c = new InitialContext();
            return (PersonasBeanLocal) c.lookup("PersonasBean");
        }
        catch (NamingException ne)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    } 
    
    private ServicioMiBios_Service obtenerServicio()
    {
        try
        {
            return new ServicioMiBios_Service(new URL("http://localhost:8080/MiBiosWebService/ServicioMiBios?wsdl"));
        }
        catch(Exception ex)
        {
            return null;
        }
        
    }
    
    private ReturnAgregarPersona agregarPersona(com.mibios.webservice.servicio.ParamAgregarPersona xParamAgregarPersona) {        
        com.mibios.webservice.servicio.ServicioMiBios port = obtenerServicio().getServicioMiBiosPort();
        return port.agregarPersona(xParamAgregarPersona);
    }
}
