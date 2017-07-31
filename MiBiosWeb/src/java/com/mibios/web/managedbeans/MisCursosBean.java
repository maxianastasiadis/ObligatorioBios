/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.web.managedbeans;

import com.mibios.dto.cursos.MisCursosDatos;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Maxi
 */
@ManagedBean
@RequestScoped
public class MisCursosBean {

    private List<MisCursosDatos> misCursos;
    
    public MisCursosBean() {
    }
    
}
