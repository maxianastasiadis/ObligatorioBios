/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.usuarios;

import com.mibios.dto.usuarios.ParamActualizarDatosPersonales;
import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.usuarios.ParamObtenerDatosPersonales;
import com.mibios.dto.usuarios.ReturnActualizarDatosPersonales;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.dto.usuarios.ReturnObtenerDatosPersonales;
import com.mibios.jpa.peristencia.UsuariosJpaPersitencia;
import javax.ejb.Stateless;

/**
 *
 * @author Maxi
 */
@Stateless(mappedName="UsuariosBean")
public class UsuariosBean implements UsuariosBeanLocal {

    @Override
    public ReturnLogin Login(ParamLogin xParamLogin) {
        return UsuariosJpaPersitencia.Login(xParamLogin);
    }
   
}
