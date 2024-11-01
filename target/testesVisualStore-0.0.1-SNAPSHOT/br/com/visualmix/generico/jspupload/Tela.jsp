<%@ page language="java" %>
<%@ page import="br.com.visualmix.generico.conexao.Application"%>
<script type="text/javascript" src="<%= Application.getInstance().getContextPath() %>/resources/js/funcoes.js"></script>
<LINK REL='STYLESHEET' HREF='<%= Application.getInstance().getContextPath() %>/resources/css/estilo.css' TYPE='Text/css' />
<html>
	<body onload="frmUpload_load()">
		<form id="frmUpload" name="frmUpload" method="post" action="Event.jsp?path=<%=request.getParameter("path")%>&filename=<%=request.getParameter("filename")%>&criapath=<%=request.getParameter("criapath")%>" enctype="multipart/form-data" onkeypress="frmUpload_keypress()" >
		
			<table border='0' cellpadding='1px' cellspacing='0' width=100%>
				<tr> <td align='center'  nowrap > <input name="txtFile" type="file" id="txtFile" value=request.getParameter("path") onkeypress="return false"></br></td></tr>
				<tr> <td align='center'  nowrap > <span name="label" id="label" style=text-align:center;cursor:default;>Aguarde...</span></br></td></tr>
				<tr> <td align='center'  nowrap > <input type="button" id="btnEnviar" title="Enviar arquivo" name="btnEnviar" Class="botao" value="Enviar" onclick=upload() style=width:75;>
					<input type="button" id="btnSair" title="Cancelar upload" name="btnSair" value="Sair" onclick="parent.closeframe()" Class="botao" style=width:75;></br></td></tr>
			</table>

		</form>
	</body>
	<script>
	function frmUpload_keypress(e){ 
		evento = new mxc_evento(e); 
		if (evento.keyCode == 13){ 
			upload();
		}
		if (evento.keyCode == 27){ 
			parent.closeframe();
		}
    } 
	
	function frmUpload_load(){ 
		document.forms[0].txtFile.focus();
		document.getElementById('label').style.visibility = "hidden";
    } 
	
	function upload(){ 
		if(document.forms[0].txtFile.value == ""){
			alert('Nenhum item selecionado!')
			document.forms[0].txtFile.focus();
		}else{
			el = document.getElementById('frmUpload');
		    document.getElementById('txtFile').style.visibility = "hidden";
		    document.getElementById('label').style.visibility = "visible";
		    document.getElementById('btnEnviar').style.visibility = "hidden";
		    document.getElementById('btnSair').style.visibility = "hidden";
		    el.onkeypress="";
			el.submit();
		}
    }
	</script>
</html>