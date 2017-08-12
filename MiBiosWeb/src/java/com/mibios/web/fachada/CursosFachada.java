/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.fachada;

import com.mibios.dto.cursos.ParamInscribirmeACurso;
import com.mibios.dto.cursos.ParamMisCursos;
import com.mibios.dto.cursos.ReturnCursos;
import com.mibios.dto.cursos.ReturnInscribirmeACurso;
import com.mibios.dto.cursos.ReturnMisCursos;
import com.mibios.ejb.cursos.CursosBeanLocal;
import com.mibios.web.auxiliarWs.IntermedioWebService;
import com.mibios.webservice.servicio.ParamClasesEnDiaParaPersona;
import com.mibios.webservice.servicio.ReturnClases;
import com.mibios.webservice.servicio.ReturnListaCursos;
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
    
    public List<ReturnCursos> ObtenerCursos() throws Exception
    {
        return lookupCursosBean().ObtenerCursos();
    }
    
    public ReturnInscribirmeACurso InscribirmeACurso(ParamInscribirmeACurso paramInscribirmeACurso) throws Exception
    {
        return lookupCursosBean().InscribirmeACurso(paramInscribirmeACurso);
    }
    
    public ReturnListaCursos ListaCursos()
    {
        return IntermedioWebService.listaCursos();
    }
    
    public ReturnClases ClasesDelDia(ParamClasesEnDiaParaPersona xParamClasesEnDiaParaPersona)
    {
        return IntermedioWebService.clasesDelDia(xParamClasesEnDiaParaPersona);
    }
    
    public ReturnClases ProximosComienzos()
    {
        return IntermedioWebService.proximosComienzos();
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
