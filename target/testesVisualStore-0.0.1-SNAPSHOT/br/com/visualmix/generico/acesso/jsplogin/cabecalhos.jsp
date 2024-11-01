<%@ page language="java" %>
<%@ page import="br.com.maxicode.util.Funcoes"%>

<%@page import="br.com.visualmix.generico.conexao.Application"%><html>
	<head>
  		<style type="">

		   body{background:#FFFFFF}
		
		   H1 {font-family:Arial;
		       font-size:20pt;
		       color:Black;
		       text-align:center} 
		
		   table {font-family:Arial,sans-serif;
		       height:60px;
		       font-size:13pt;
		       color:#000000;
			   font-weight:bold;
		       text-align:middle} 
	
		</style>
   		<meta http-equiv="pragma" content="no-cache">
   		<meta http-equiv="expires" content="0">
 		<title>Visual Store</title>
 	</head>
	<%
		 String ambiente = Funcoes.getParametro("GERAL.TIPOAMBIENTE","");
	%>
 	<body  style='border:0px;background-color:#32597A;background-image: url(<%= Application.getInstance().getContextPath() %>/resources/images/header.png); background-repeat: no-repeat;' marginheight="0" marginwidth="0" leftmargin="0" bottommargin="0" rightmargin="0" topmargin="0" >
		<table summary="" width="100%" height="100%" border="0" align="center">
			<tr>
		  		<td colspan="2" 
		  			height="70px"
		  			style="font-family: sans-serif; color:#32597A;text-align: center;">
		  			&nbsp;<span id="lblEnvironment" style="font-weight: bold;"></span> 
		  			<span style="color: #FFFFFF;"> <%= !ambiente.isEmpty() ? ambiente : ""%></span></td>
		  	</tr> 	
		</table>
	</body>
	<script>
		<%
		if(!ambiente.isEmpty()){
		%>
			document.getElementById("lblEnvironment").textContent = parent.cabecalhoEnvironmentTranslate.textContent + ":";
		<%
		}
		%>
	</script>	
</html>