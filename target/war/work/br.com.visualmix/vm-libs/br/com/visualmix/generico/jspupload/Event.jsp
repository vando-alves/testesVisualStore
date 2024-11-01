<%@ page language="java" %>
<%@ page import="br.com.visualmix.generico.conexao.Application"%>
<%
	String msg;
	
	try{
		UploadEngine.upload(request, request.getParameter("path"), request.getParameter("filename"),  request.getParameter("criapath"));
		msg = "Upload Concluido com Sucesso.";
	}catch(Exception e){
		msg = e.getMessage();
		e.printStackTrace();
	}
%>
<LINK REL='STYLESHEET' HREF='<%= Application.getInstance().getContextPath() %>/resources/css/estilo.css' TYPE='Text/css' />


<%@page import="br.com.visualmix.generico.jspupload.UploadEngine"%><html>
 	<body> 	
	 	<table border='0' cellpadding='1px' cellspacing='0' width=100%>
		 	<tr><br/><br/> <td align='center'  nowrap > <span name="label" id="label" style=text-align:center;cursor:default;><%=msg%></span><br/></td></tr>
		 	<tr> <td align='center'  nowrap > <span name="label" id="label" style=text-align:center;></span></br></td></tr>
		 	<tr> <td align='center'  nowrap > <input type="button" name="btnSair" Class="botao" value="Sair" onclick="parent.closeframe()" style=width:75;></br></td></tr>
	 	</table>
 	</body>
</html>