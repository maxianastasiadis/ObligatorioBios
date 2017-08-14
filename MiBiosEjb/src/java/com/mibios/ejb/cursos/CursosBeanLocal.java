/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.ejb.cursos;

import com.mibios.dto.cursos.ParamInscribirmeACurso;
import com.mibios.dto.cursos.ParamMisCursos;
import com.mibios.dto.cursos.ReturnCursos;
import com.mibios.dto.cursos.ReturnInscribirmeACurso;
import com.mibios.dto.cursos.ReturnMisCursos;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Maxi
 */
@Remote
public interface CursosBeanLocal {
 
    public List<ReturnMisCursos> ObtenerMisCursos(ParamMisCursos xParamMisCursos) throws Exception;
    
    public List<ReturnMisCursos> ObtenerMisCursosDocente(ParamMisCursos xParamMisCursos) throws Exception;
    
    public List<ReturnCursos> ObtenerCursos() throws Exception;
    
    public ReturnInscribirmeACurso InscribirmeACurso(ParamInscribirmeACurso xParamInscribirmeACurso) throws Exception;
}
