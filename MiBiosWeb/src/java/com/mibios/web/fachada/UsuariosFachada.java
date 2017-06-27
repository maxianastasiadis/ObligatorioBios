/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.fachada;

import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.ejb.usuarios.UsuariosEjbLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author Maxi
 */
public class UsuariosFachada {
    
    public ReturnLogin Login(ParamLogin xParamLogin) throws Exception
    {
        return lookupUsuariosEjb().Login(xParamLogin);
    }
    
    /**********************************/
    /*ACA ESTAN LAS LLAMADAS A LOS EJB*/
    /**********************************/
    
    //ABMTCambioBean 
    private UsuariosEjbLocal lookupUsuariosEjb()
    {
        try
        {
            Context c = new InitialContext();
            return (UsuariosEjbLocal) c.lookup("UsuariosEjb");
        }
        catch (NamingException ne)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }  
}
