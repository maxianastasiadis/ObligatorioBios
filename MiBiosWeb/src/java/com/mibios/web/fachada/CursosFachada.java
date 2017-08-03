/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.fachada;

import com.mibios.dto.cursos.ParamMisCursos;
import com.mibios.dto.cursos.ReturnMisCursos;
import com.mibios.ejb.cursos.CursosBeanLocal;
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
public class CursosFachada {
    
    public List<ReturnMisCursos> ObtenerMisCursos(ParamMisCursos xParamMisCursos) throws Exception
    {
        return lookupCursosBean().ObtenerMisCursos(xParamMisCursos);
    }
    
    /**********************************/
    /*ACA ESTAN LAS LLAMADAS A LOS EJB*/
    /**********************************/
    
    //CursosBean 
    private CursosBeanLocal lookupCursosBean()
    {
        try
        {
            Context c = new InitialContext();
            return (CursosBeanLocal) c.lookup("CursosBean");
        }
        catch (NamingException ne)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    } 
    
}
