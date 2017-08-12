/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.jpa.entidades.Clases;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author General
 */
public class ClasesJpaPersitencia {
    
    public static Clases ObtenerClase(EntityManager em, Integer xIdClase) throws Exception 
    {
        Clases objClases = new Clases();
        
        try
        {
            objClases = em.createNamedQuery("Clases.obtenerClase",Clases.class)
                    .setParameter("idClase", xIdClase)
                    .getSingleResult();
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return objClases;
    }
    
    public static List<Clases> ObtenerClasesPorFechaComienzo(EntityManager em, String xFechaDesde, String xFechaHasta) throws Exception
    {
        List<Clases> retorno = null;
        
        try
        {
            retorno = em.createNamedQuery("Clases.ObtenerPorFechaComienzo", Clases.class)
                    .setParameter("fechaDesde", xFechaDesde)
                    .setParameter("fechaHasta", xFechaHasta)
                    .getResultList();
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        
        return retorno;
    }
    
}
