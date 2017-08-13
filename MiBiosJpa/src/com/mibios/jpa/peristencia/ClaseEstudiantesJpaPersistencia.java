/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.jpa.entidades.ClaseEstudiantes;
import com.mibios.jpa.entidades.ClaseEstudiantesPK;
import javax.persistence.EntityManager;

/**
 *
 * @author General
 */
public class ClaseEstudiantesJpaPersistencia {
    
    public static void InscribirmeACurso(EntityManager em, ClaseEstudiantes objClaseEstudiantes) throws Exception 
    {   
        try
        {
            em.persist(objClaseEstudiantes);
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
    }
    
    public static Boolean existeClaseEstudiante(EntityManager em, ClaseEstudiantesPK objClaseEstudiantesPK) throws Exception 
    {
        Boolean existeClaseEstudiante = false;
        try
        {
            ClaseEstudiantes objClaseEstudiantes = em.find(ClaseEstudiantes.class, objClaseEstudiantesPK);

            if(objClaseEstudiantes!=null)
            {
                existeClaseEstudiante = true;
            }
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return existeClaseEstudiante;
    }
    
}
