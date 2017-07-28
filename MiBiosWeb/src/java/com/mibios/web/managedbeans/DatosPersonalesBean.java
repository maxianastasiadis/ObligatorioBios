/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.personas.ParamActualizarDatosPersonales;
import com.mibios.dto.personas.ParamObtenerDatosPersonales;
import com.mibios.dto.personas.ReturnActualizarDatosPersonales;
import com.mibios.dto.usuarios.ReturnLogin;
import com.mibios.dto.personas.ReturnObtenerDatosPersonales;
import com.mibios.funciones.FuncionesFecha;
import com.mibios.web.fachada.PersonasFachada;
import com.mibios.web.fachada.UsuariosFachada;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Maxi
 */
@ManagedBean
@RequestScoped
public class DatosPersonalesBean implements Serializable  {

    private String tipoPersona;
    private String tipoDocumento;
    private String documento; 
    private String apellido1; 
    private String apellido2; 
    private String nombre1; 
    private String nombre2; 
    private Date fechaNacimiento; 
    private String sexo; 
    private String mail;
    private String telefono;
    private String celular;
    private String direccion;
    private String ciudad;
    private String departamento;
    private String pais;
    private String fechaIngreso;
    private String activo;
    
    public DatosPersonalesBean() {
        cargarDatosPersonales();
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
    
    public Boolean ActualizarDatosPersonales() {
        ParamActualizarDatosPersonales paramActualizarDatosPersonales = new ParamActualizarDatosPersonales();
        ReturnActualizarDatosPersonales returnActualizarDatosPersonales = new ReturnActualizarDatosPersonales();
        PersonasFachada personasFachada = new PersonasFachada();
        
        try {
    
            paramActualizarDatosPersonales.setTipoPersona(tipoPersona);
            paramActualizarDatosPersonales.setTipoDocumento(tipoDocumento);
            paramActualizarDatosPersonales.setDocumento(documento);
            
            paramActualizarDatosPersonales.setApellido1(apellido1);
            paramActualizarDatosPersonales.setApellido2(apellido2);
            paramActualizarDatosPersonales.setNombre1(nombre1); 
            paramActualizarDatosPersonales.setNombre2(nombre2);   
            paramActualizarDatosPersonales.setFechaNacimiento(fechaNacimiento); 
            paramActualizarDatosPersonales.setSexo(sexo); 
            paramActualizarDatosPersonales.setMail(mail);
            paramActualizarDatosPersonales.setTelefono(telefono);
            paramActualizarDatosPersonales.setCelular(celular);
            paramActualizarDatosPersonales.setDireccion(direccion);
            paramActualizarDatosPersonales.setCiudad(ciudad);
            paramActualizarDatosPersonales.setDepartamento(departamento);
            paramActualizarDatosPersonales.setPais(pais);
            paramActualizarDatosPersonales.setFechaIngreso(fechaIngreso);
            paramActualizarDatosPersonales.setActivo(activo);

            returnActualizarDatosPersonales = personasFachada.ActualizarDatosPersonales(paramActualizarDatosPersonales);
            if(returnActualizarDatosPersonales.getGuardado())
            {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("Usuario");
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", returnActualizarDatosPersonales.getDatosUsuario());
            }
            else
            {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(returnActualizarDatosPersonales.getRespuesta()));
            }
        } catch (Exception ex) {
            Logger.getLogger(DatosPersonalesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnActualizarDatosPersonales.getGuardado();
    }
    
    private void cargarDatosPersonales() {
        try {
    
            ReturnLogin objReturnSesion = (ReturnLogin)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
            PersonasFachada personasFachada = new PersonasFachada();
            ParamObtenerDatosPersonales objParamObtenerDatosPersonales = new ParamObtenerDatosPersonales();
            objParamObtenerDatosPersonales.setTipoDocumento(objReturnSesion.getTipoDocumento());
            objParamObtenerDatosPersonales.setDocumento(objReturnSesion.getDocumento());

            ReturnObtenerDatosPersonales objReturnObtenerDatosPersonales = personasFachada.ObtenerDatosPersonales(objParamObtenerDatosPersonales);

            tipoPersona = objReturnSesion.getTipoPersona();
            tipoDocumento = objReturnSesion.getTipoDocumento();
            documento = objReturnSesion.getDocumento(); 
            apellido1 = objReturnObtenerDatosPersonales.getApellido1(); 
            apellido2 = objReturnObtenerDatosPersonales.getApellido2(); 
            nombre1 = objReturnObtenerDatosPersonales.getNombre1(); 
            nombre2 = objReturnObtenerDatosPersonales.getNombre2(); 
            fechaNacimiento = FuncionesFecha.mostrarFechaDDMMAAAA(objReturnObtenerDatosPersonales.getFechaNacimiento());
            sexo = objReturnObtenerDatosPersonales.getSexo(); 
            mail = objReturnObtenerDatosPersonales.getMail();
            telefono = objReturnObtenerDatosPersonales.getTelefono();
            celular = objReturnObtenerDatosPersonales.getCelular();
            direccion = objReturnObtenerDatosPersonales.getDireccion();
            ciudad = objReturnObtenerDatosPersonales.getCiudad();
            departamento = objReturnObtenerDatosPersonales.getDepartamento();
            pais = objReturnObtenerDatosPersonales.getPais();

        } catch (Exception ex) {
            Logger.getLogger(DatosPersonalesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
