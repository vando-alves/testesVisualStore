<%@ page language="java"%>
<%@ page session="true"%>
<%@ page import="br.com.maxicode.enums.EnmVmixConf" %>
<%@ page import="br.com.maxicode.util.Funcoes" %>
<jsp:useBean id='telas' scope='session' class='br.com.visualmix.generico.jsptelas.Telas' type="br.com.visualmix.generico.jsptelas.Telas" />
<jsp:useBean id='sistem' scope='session' class='br.com.maxicode.html.Sistema' type="br.com.maxicode.html.Sistema" />
<jsp:useBean id='login' scope='session' class='br.com.visualmix.generico.acesso.jsplogin.Login' type="br.com.visualmix.generico.acesso.jsplogin.Login" />
<%

	telas.setLanguageId(login.objLogin.getLanguageId());
	telas.setUsuario(login.objLogin.getUsuario());

	// Captura evento a ser executado informando ao Folder para considerar chamada OK
	// independente do controle de id de formulario
	telas.setRequest(request, true);
	telas.setResponse(response);  
	telas.setSistema(sistem);
	String evento = telas.getEvent();
	String indice = telas.getEventIndex();

    boolean	istablet = false;
    if (session.getAttribute("istablet") != null) {
		istablet = (Boolean)session.getAttribute("istablet");
	} 
    sistem.setTablet(istablet);
	if (evento.equals("Encerrar")) {
		telas.remover(telas.getParameter());
		if (telas.items.size() > 0) {
			response.sendRedirect(telas.getFirstItem());
			return;
		} 
	}

	if (istablet) {
		if (telas.items.size() == 0) {
			sistem.setContextpath(Funcoes.getParametro(EnmVmixConf.GERAL_CONTEXTPATH.getParametro()).trim() + "/");
	   		out.print(sistem.getHtml());
	   		return;
		}		
	} else {
		sistem.setContextpath("");
	}
	
   	if(sistem.isReload()) {
   		out.print(sistem.getHtml());
   		return;
   	}
	
	pageContext.forward("Show.jsp");
%>
