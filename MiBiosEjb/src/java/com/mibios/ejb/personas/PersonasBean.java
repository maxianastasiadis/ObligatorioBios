/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.personas;

import com.mibios.dto.cuentaCorriente.ParamCuentaCorriente;
import com.mibios.dto.cuentaCorriente.ReturnCuentaCorriente;
import com.mibios.dto.personas.ParamActualizarDatosPersonales;
import com.mibios.dto.personas.ParamObtenerDatosPersonales;
import com.mibios.dto.personas.ReturnActualizarDatosPersonales;
import com.mibios.dto.personas.ReturnObtenerDatosPersonales;
import com.mibios.jpa.conexion.ConexionJpa;
import com.mibios.jpa.entidades.CuentaCorriente;
import com.mibios.jpa.entidades.PersonasPK;
import com.mibios.jpa.peristencia.PersonasJpaPersistencia;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
                
        ReturnActualizarDatosPersonales objReturnActualizarDatosPersonales = new ReturnActualizarDatosPersonales();
        EntityManager em = ConexionJpa.obtenerInstancia().obtenerConeccion();
        try
        {
            em.getTransaction().begin();

            //ACA TENGO QUE VER PRIMERO SI EXISTE LA PERSONA
            PersonasPK objPersonasPK = new PersonasPK(xParamActualizarDatosPersonales.getTipoDocumento(), xParamActualizarDatosPersonales.getDocumento());
            //SI EXISTE LA ACTUALIZO
            if(PersonasJpaPersistencia.existePersona(em, objPersonasPK))
            {
                objReturnActualizarDatosPersonales = PersonasJpaPersistencia.ActualizarDatosPersonales(em, xParamActualizarDatosPersonales);
            }
            //SINO MUESTRO MENSAJE DE ERROR QUE LA PERSONA NO EXISTE
            else
            {
                objReturnActualizarDatosPersonales.setGuardado(false);
                objReturnActualizarDatosPersonales.setRespuesta("No Existe Persona");
            }

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
        return objReturnActualizarDatosPersonales;
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
    
    @Override
    public List<ReturnCuentaCorriente> obtenerCuentaCorriente(ParamCuentaCorriente xParamCuentaCorriente) throws Exception{
        
        List<ReturnCuentaCorriente> colReturnCuentaCorriente = new ArrayList<>();
        List<CuentaCorriente> colCuentaCorriente = null;
        EntityManager em = ConexionJpa.obtenerInstancia().obtenerConeccion();
        try
        {
            em.getTransaction().begin();

            colCuentaCorriente = PersonasJpaPersistencia.obtenerCuentaCorriente(em, xParamCuentaCorriente);
            BigDecimal saldo = new BigDecimal(0);
            for(CuentaCorriente cuentaCorriente : colCuentaCorriente)
            {
                ReturnCuentaCorriente obj = new ReturnCuentaCorriente();
                
                obj.setConcepto(cuentaCorriente.getConcepto());
                obj.setFecha(cuentaCorriente.getFecha());
                obj.setHora(cuentaCorriente.getHora());
                if(cuentaCorriente.getTipoMovimiento().equalsIgnoreCase("D"))
                {
                    obj.setDebe(cuentaCorriente.getImporte());
                    saldo.add(cuentaCorriente.getImporte());
                }
                else
                {
                    obj.setHaber(cuentaCorriente.getImporte());
                    saldo.subtract(cuentaCorriente.getImporte());
                }
                obj.setSaldo(saldo);
                obj.setRespuesta("");
                
                colReturnCuentaCorriente.add(obj);
            }

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
        return colReturnCuentaCorriente;
    }
}
