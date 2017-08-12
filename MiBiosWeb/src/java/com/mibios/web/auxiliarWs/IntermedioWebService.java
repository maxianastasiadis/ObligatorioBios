/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.auxiliarWs;

import com.mibios.webservice.servicio.ReturnAgregarPersona;
import com.mibios.webservice.servicio.ReturnCantidadAlumnosSexo;
import com.mibios.webservice.servicio.ReturnClases;
import com.mibios.webservice.servicio.ReturnListaCursos;

/**
 *
 * @author usuario
 */
public class IntermedioWebService {
    
    public static ReturnAgregarPersona agregarPersona(com.mibios.webservice.servicio.ParamAgregarPersona xParamAgregarPersona) {        
        com.mibios.webservice.servicio.ServicioMiBios port = HelpWebService.obtenerServicio().getServicioMiBiosPort();
        return port.agregarPersona(xParamAgregarPersona);
    }

    public static ReturnCantidadAlumnosSexo cantidadAlumnosPorSexo() {
        com.mibios.webservice.servicio.ServicioMiBios port = HelpWebService.obtenerServicio().getServicioMiBiosPort();
        return port.cantidadAlumnosPorSexo();
    }

    public static ReturnClases clasesDelDia(com.mibios.webservice.servicio.ParamClasesEnDiaParaPersona xParamClasesEnDiaParaPersona) {
        com.mibios.webservice.servicio.ServicioMiBios port = HelpWebService.obtenerServicio().getServicioMiBiosPort();
        return port.clasesDelDia(xParamClasesEnDiaParaPersona);
    }

    public static ReturnListaCursos listaCursos() {
        com.mibios.webservice.servicio.ServicioMiBios port = HelpWebService.obtenerServicio().getServicioMiBiosPort();
        return port.listaCursos();
    }

    public static ReturnClases proximosComienzos() {
        com.mibios.webservice.servicio.ServicioMiBios port = HelpWebService.obtenerServicio().getServicioMiBiosPort();
        return port.proximosComienzos();
    }
    
}
