/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mibios.dto.cuentaCorriente;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Maxi
 */
public class ParamCuentaCorriente implements Serializable{
    
    private String tipoDocumento;
    private String documento;

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

    
}
