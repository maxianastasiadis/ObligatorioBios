/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.jpa.entidades.Docentes;
import javax.persistence.EntityManager;

/**
 *
 * @author Maxi
 */
public class DocentesJpaPersitencia {
    
    public static Boolean existeDocente(EntityManager em, String xTipoDocumento, String xDocumento) throws Exception 
    {
        Boolean existeDocente = false;
        try
        {
            long existe = (Long)em.createNamedQuery("Docentes.existeDocente")
                    .setParameter("tipoDocumento", xTipoDocumento)
                    .setParameter("documento", xDocumento)
                    .getSingleResult();

            if(existe > 0)
            {
                existeDocente = true;
            }
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return existeDocente;
    }
    
    public static Docentes ObtenerDocente(EntityManager em, String xTipoDocumento, String xDocumento) throws Exception 
    {
        Docentes objDocente = new Docentes();
        
        try
        {
            objDocente = em.createNamedQuery("Docentes.obtenerDocente",Docentes.class)
                    .setParameter("tipoDocumento", xTipoDocumento)
                    .setParameter("documento", xDocumento)
                    .getSingleResult();
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return objDocente;
    }
    
}
