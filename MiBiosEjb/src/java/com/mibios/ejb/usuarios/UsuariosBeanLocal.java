/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.usuarios;

import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.usuarios.ReturnLogin;
import javax.ejb.Remote;

/**
 *
 * @author Maxi
 */
@Remote
public interface UsuariosBeanLocal {
    public ReturnLogin Login(ParamLogin xParamLogin);
}
