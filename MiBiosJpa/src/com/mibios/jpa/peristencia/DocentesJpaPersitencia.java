/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.jpa.entidades.Docentes;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Maxi
 */
public class DocentesJpaPersitencia {
    
    public static Boolean existeDocente(EntityManager em, String xTipoDocumento, String xDocumento) 
    {
        long existe = (Long)em.createNamedQuery("Docentes.existeDocente")
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
