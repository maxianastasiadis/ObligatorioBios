/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.jpa.peristencia;

import com.mibios.dto.cuentaCorriente.ParamCuentaCorriente;
import com.mibios.jpa.entidades.CuentaCorriente;
import com.mibios.jpa.entidades.CuentaCorrientePK;
import com.mibios.jpa.entidades.Personas;
import com.mibios.jpa.entidades.PersonasPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Maxi
 */
public class PersonasJpaPersistencia {
    
    public static Boolean ExistePersona(EntityManager em, PersonasPK objPersonasPK) throws Exception 
    {
        Boolean existePersona = false;
        
        try
        {
            Personas objPersonas = em.find(Personas.class, objPersonasPK);

            if(objPersonas!=null)
            {
                existePersona = true;
            }
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return existePersona;
    }
    
    public static Personas ObtenerPersona(EntityManager em, PersonasPK objPersonaPK) throws Exception 
    {   
        Personas objPersona = new Personas();
    
        try
        {
            objPersona = em.find(Personas.class, objPersonaPK);
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        } 
        
        return objPersona;
    }
    
    public static Personas ModificarPersona(EntityManager em, Personas objPersonas) throws Exception 
    {   
        Personas retorno = new Personas();
        try
        {
            retorno = em.merge(objPersonas);
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return retorno;
    }
    
    public static List<CuentaCorriente> ObtenerCuentaCorriente(EntityManager em, ParamCuentaCorriente xParamCuentaCorriente) throws Exception
    {
        List<CuentaCorriente> colCuentaCorriente = new ArrayList<>();
        try
        {
            colCuentaCorriente = em.createNamedQuery("CuentaCorriente.obtenerTransacciones",CuentaCorriente.class)
                    .setParameter("tipoDocumento", xParamCuentaCorriente.getTipoDocumento())
                    .setParameter("documento", xParamCuentaCorriente.getDocumento())
                    .getResultList();
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return colCuentaCorriente; 
    }
    
    public static CuentaCorriente ObtenerPago(EntityManager em, CuentaCorrientePK objCunCorrientePK) throws Exception
    {
        CuentaCorriente colCuentaCorriente = new CuentaCorriente();
        try
        {
            colCuentaCorriente = em.createNamedQuery("CuentaCorriente.obtenerTransaccion",CuentaCorriente.class)
                    .setParameter("fecha", objCunCorrientePK.getFecha())
                    .setParameter("hora", objCunCorrientePK.getHora())
                    .getSingleResult();
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
        return colCuentaCorriente; 
    }
    
    public static void IngresarPago(EntityManager em, CuentaCorriente objCuentaCorriente) throws Exception 
    {   
        try
        {
            em.persist(objCuentaCorriente);
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
    }
    
    public static void ModificarPago(EntityManager em, CuentaCorriente objCuentaCorriente) throws Exception 
    {   
        try
        {
            em.merge(objCuentaCorriente);
        }
        catch(Exception e)
        {
            throw new Exception("Persistencia--> " + e);
        }
    }
}
