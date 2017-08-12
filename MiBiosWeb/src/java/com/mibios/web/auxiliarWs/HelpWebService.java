/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.auxiliarWs;

import com.mibios.webservice.servicio.ServicioMiBios_Service;
import java.net.URL;
import javax.faces.context.FacesContext;

/**
 *
 * @author usuario
 */
public class HelpWebService {
    
    public static ServicioMiBios_Service obtenerServicio()
    {
        try
        {
            String rutaWsdl = FacesContext.getCurrentInstance().getExternalContext().getInitParameterMap().get("rutaWSDL").toString();
            return new ServicioMiBios_Service(new URL(rutaWsdl));
        }
        catch(Exception ex)
        {
            return null;
        }
        
    }
    
}
