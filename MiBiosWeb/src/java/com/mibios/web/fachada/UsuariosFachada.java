/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.fachada;

import com.mibios.dto.usuarios.ParamLogin;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.ejb.usuarios.UsuariosBeanLocal;
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
        return lookupUsuariosBean().Login(xParamLogin);
    }
    
    /**********************************/
    /*ACA ESTAN LAS LLAMADAS A LOS EJB*/
    /**********************************/
    
    //UsuariosBean 
    private UsuariosBeanLocal lookupUsuariosBean()
    {
        try
        {
            Context c = new InitialContext();
            return (UsuariosBeanLocal) c.lookup("UsuariosBean");
        }
        catch (NamingException ne)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }  
}
