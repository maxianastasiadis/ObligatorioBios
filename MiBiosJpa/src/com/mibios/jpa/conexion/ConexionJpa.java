/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maxi
 */
public class ConexionJpa {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    private static ConexionJpa instancia;
    
    private ConexionJpa()
    {
        //emf = Persistence.createEntityManagerFactory("MiBiosJpaPU");
    }
    
    public static ConexionJpa obtenerInstancia()
    {
        if(instancia==null)
        {
            instancia = new ConexionJpa();
        }
        return instancia;
    }
    
    public EntityManager obtenerConeccion()
    {
        //EntityManager em;// = emf.createEntityManager();   
        return em;
    }
    
    public void cerrarConeccion()
    {
        emf.close();
    }
}
