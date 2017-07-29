/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.personas;

import com.mibios.dto.cuentaCorriente.ParamCuentaCorriente;
import com.mibios.dto.cuentaCorriente.ReturnCuentaCorriente;
import com.mibios.dto.personas.ParamActualizarDatosPersonales;
import com.mibios.dto.personas.ParamObtenerDatosPersonales;
import com.mibios.dto.personas.ReturnActualizarDatosPersonales;
import com.mibios.dto.personas.ReturnObtenerDatosPersonales;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Maxi
 */
@Remote
public interface PersonasBeanLocal {
        
    public ReturnActualizarDatosPersonales ActualizarDatosPersonales(ParamActualizarDatosPersonales xParamActualizarDatosPersonales) throws Exception;
    
    public ReturnObtenerDatosPersonales ObtenerDatosPersonales(ParamObtenerDatosPersonales xParamObtenerDatosPersonales) throws Exception;
    
    public List<ReturnCuentaCorriente> ObtenerCuentaCorriente(ParamCuentaCorriente xParamCuentaCorriente) throws Exception;
    
}
