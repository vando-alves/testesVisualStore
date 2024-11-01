<%@ page language="java" %>
<%@page import="br.com.maxicode.factory.Factoryi18n"%>
<%@page import="br.com.maxicode.i18n.ELanguageType"%>
<%@page import="br.com.maxicode.util.Language"%>
<%@page import="java.util.List"%>
<%@page import="br.com.maxicode.i18n.messages.EKeyLanguage"%>

<% 
short languageId = 0;
String absPath = request.getContextPath(); 
boolean	istablet = false;
if (session.getAttribute("istablet") != null) {
	istablet = (Boolean)session.getAttribute("istablet");
} 
String resource = "resources";
if (istablet) {
	resource = "resourcestab";
}

	if (request.getParameter("mensagem") != null) {
		languageId = Short.parseShort(request.getParameter("languageId"));
	}

	String user = Factoryi18n.getInstance().getLanguage(languageId).getTranslate("Usuário", "Usuário");
	String pass = Factoryi18n.getInstance().getLanguage(languageId).getTranslate("Senha", "Senha");
	String idt  = "Visual Mix";
	String ent	= Factoryi18n.getInstance().getLanguage(languageId).getTranslate("Entrar", "Entrar");
	String clr	= Factoryi18n.getInstance().getLanguage(languageId).getTranslate("Limpar", "Limpar");
    String username = Factoryi18n.getInstance().getLanguage(languageId).getTranslate("username", "username");
	String password = Factoryi18n.getInstance().getLanguage(languageId).getTranslate("password", "password");
	String processing = Factoryi18n.getInstance().getLanguage(languageId).getTranslate(EKeyLanguage.PROCESSING.getKey(), EKeyLanguage.PROCESSING.getKey());
	String invalidPassword = Factoryi18n.getInstance().getLanguage(languageId).getTranslate(EKeyLanguage.INVALID_PASSWORD.getKey(), EKeyLanguage.INVALID_PASSWORD.getKey());	
	String invalidUser = Factoryi18n.getInstance().getLanguage(languageId).getTranslate(EKeyLanguage.INVALID_USER.getKey(), EKeyLanguage.INVALID_USER.getKey());	

%>	
<html>

 <head>

   <meta http-equiv="pragma" content="no-cache">
   <meta http-equiv="expires" content="0">

	<% System.out.println(absPath);%>
	
   	<LINK REL="STYLESHEET" HREF="<%= absPath %>/<%= resource %>/css/estilo20009.css" TYPE="text/css" />
	<LINK REL="STYLESHEET" HREF="<%= absPath %>/<%= resource %>/css/flag/bandeiras.css" TYPE="text/css" />
	<LINK REL="STYLESHEET" HREF="<%= absPath %>/<%= resource %>/css/jquery-ui.css" TYPE="text/css" />	
   	<LINK REL="STYLESHEET" HREF="<%= absPath %>/<%= resource %>/css/switch-style.css" TYPE="text/css" />
	<LINK REL="STYLESHEET" HREF="<%= absPath %>/<%= resource %>/css/bio-style.css" TYPE="text/css" />

   	<script src="<%= absPath %>/<%= resource %>/js/jquery-3.3.1.min.js"></script>
   	<script src="<%= absPath %>/<%= resource %>/js/jquery-1.9.1.js"></script>
	<script src="<%= absPath %>/<%= resource %>/js/jquery-ui.js"></script>
   	<script src="<%= absPath %>/<%= resource %>/js/sistema.js"></script>
	<script src="<%= absPath %>/<%= resource %>/js/cookies.js"></script>
	<script src="<%= absPath %>/<%= resource %>/js/initialize.js"></script>
   	<script src="<%= absPath %>/<%= resource %>/js/biometria.js"></script>

  <style type="text/css">

   body{text-align:center}


.desktop {
  position : absolute;
  left: 5px; 
  top: 75px;
  border: 1px #bbbbbb solid;
  box-shadow: 3px 3px 3px #888888;
  background-color:#ffffff;
  xfilter: progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=145,strength=3);
}
   
.header {  position : absolute; left:5px; top:3px; height: 65px; background: #32597A; width:100%;
           box-shadow: 0px 3px 3px #888888;
		   border: 1px #bbbbbb solid;
	       xfilter: progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=145,strength=3);
        }
.shadow {
   position : absolute;
   background-color:#dddddd;
}
   
  </style>

 <title>
  Login do Sistema
 </title>


<script type="text/javascript">

var ua = navigator.userAgent.toLowerCase();
var check = function(r) {
    return r.test(ua);
};
var DOC = document;
var isStrict = DOC.compatMode == "CSS1Compat";
var isOpera = check(/opera/);
var isChrome = check(/chrome/);
var isWebKit = check(/webkit/);
var isSafari = !isChrome && check(/safari/);
var isSafari2 = isSafari && check(/applewebkit\/4/); // unique to
// Safari 2
var isSafari3 = isSafari && check(/version\/3/);
var isSafari4 = isSafari && check(/version\/4/);
var isIE = !isOpera && check(/msie/);
var isIE7 = isIE && check(/msie 7/);
var isIE8 = isIE && check(/msie 8/);
var isIE6 = isIE && !isIE7 && !isIE8;
var isGecko = !isWebKit && check(/gecko/);
var isGecko2 = isGecko && check(/rv:1\.8/);
var isGecko3 = isGecko && check(/rv:1\.9/);
var isBorderBox = isIE && !isStrict;
var isWindows = check(/windows|win32/);
var isMac = check(/macintosh|mac os x/);
var isAir = check(/adobeair/);
var isLinux = check(/linux/);
var isSecure = /^https/i.test(window.location.protocol);
var isIE7InIE8 = isIE7 && DOC.documentMode == 7


function handleEnter (field, event) {
var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
if (keyCode == 13) {
var i;
for (i = 0; i < field.form.elements.length; i++)
if (field == field.form.elements[i])
break;
i = (i + 1) % field.form.elements.length;
try {
  field.form.elements[i].focus();
} catch (e) {}
return false;
}
else
return true;
}

function shadow(objeto,nome) {
   var aux = document.getElementById(nome + '_shadow');
   if (aux) {
     aux.style.height = objeto.offsetHeight + 'px';
     aux.style.width = objeto.offsetWidth + 'px';
     aux.style.top = (objeto.offsetTop + 3) + 'px';
     aux.style.left = (objeto.offsetLeft + 3) + 'px';
	 aux.style.visibility='';
   }
}

function bodyload() {
   resizeIframe();
   try {
   document.frmTela.txtUsuario.focus();
   }
   catch (e) {};
}

function resizeIframe() {  

    var ajuste = 0;
    var iframe = document.getElementById('desktop');
    var header = document.getElementById('header');
    var w = window.innerWidth
         || document.documentElement.clientWidth
         || document.body.clientWidth;  
    w = w - 14;		 
    w = (w < 0) ? 0 : w;

    var h = window.innerHeight
         || document.documentElement.clientHeight
         || document.body.clientHeight;    

    h = h - 85;
    h = (h < 0) ? 0 : h;
    header.style.width = (w-ajuste) + 'px';
    iframe.style.height = (h-ajuste) + 'px';
	if (isIE) {
	    ajuste = -2;
	}
    iframe.style.width = (w-ajuste) + 'px';	
	
	header.style.visibility='';
	iframe.style.visibility='';

	if (isIE) {
	   shadow(header, "header");
	   shadow(iframe, "desktop");
	}

}


window.onresize = resizeIframe;

</script>

 </head>

 
<body onload="bodyload()" onunload="stopSensor()" scroll=no >

 <div style="visibility:hidden;" class="shadow" id="header_shadow"></div>
 <div style="visibility:hidden;" class="shadow" id="desktop_shadow"></div>

<OBJect id="cabecalhoEnvironmentTranslate">
	 <%= Factoryi18n.getInstance().getLanguage(languageId).getTranslate(EKeyLanguage.ENVIRONMENT.getKey(),EKeyLanguage.ENVIRONMENT.getKey()) %>
</OBJect>

 <IFRAME style="visibility:hidden;" class="header" id="header" NAME="Cabecalho" MARGINWIDTH="0" MARGINHEIGHT="0" 
         SRC="cabecalhos.jsp" SCROLLING=no border="false" frameborder="0"></iframe>
		 
<div class="desktop" id="desktop" style="visibility:hidden;">		 
 <table class=Principal>

 <tr><td class=PrincipalCab>
    <p style = "color:white"><%=idt%></p>
 </td></tr>
 
 </table>

  <br>
  <center>
	<div id='div_bloqueio' class='transparent' style='position: absolute; left: 0px; top: 0px; width: 100%; height: 100%; z-index: 997; background: gray; opacity: 	0.4; visibility: hidden;'></div><div class='div_mensagem' id='div_mensagem' style='visibility: hidden; position: absolute; left: 533px; top: 253.5px; z-index: 	998; text-align: center; width: 300px;'><br><%=processing %><br><br></div><iframe border='0' id='iframe_bloqueio' src='' 	style='filter:alpha(opacity=0);opacity=0.0;-moz-opacity:0.0;border:0;visibility:hidden;position:absolute;left:0;top:0;z-index: 996;'></iframe>
<%
	if (request.getParameter("mensagem") != null) {
		String retorno = (String)request.getParameter("mensagem") ;
		out.print("<span  id='lblMensagem'>" + retorno + "</span>");
				
		if(retorno.contains("invalida")){
		   out.print("<script>window.addEventListener('load', function(){callDecision();});</script>"); 
		}
	}
%>
  </center>
  <br>
  <br>
 <form name="frmTela" METHOD="POST" ACTION="Event.jsp" onsubmit="return critica()"> 
     <INPUT TYPE='hidden' NAME='formname' VALUE='frmTela'>
	 <INPUT TYPE="hidden" NAME="evento" VALUE="logar">
	 <INPUT TYPE="hidden" NAME="cmbIdioma" VALUE="0">
<Table align='center'width='330'height='160'border='0' cellpadding='0'cellspacing='0'> 
<tr>
 <td  valign='middle' width='10%'><img src="<%= absPath %>/<%= resource %>/images/cpu.gif"></td>
 <td  width='90%'>
  <center>
    <Table border='0' cellpadding='1' cellspacing = '0' >
   <tr>
	  <td align='right'  valign='bottom'><span  id = "rotuloUsuarios" class='rotulo'><%=user%></span></td>
	  <td align='right'  valign='bottom'> <input type="text" id="usuarios" name="txtUsuario" placeholder=<%= username %> SIZE="12" MAXLENGTH="50" class='caixa' onkeypress="return handleEnter(this, event)" oninput="stopSensorServer()"></td>
    </tr>
    <tr>
	  <td align='right'><span id = "rotuloSenha" class='rotulo'><%=pass%></span></td>
	  <td align='right'> &nbsp;  
	  <INPUT TYPE="password" id="senha" NAME="txtSenha" VALUE="" SIZE="12" MAXLENGTH="12" class='caixa' placeholder=<%= password %>></td>
    </tr>
    <tr>
	 <td align='right' valign = 'bottom'><INPUT TYPE = "hidden"  NAME = "txtTemplate" VALUE = "" id = 'template'"></td>
    </tr>
    
    <tr>
	<td colspan = "2" align = 'left' valign = 'bottom'>
		<label id = "bioAtiva" class = 'rotulo' style = 'vertical-align: text-top;' hidden> Biometria <label>&nbsp;
		<label id = "bio_label" class = "switch"  hidden> 
			<input id = "bio" type = "checkbox" onclick = "alterarLayoutTela()"  hidden>
  			<span id="bio_span" class="slider round" hidden/>
		</label>
	</td>
    </tr>
	
   </table>
  </center>
 </td>
</tr>
</table> 
<br><br>
<center>
<table align='center'>
     <tbody>
	<tr>
           <td style = 'width = 78px;'></td>
	   <td>
	      <INPUT TYPE='submit' id = "btnEnviar" class='botao' value=<%=ent%> style = 'margin-left : 85px; margin-top : -40;'>
	   </td>
	   <td>
	      <INPUT TYPE='button' id = "btnLimpar" class='botao' value=<%=clr%> style = 'margin-top : -40;' onclick="limpar();">
	   </td>
	</tr>
	<tr>
           <td style = 'width = 78px;'></td>
	   <td align='center'>
	      <button id='btnAtivarSensor' title = 'Ativar sensor' style = 'visibility : hidden; margin-top : -40; margin-left: 85px; ');></button>
	   </td>
	</tr>
	<tr> 
           <td style = 'width = 78px;'></td>
	   <td align='center'>
	      <label id = "msgBiometria" class = 'rotulo' style = 'vertical-align: text-top; margin-left: 85px;' hidden>Clique para ativar o sensor.<label>
	   </td>
        </tr>
      </tbody>
</table>
</center>
</form>


	<!-- I18N languages -->
	<%
	List<Language> languages = Factoryi18n.getInstance().loadLanguagesFromDatabase();
	if(languages.size() > 1){
	%>

	<form name="frmLanguage" method="POST" action="Event.jsp"> 
	 	<input type="hidden" name="evento" value="lang"/>
	    <input type="hidden" name="languageId" value="0"/>
    </form>

	<div id='language-selector'>
      <div id='language-selector-icon'><img src="<%= absPath %>/<%= resource %>/images/flag/internet.svg"/></div>
      <ul id="language-selector-container">
      	<% for(Language language: languages) { %>
		<li onclick="sendLanguage(<%= language.getId() %>)">
			<div class="language-selector-option">
				<span><img alt="<%= language.getAbbrev() %>" src="<%= absPath %>/<%= resource %>/<%= language.getImagePath() %>"/></span>
				<%= language.getName() %>
			</div>
		</li>
		<% } %>
		<% if(languages.isEmpty()) { %>
		<li onclick="sendLanguage(0)">
			<div class="language-selector-option">
				<span><img alt ="PT_BR" src="<%= absPath %>/<%= resource%>/images/flag/brazil.svg"/></span>
				Português
			</div>
		</li>
		<% } %>
      </ul>
    </div>
   	<% } %>
</div> 
<script>

function sendLanguage(value) {
	document.forms['frmLanguage'].languageId.value = value;
	document.forms['frmLanguage'].submit();
}

function critica() 
{
 
 
 var erro  = false
 var erros = ""

 if (document.frmTela.txtUsuario.value == "") {
      erros = erros + "<%= invalidUser %>" + "\n"
      erro = true }

 if (document.frmTela.txtSenha.value == "") {
      erros = erros + "<%= invalidPassword %>" + "\n"
      erro = true  }

 if (erro) {
     alert(erros)
     document.frmTela.txtUsuario.focus()
     return false 
 } else {
     return true
 }


}

function limpar() 
{
	document.frmTela.txtUsuario.value="";
	document.frmTela.txtSenha.value="";
	$("#lblMensagem").html("");
	document.frmTela.txtUsuario.focus();
}
parametrizar();
carregaUltimoUsuario();
activeBio();
 
</script>
</body>
</html>
