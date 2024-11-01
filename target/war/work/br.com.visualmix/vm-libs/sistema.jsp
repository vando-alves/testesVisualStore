<%@ page 
	language="java" 
	session="true" 
	import="br.com.visualmix.generico.conexao.Application"
	import="br.com.visualmix.generico.utils.GenericoAuxiliar"
	import="br.com.maxicode.util.Funcoes"
	import="br.com.maxicode.core.Conexao" 
	import="br.com.maxicode.util.UtilDate"
	import="br.com.maxicode.factory.Factoryi18n"
	import="br.com.maxicode.i18n.ELanguageType"
	import="br.com.maxicode.util.Language"
	import="br.com.maxicode.i18n.messages.EKeyLanguage"
%>
<%@page import="br.com.visualmix.generico.log.InfoLog"%>
<jsp:useBean id='login' scope='session' class='br.com.visualmix.generico.acesso.jsplogin.Login' type="br.com.visualmix.generico.acesso.jsplogin.Login" />
<jsp:useBean id='menu' scope='session' class='br.com.visualmix.generico.menu.Menus' type="br.com.visualmix.generico.menu.Menus" />
<jsp:useBean id='sistem' scope='session' class='br.com.maxicode.html.Sistema' type="br.com.maxicode.html.Sistema" />
<% 	


	Conexao conexao;	
	//Abertura de Conexão e parametrização
	try {			
		if (Application.Inicializou == false) {
		   response.sendRedirect("index.jsp");
		   return;
	    }
		if (Application.getInstance() == null) {
			response.sendRedirect("index.jsp");
			return;
		}
	    if (login.objLogin.getUsuario()	<= 0) {
	       response.sendRedirect("index.jsp");
		   return;
		}
	} catch (Exception e) {
		response.sendRedirect("index.jsp");
		return;
	}	
	
	conexao = (Conexao)session.getAttribute("conexaodasessao");
	if (conexao == null || !conexao.testaConexao()) {
		conexao = Application.getInstance().getNovaConexao();
		conexao.setUsuario(login.objLogin.getUsuario());
		session.setAttribute("conexaodasessao", conexao);	
	}

	menu.setConexao(conexao);
	menu.setLanguageId(login.objLogin.getLanguageId());
	menu.setUsuario(login.objLogin.getUsuario());	

	String date = Factoryi18n.getInstance().getLanguage(login.objLogin.getLanguageId()).getTranslate(EKeyLanguage.DATE.getKey(), EKeyLanguage.DATE.getKey());
	String dataBase = Factoryi18n.getInstance().getLanguage(login.objLogin.getLanguageId()).getTranslate(EKeyLanguage.DATABASE.getKey(), EKeyLanguage.DATABASE.getKey());
	String user = Factoryi18n.getInstance().getLanguage(login.objLogin.getLanguageId()).getTranslate(EKeyLanguage.USER.getKey(), EKeyLanguage.USER.getKey());
	String options = Factoryi18n.getInstance().getLanguage(login.objLogin.getLanguageId()).getTranslate(EKeyLanguage.OPTIONS.getKey(), EKeyLanguage.OPTIONS.getKey());
	String environment = Factoryi18n.getInstance().getLanguage(login.objLogin.getLanguageId()).getTranslate(EKeyLanguage.ENVIRONMENT.getKey(), EKeyLanguage.ENVIRONMENT.getKey());
	String environmentValue = "";
	environmentValue = Funcoes.getParametro("GERAL.TIPOAMBIENTE","");
%>

<html>
<head>
        <meta http-equiv="Cache-Control" content="no-cache, no-store" />
        <meta http-equiv="Pragma" content="no-cache, no-store" />
		<meta http-equiv="Expires" content="Wed, 09 Aug 2000 08:21:57 GMT" />
		<meta http-equiv="Content-type" content="text/html; charset=iso-8859-1" />
        <LINK REL='STYLESHEET' HREF='<%= Application.getInstance().getContextPath() %>/resources/css/estilo20009.css' TYPE='Text/css' />	
        <LINK REL='STYLESHEET' HREF='<%= Application.getInstance().getContextPath() %>/resources/css/menu20009.css' TYPE='Text/css' />	
        <LINK REL='STYLESHEET' HREF='<%= Application.getInstance().getContextPath() %>/resources/css/estilomenu.css' TYPE='Text/css' />
        <LINK REL='STYLESHEET' HREF='<%= Application.getInstance().getContextPath() %>/resources/css/sistema.css' TYPE='Text/css' />	
        <script language='JavaScript' src='<%= Application.getInstance().getContextPath() %>/resources/js/menu20009.js'></script>
        <script language='JavaScript' src='<%= Application.getInstance().getContextPath() %>/resources/js/sistema.js'></script>

<script>

function bodyload() {
	   resizeIframe()   ;
	   desbloquear();
	   <%=sistem.getCallOnload() %>;   
}

window.onresize = resizeIframe;

</script>

</head>
<body onload="bodyload()">
	<div style="visibility:hidden;" class="shadow" id="desktop_shadow"></div>
	<div style="visibility:hidden;" class="shadow" id="menu_shadow"></div>
	<div style="visibility:hidden;" class="shadow" id="header_shadow"></div>
    <iframe  style="visibility:hidden;" class='framedesktop' name="desktop" id="desktop" src="br/com/visualmix/generico/jsptelas/Event.jsp" frameborder="0" scrolling="no"></iframe>
    <div class="menu" id="menu" style="visibility:hidden;">
   		<%if(sistem.isMenuVisibility()) {%>
	        <table class="Principal"><tbody><tr><td class="PrincipalCab" style="TEXT-ALIGN: center"><%=options %></td></tr></tbody></table>
       	<%} %>
	</div>
	<div id="framework" class="framework">Visual Mix Framework</div>
    <div class="header" id="header" style="visibility:xhidden;">
		  			<table border="0" width="100%" style="FONT-SIZE: 8pt; FONT-FAMILY: arial,verdana,sans-serif;">		
		  				<tbody><tr valign="bottom">		
			  				<td rowspan="3" align="right" width="85%">		
			  					<span style="margin-right: 215px;">
									<span style="font-family: sans-serif; color:#32597A;font-size: 17.3333px;font-weight: bold;"><%=!environmentValue.isEmpty() ? environment.concat(":") : ""%></span> 
									<span style="font-family: sans-serif; color:#ffffff;font-size: 17.3333px;font-weight: bold;"><%=!environmentValue.isEmpty() ? environmentValue : ""%></span>
								</span>
								
								
			  					<table  style="FONT-SIZE: 8pt; FONT-FAMILY: arial,verdana,sans-serif;">
			  					<tr><td > <img src="<%= Application.getInstance().getContextPath() %>/resources/images/user.png" alt="Usuário"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><%=user %>:</strong><%= login.objLogin.getUsuarioShow() + "-" + Funcoes.mid(new GenericoAuxiliar(conexao).getNomeUsuario(login.objLogin.getUsuario(), conexao), 0, 22)%></span>
			  					<input id="idUser" name="idUser" type="hidden" value="<%=login.objLogin.getUsuario()%>"> </td></tr>
			  					<tr><td align="right" id='expiraSenha' ></td></tr>
			  					
			  					</table>
			  					
			  					
			  		        </td>	  				
			  				<td align="right" width="15%">	
			  					<table width="100%" style="FONT-SIZE: 8pt; FONT-FAMILY: arial,verdana,sans-serif;">		
						  			<tbody>
						  			<tr><td nowrap align="right"><b>Visual Mix:</b> <%= GenericoAuxiliar.getVrsSistemaString() %></td></tr>
						  			<tr><td nowrap align="right"><b><%=dataBase %> :</b> <%= new GenericoAuxiliar(conexao).getVrsBancoString(conexao) %></td></tr>
						  			<tr><td nowrap align="right"><b><%=date %> :</b> <%= Funcoes.format(UtilDate.now(conexao), "dd/MM/yyyy") %></td></tr>
					  			</tbody></table>		  					
					  		</td>		
					  	</tr>	  		
					</tbody></table>
    </div>
	<iframe border=0 id='iframe_bloqueio' src='' style='filter:alpha(opacity=0);opacity=0.0;-moz-opacity:0.0;border:0;visibility:hidden;position:absolute;left:0;top:0;z-index: 996;'></iframe>
	<div id="div_ajax" class="div_ajax" style="background:red;visibility:hidden;position:absolute;left:6;top:4;width:117;height:18; z-index:997"><center>Aguarde...</center></div>
	<div id="div_bloqueio" class="transparent" style="visibility: xhidden; position: absolute; left: 0px; top: 0px; width: 100%; height: 100%; z-index: 997; background: black;"></div>
	<div class="div_mensagem" id="div_mensagem" style="visibility: hidden; position: absolute; left: 0px; top: 0px; z-index: 998; text-align: center;"> </div>
	<div class="div_mensagem" id="div_control" style="visibility:hidden;position:absolute;left:0;top:0;z-index:998;text-align: center;"> </div>
   	<iframe scrolling="no" name="iframe_modal" class="framemodal" id="iframe_modal"  frameborder="0" src="about:blank" style="Z-INDEX: 999;POSITION: absolute;Top: 0px;VISIBILITY: hidden;"></iframe>
	
</div>

		<script language="JavaScript">
			if(<%=sistem.isMenuVisibility()%>) {//TESTE GLAUCO
       			<%= menu.getVarMenu() %>	    
	   			new menu (MENU_ITEMS, MENU_POS);
			}
    	</script>
		
</body>


<script>
var userMaster = <%=Integer.parseInt(login.getUserMaster())%>
var qtdDias = <%=Integer.parseInt(login.qtdDiasEspirarSenha())%>
var qtdSegundos = <%=Integer.parseInt(login.qtdSegundosEspirarSenha())%>
var senhaAviso = <%=Application.SENHAFORTE_AVISO%>
if(userMaster != 1){
	if(qtdDias == 0 && qtdSegundos == 0){
		document.getElementById("expiraSenha").style.display = "none"
	}else if(senhaAviso != 0 && qtdDias > 0){
		document.getElementById('expiraSenha').innerText = 'Senha expira em ' + qtdDias + ' dias'
		if(senhaAviso >= qtdDias){
			alert('Atenção, sua senha expira em ' + qtdDias + ' dias\nPor favor, trocar a senha.');
		}
	}else if(senhaAviso != 0 && senhaAviso >= 0 && qtdSegundos > 0){
		document.getElementById('expiraSenha').innerText = 'Senha expira hoje'
		alert('Atenção, sua senha expira hoje\nPor favor, trocar a senha.');
	}
}
</script>


<script>

function callopcao(opcao) {

	   if (opcao == '') {
	      return;
	    }

	    fecharopcao();

		<%= menu.getVarControlOpcoes() %>
	  
	    try {
		  window.top.frames['desktop'].focarnoultimo();
		} catch(e) {} 

	}
	
function callControl() {

	var texto
	var el
	var objeto

	el = document.getElementById('div_bloqueio');
	if (el.style.visibility == 'visible') {
	    el = document.getElementById('div_control');
  		if (el.style.visibility != 'visible') {
  	  		//Se div de bloqueio ja esta ativa e nao e o div de controle entao sai da funcao
  		  return false;
  		}
	};
		
	texto = <%= menu.getVarControl() %>;
	if (texto == '') {
	  return false;
	}
	
    bloquear('');
    el = document.getElementById('div_control');
    el.style.visibility = 'visible';
    el.innerHTML = texto 

    if (el.innerHTML == '') {
	   return false;
    }
    
    el.style.width = '400';
    el.style.height = '300';
    el.style.top  = (document.body.offsetHeight - 300) / 2;
    el.style.left = (document.body.offsetWidth - 300) / 2;

    objeto =  document.forms['frmopcao'].elements['txtopcao'];
    try {
  	   objeto.focus();
	   objeto.select();
    } catch(e) {} 
   
}

document.onkeydown = keydowndocument; 

</script>

</html>
