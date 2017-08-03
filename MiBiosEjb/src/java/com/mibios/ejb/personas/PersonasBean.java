/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.personas;

import com.mibios.dto.cuentaCorriente.ParamCuentaCorriente;
import com.mibios.dto.cuentaCorriente.ReturnCuentaCorriente;
import com.mibios.dto.personas.ParamActualizarDatosPersonales;
import com.mibios.dto.personas.ParamIngresarPago;
import com.mibios.dto.personas.ParamObtenerDatosPersonales;
import com.mibios.dto.personas.ReturnActualizarDatosPersonales;
import com.mibios.dto.personas.ReturnIngresarPago;
import com.mibios.dto.personas.ReturnObtenerDatosPersonales;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.funciones.FuncionesFecha;
import com.mibios.jpa.entidades.CuentaCorriente;
import com.mibios.jpa.entidades.Personas;
import com.mibios.jpa.entidades.PersonasPK;
import com.mibios.jpa.peristencia.PersonasJpaPersistencia;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maxi
 */
@Stateless(mappedName="PersonasBean")
public class PersonasBean implements PersonasBeanLocal {

    @PersistenceContext(unitName = "MiBiosJpaPU")
    private EntityManager em;
    
    @Override
    public ReturnActualizarDatosPersonales ActualizarDatosPersonales(ParamActualizarDatosPersonales xParamActualizarDatosPersonales) throws Exception 
    {            
        ReturnActualizarDatosPersonales objReturnActualizarDatosPersonales = new ReturnActualizarDatosPersonales();
        try
        {
            //ACA TENGO QUE VER PRIMERO SI EXISTE LA PERSONA
            PersonasPK objPersonasPK = new PersonasPK(xParamActualizarDatosPersonales.getTipoDocumento(), xParamActualizarDatosPersonales.getDocumento());
            //SI EXISTE LA ACTUALIZO
            if(PersonasJpaPersistencia.ExistePersona(em, objPersonasPK))
            {
                Personas objPersonas = new Personas();

                objPersonas.setActivo(xParamActualizarDatosPersonales.getActivo());
                objPersonas.setApellido1(xParamActualizarDatosPersonales.getApellido1());
                objPersonas.setApellido2(xParamActualizarDatosPersonales.getApellido2());
                objPersonas.setCelular(xParamActualizarDatosPersonales.getCelular());
                objPersonas.setCiudad(xParamActualizarDatosPersonales.getCiudad());
                objPersonas.setDepartamento(xParamActualizarDatosPersonales.getDepartamento());
                objPersonas.setDireccion(xParamActualizarDatosPersonales.getDireccion());
                objPersonas.setFechaIngreso(xParamActualizarDatosPersonales.getFechaIngreso());
                objPersonas.setFechaNacimiento(FuncionesFecha.guardarFechaAAAAMMDD(xParamActualizarDatosPersonales.getFechaNacimiento()));
                objPersonas.setMail(xParamActualizarDatosPersonales.getMail());
                objPersonas.setNombre1(xParamActualizarDatosPersonales.getNombre1());
                objPersonas.setNombre2(xParamActualizarDatosPersonales.getNombre2());
                objPersonas.setPais(xParamActualizarDatosPersonales.getPais());
                objPersonas.setPersonasPK(objPersonasPK);
                objPersonas.setSexo(xParamActualizarDatosPersonales.getSexo());
                objPersonas.setTelefono(xParamActualizarDatosPersonales.getTelefono());
                
                objPersonas = PersonasJpaPersistencia.ModificarPersona(em, objPersonas);
                
                Boolean guardadoOk = false;
                String respuesta = "No se han podido guardar los datos";
                ReturnLogin datosUsuario = new ReturnLogin();
        
                if(objPersonas.getPersonasPK().getTipoDocumento().equalsIgnoreCase(xParamActualizarDatosPersonales.getTipoDocumento()) && 
                   objPersonas.getPersonasPK().getDocumento().equalsIgnoreCase(xParamActualizarDatosPersonales.getDocumento()))
                {
                    guardadoOk = true;
                    datosUsuario.setTipoPersona(xParamActualizarDatosPersonales.getTipoPersona());
                    datosUsuario.setTipoDocumento(xParamActualizarDatosPersonales.getTipoDocumento());
                    datosUsuario.setDocumento(xParamActualizarDatosPersonales.getDocumento());
                    datosUsuario.setNombreUsuario(objPersonas.getNombre1()+ " " + objPersonas.getApellido1());
                    respuesta = "";
                }

                objReturnActualizarDatosPersonales.setGuardado(guardadoOk);
                objReturnActualizarDatosPersonales.setRespuesta(respuesta);
                objReturnActualizarDatosPersonales.setDatosUsuario(datosUsuario);
            }
            //SINO MUESTRO MENSAJE DE ERROR QUE LA PERSONA NO EXISTE
            else
            {
                objReturnActualizarDatosPersonales.setGuardado(false);
                objReturnActualizarDatosPersonales.setRespuesta("No Existe Persona");
            }
        }
        catch(Exception e)
        {
            Logger.getLogger(PersonasBean.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Beans--> " + e.getMessage() + " [" + this.getClass().getSimpleName() + "]");
        }
        finally{
        }
        return objReturnActualizarDatosPersonales;
    }
    
    @Override
    public ReturnObtenerDatosPersonales ObtenerDatosPersonales(ParamObtenerDatosPersonales xParamObtenerDatosPersonales) throws Exception{
        
        ReturnObtenerDatosPersonales objReturnObtenerDatosPersonales = new ReturnObtenerDatosPersonales();
        try
        {
            PersonasPK objPersonasPK = new PersonasPK(xParamObtenerDatosPersonales.getTipoDocumento(), xParamObtenerDatosPersonales.getDocumento());
            Personas objPersonas = PersonasJpaPersistencia.ObtenerPersona(em, objPersonasPK);
            
            objReturnObtenerDatosPersonales.setTipoDocumento(objPersonas.getPersonasPK().getTipoDocumento());
            objReturnObtenerDatosPersonales.setDocumento(objPersonas.getPersonasPK().getDocumento());
            objReturnObtenerDatosPersonales.setApellido1(objPersonas.getApellido1());
            objReturnObtenerDatosPersonales.setApellido2(objPersonas.getApellido2());
            objReturnObtenerDatosPersonales.setNombre1(objPersonas.getNombre1());
            objReturnObtenerDatosPersonales.setNombre2(objPersonas.getNombre2());
            objReturnObtenerDatosPersonales.setFechaNacimiento(objPersonas.getFechaNacimiento());
            objReturnObtenerDatosPersonales.setSexo(objPersonas.getSexo());
            objReturnObtenerDatosPersonales.setCelular(objPersonas.getCelular());
            objReturnObtenerDatosPersonales.setTelefono(objPersonas.getTelefono());
            objReturnObtenerDatosPersonales.setMail(objPersonas.getMail());
            objReturnObtenerDatosPersonales.setPais(objPersonas.getPais());
            objReturnObtenerDatosPersonales.setDepartamento(objPersonas.getDepartamento());
            objReturnObtenerDatosPersonales.setCiudad(objPersonas.getCiudad());
            objReturnObtenerDatosPersonales.setDireccion(objPersonas.getDireccion());
        }
        catch(Exception e)
        {
            Logger.getLogger(PersonasBean.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Beans--> " + e.getMessage() + " [" + this.getClass().getSimpleName() + "]");
        }
        finally{
        }
        return objReturnObtenerDatosPersonales;
    }
    
    @Override
    public List<ReturnCuentaCorriente> ObtenerCuentaCorriente(ParamCuentaCorriente xParamCuentaCorriente) throws Exception{
        
        List<ReturnCuentaCorriente> colReturnCuentaCorriente = new ArrayList<>();
        List<CuentaCorriente> colCuentaCorriente = null;
        try
        {
            colCuentaCorriente = PersonasJpaPersistencia.ObtenerCuentaCorriente(em, xParamCuentaCorriente);
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
                    saldo = saldo.add(cuentaCorriente.getImporte());
                }
                else
                {
                    obj.setHaber(cuentaCorriente.getImporte());
                    saldo = saldo.subtract(cuentaCorriente.getImporte());
                }
                obj.setSaldo(saldo);
                obj.setRespuesta("");
                
                colReturnCuentaCorriente.add(obj);
            }
        }
        catch(Exception e)
        {
            Logger.getLogger(PersonasBean.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Beans--> " + e.getMessage() + " [" + this.getClass().getSimpleName() + "]");
        }
        finally{
        }
        return colReturnCuentaCorriente;
    }
    
    @Override
    public ReturnIngresarPago IngresarPago(ParamIngresarPago xParamIngresarPago) throws Exception{
        
        ReturnIngresarPago objReturnIngresarPago = new ReturnIngresarPago();
        try
        {
            PersonasPK objPersonasPK = new PersonasPK(xParamIngresarPago.getTipoDocumento(), xParamIngresarPago.getDocumento());
            Personas objPersonas = PersonasJpaPersistencia.ObtenerPersona(em, objPersonasPK);

            CuentaCorriente objCuentaCorriente = new CuentaCorriente();

            objCuentaCorriente.setPersonas(objPersonas);
            objCuentaCorriente.setConcepto(xParamIngresarPago.getConcepto());
            objCuentaCorriente.setTipoMovimiento(xParamIngresarPago.getTipoMovimiento());
            objCuentaCorriente.setFecha(FuncionesFecha.mostrarFechaAAAAMMDDString(FuncionesFecha.getFechaSistema()));
            objCuentaCorriente.setHora(FuncionesFecha.getHoraSistema().replace(":", ""));
            objCuentaCorriente.setImporte(xParamIngresarPago.getImporte());

            PersonasJpaPersistencia.IngresarPago(em, objCuentaCorriente);

            Boolean guardadoOk = true;
            ReturnLogin datosUsuario = new ReturnLogin();

            objReturnIngresarPago.setGuardado(guardadoOk);
            objReturnIngresarPago.setRespuesta("");
            objReturnIngresarPago.setDatosUsuario(datosUsuario);
        }
        catch(Exception e)
        {
            objReturnIngresarPago.setRespuesta("Error al ingresar el pago");
            Logger.getLogger(PersonasBean.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Beans--> " + e.getMessage() + " [" + this.getClass().getSimpleName() + "]");
        }
        finally{
        }
        return objReturnIngresarPago;
    }
}
