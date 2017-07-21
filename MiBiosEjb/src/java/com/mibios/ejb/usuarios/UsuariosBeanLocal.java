/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.usuarios;

import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.usuarios.ParamRecuperarContrasena;
import com.mibios.dto.usuarios.ParamRegistro;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.dto.usuarios.ReturnRecuperarContrasena;
import com.mibios.dto.usuarios.ReturnRegistro;
import javax.ejb.Remote;

/**
 *
 * @author Maxi
 */
@Remote
public interface UsuariosBeanLocal {
    
    public ReturnLogin Login(ParamLogin xParamLogin);
    
    public ReturnRegistro Registro(ParamRegistro xParamRegistro);
    
    public ReturnRecuperarContrasena RecuperarContrasena(ParamRecuperarContrasena xParamRecuperarContrasena) throws Exception;
    
}
