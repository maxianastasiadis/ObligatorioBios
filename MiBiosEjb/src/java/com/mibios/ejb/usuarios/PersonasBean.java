/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.usuarios;

import com.mibios.dto.usuarios.ParamActualizarDatosPersonales;
import com.mibios.dto.usuarios.ParamObtenerDatosPersonales;
import com.mibios.dto.usuarios.ReturnActualizarDatosPersonales;
import com.mibios.dto.usuarios.ReturnObtenerDatosPersonales;
import com.mibios.jpa.conexion.ConexionJpa;
import com.mibios.jpa.peristencia.PersonasJpaPersistencia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author Maxi
 */
@Stateless(mappedName="PersonasBean")
public class PersonasBean implements PersonasBeanLocal {

    @Override
    public ReturnActualizarDatosPersonales ActualizarDatosPersonales(ParamActualizarDatosPersonales xParamActualizarDatosPersonales) throws Exception {
        
        return null;
    }
    
    @Override
    public ReturnObtenerDatosPersonales ObtenerDatosPersonales(ParamObtenerDatosPersonales xParamObtenerDatosPersonales) throws Exception{
        
        ReturnObtenerDatosPersonales objReturnObtenerDatosPersonales = new ReturnObtenerDatosPersonales();
        EntityManager em = ConexionJpa.obtenerInstancia().obtenerConeccion();
        try
        {
            em.getTransaction().begin();

            objReturnObtenerDatosPersonales = PersonasJpaPersistencia.ObtenerDatosPersonales(em, xParamObtenerDatosPersonales);

            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            if(em.getTransaction()!=null && em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally{
            em.close();
        }
        return objReturnObtenerDatosPersonales;
    }
}
