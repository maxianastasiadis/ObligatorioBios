/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.jpa.entidades.Estudiantes;
import javax.persistence.EntityManager;

/**
 *
 * @author Maxi
 */
public class EstudiantesJpaPersitencia {
    
    public static Boolean existeEstudiante(EntityManager em, String xTipoDocumento, String xDocumento) throws Exception 
    {
        Boolean existeEstudiante = false;
        
        try
        {
            long existe = (Long)em.createNamedQuery("Estudiantes.existeEstudiante")
                    .setParameter("tipoDocumento", xTipoDocumento)
                    .setParameter("documento", xDocumento)
                    .getSingleResult();

            if(existe > 0)
            {
                existeEstudiante =  true;
            }
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return existeEstudiante;
    }
    
    public static Estudiantes ObtenerEstudiante(EntityManager em, String xTipoDocumento, String xDocumento) throws Exception 
    {
        Estudiantes objEstudiante = new Estudiantes();
        
        try
        {
            objEstudiante = em.createNamedQuery("Estudiantes.obtenerEstudiante",Estudiantes.class)
                    .setParameter("tipoDocumento", xTipoDocumento)
                    .setParameter("documento", xDocumento)
                    .getSingleResult();
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return objEstudiante;
    }
    
    public static int ObtenerCantidadEstudiantesActivosPorSexo(EntityManager em, String xSexo, String xFecha) throws Exception
    {
        int retorno = 0;
        try
        {
            long cantidad = (long)em.createNamedQuery("Estudiantes.cantidadPorSexo")
                    .setParameter("sexo", xSexo)                   
                    .setParameter("fecha", xFecha)
                    .getSingleResult();
            
            retorno = Integer.parseInt(String.valueOf(cantidad));
            
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return retorno;
    }
}
