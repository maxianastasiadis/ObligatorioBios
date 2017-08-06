/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.jpa.entidades.Clases;
import com.mibios.jpa.entidades.Cursos;
import com.mibios.jpa.entidades.Estudiantes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Maxi
 */
public class CursosJpaPersistencia {
    
    public static List<Cursos> ObtenerCursos(EntityManager em) throws Exception 
    {
        List<Cursos> colCursos = new ArrayList<>();
        
        try
        {
            colCursos = em.createNamedQuery("Cursos.findAll",Cursos.class)
                    .getResultList();
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return colCursos;
    }
}
