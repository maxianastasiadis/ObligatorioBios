/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.usuarios;

import com.mibios.dto.usuarios.ParamActualizarDatosPersonales;
import com.mibios.dto.usuarios.ParamObtenerDatosPersonales;
import com.mibios.dto.usuarios.ReturnActualizarDatosPersonales;
import com.mibios.dto.usuarios.ReturnObtenerDatosPersonales;
import javax.ejb.Stateless;

/**
 *
 * @author Maxi
 */
@Stateless(mappedName="PersonasBean")
public class PersonasBean implements PersonasBeanLocal {

    @Override
    public ReturnActualizarDatosPersonales ActualizarDatosPersonales(ParamActualizarDatosPersonales xParamActualizarDatosPersonales) throws Exception {
        
        return null;
    }
    
    @Override
    public ReturnObtenerDatosPersonales ObtenerDatosPersonales(ParamObtenerDatosPersonales xParamObtenerDatosPersonales) throws Exception{
        
        return null;
    }
}
