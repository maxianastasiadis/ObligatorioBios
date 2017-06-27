<%-- 
    Document   : index
    Created on : 27/06/2017, 12:16:08 AM
    Author     : Maxi
--%>

<%@page import="com.mibios.dto.usuarios.ReturnLogin"%>
<%@page import="com.mibios.dto.usuarios.ParamLogin"%>
<%@page import="com.mibios.web.fachada.UsuariosFachada"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            UsuariosFachada fachada = new UsuariosFachada();
            ParamLogin xParamLogin = new ParamLogin();
            xParamLogin.setTipoPersona("A");
            xParamLogin.setTipoDocumento("1");
            xParamLogin.setDocumento("39342968");
            xParamLogin.setClave("1234");
            
            ReturnLogin ret = fachada.Login(xParamLogin);
            System.out.println("ret = " + ret.getNombreUsuario());
                   
        %>
    </body>
</html>
