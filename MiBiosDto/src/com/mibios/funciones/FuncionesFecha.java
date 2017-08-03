/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.funciones;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.print.attribute.Size2DSyntax.MM;

/**
 *
 * @author Maxi
 */
public class FuncionesFecha {
    
    /**
     * Da un formato para mostar al usuario.
     * @param xFecha
     * @return una fecha con un formato (dd/MM/yyyy)
     */
    public static Date mostrarFechaDDMMAAAA(String xFecha)
    {
        Date fechaFormateada = new Date();
        try {
            
            DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            String a = xFecha.substring(0, 4);
            String m = xFecha.substring(4, 6);
            String d = xFecha.substring(6, 8);
            String fecha = d+"/"+m+"/"+a;
            fechaFormateada = sourceFormat.parse(fecha);
            
        } catch (ParseException ex) {
            Logger.getLogger(FuncionesFecha.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return fechaFormateada;
    }
    
    /**
     * Da un formato para mostar al usuario.
     * @param xFecha
     * @return una fecha String con un formato (dd/MM/yyyy)
     */
    public static String mostrarFechaDDMMAAAAString(String xFecha)
    {
        String fecha = "";
        try {
            
            String a = xFecha.substring(0, 4);
            String m = xFecha.substring(4, 6);
            String d = xFecha.substring(6, 8);
            fecha = d+"/"+m+"/"+a;
            
        } catch (Exception ex) {
            Logger.getLogger(FuncionesFecha.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return fecha;
    }
    
    
    /**
     * Da un formato para mostar al usuario.
     * @param xFecha
     * @return una fecha con un formato (yyyyddmm)
     */
    public static String guardarFechaAAAAMMDD(Date xFecha)
    {
        String fechaFormateada = "";
        
        SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdFormat.format(xFecha).replace("/", "");
        String a = fecha.substring(4, 8);
        String m = fecha.substring(2, 4);
        String d = fecha.substring(0, 2);
        fechaFormateada = a+m+d; 
        
        return fechaFormateada;
    }
    
    /**
     * Da un formato para mostar al usuario.
     * @param xHora
     * @return una hora String con un formato (hh:mm:ss)
     */
    public static String mostrarHoraHHMMSS(String xHora)
    {
        String hora = "";
        try {
            
            String h = xHora.substring(0, 2);
            String m = xHora.substring(2, 4);
            String s = xHora.substring(4, 6);
            hora = h+":"+m+":"+s;
            
        } catch (Exception ex) {
            Logger.getLogger(FuncionesFecha.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return hora;
    }
    
    /**
     * Da un formato para guardar en la base.
     * @param xFecha
     * @return una fecha String con un formato (yyyyMMdd)
     */
    public static String mostrarFechaAAAAMMDDString(String xFecha)
    {
        String fecha = "";
        try {
            xFecha = xFecha.replace("/", "");
            String a = xFecha.substring(4, 8);
            String m = xFecha.substring(2, 4);
            String d = xFecha.substring(0, 2);
            fecha = a+m+d;
            
        } catch (Exception ex) {
            Logger.getLogger(FuncionesFecha.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return fecha;
    }
    
    /**
     * Fecha del sistema.
     * @param
     * @return fecha del sistema
     */
    public static String getFechaSistema()
    {
        Date dtFechaActual = new Date();
        DateFormat dfLocal = new SimpleDateFormat("dd/MM/yyyy");

        return dfLocal.format(dtFechaActual);
    }
    
    /**
     * Hora del sistema.
     * @param
     * @return hora del sistema
     */
    public static String getHoraSistema()
    {
        Date dtFechaActual = new Date();
        DateFormat dfLocal = new SimpleDateFormat("HH:mm:ss");

        return dfLocal.format(dtFechaActual);
    }
    
}
