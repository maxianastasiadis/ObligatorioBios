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
    
    public static Boolean existeEstudiante(EntityManager em, String xTipoDocumento, String xDocumento) 
    {        
        long existe = (Long)em.createNamedQuery("Estudiantes.existeEstudiante")
                .setParameter("tipoDocumento", xTipoDocumento)
                .setParameter("documento", xDocumento)
                .getSingleResult();
        
        if(existe > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
