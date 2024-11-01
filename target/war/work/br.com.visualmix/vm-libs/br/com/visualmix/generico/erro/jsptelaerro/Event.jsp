<%@ page language="java" %>
<%@ page session="true" %>
<%@ page isErrorPage="true"%>  
<%@ page import="br.com.visualmix.generico.erro.jsptelaerro.TelaErro" %>
<%@ page import="br.com.visualmix.generico.utils.GenericoAuxiliar"%>
<jsp:useBean id='telas' scope='session' class='br.com.visualmix.generico.jsptelas.Telas' type="br.com.visualmix.generico.jsptelas.Telas" />
<jsp:useBean id='login' scope='session' class='br.com.visualmix.generico.acesso.jsplogin.Login' type="br.com.visualmix.generico.acesso.jsplogin.Login" />
<jsp:useBean id='sistem' scope='session' class='br.com.maxicode.html.Sistema' type="br.com.maxicode.html.Sistema" />
<%@ page import="br.com.maxicode.core.Conexao" %>
<%@ page import="br.com.visualmix.generico.conexao.Application" %>
<%
	String diretorio = "jsptelaerro";
  
   	TelaErro objtela = (TelaErro)session.getAttribute(diretorio);
   	
   	if (objtela == null) {
    	objtela = new TelaErro();
    	objtela.setJspThrowable(exception);
    	objtela.setRequest(request);  
      	objtela.setResponse(response);
      	objtela.setSistema(sistem);
      	objtela.Initialize();      	
      	objtela.initialize();
      	session.setAttribute(diretorio, objtela);
   	}

    boolean	istablet = false;
    if (session.getAttribute("istablet") != null) {
		istablet = (Boolean)session.getAttribute("istablet");
	}
   	objtela.setTablet(istablet);
   	
   	
   	String ajaxcall = request.getParameter("ajaxcall");
   	if (ajaxcall != null) {
		if (ajaxcall.equals("call")) {
	    	String funcao = request.getParameter("funcao");
	  	   	String parametro = request.getParameter("parametro");
		   	String[] params = parametro.split(GenericoAuxiliar.VSEP1);
		   	objtela.executeajax(funcao, params);	   
	       	out.print(objtela.ajax.getJSON());
	    }   
       	return;
   	}
   
   	// Captura evento a ser executado
   	objtela.setRequest(request);
  	objtela.setResponse(response);  
   
   	if (objtela.getEvent().equals("encerrar")) {
    	objtela.terminate();
	   	session.removeAttribute(diretorio);
       	response.sendRedirect(request.getContextPath() + objtela.getCall("/br/com/visualmix/generico/jsptelas/Event.jsp", "Encerrar", "", diretorio));
       	return;
   	}
   	if (objtela.getEvent().toLowerCase().equals("tablet")) {
       	response.sendRedirect(request.getContextPath() + objtela.getCall("/tabletmenu.jsp", "", "", diretorio));
       	return;
   	}
  
  	objtela.execute();
     
   	if (objtela.getRedirect().equals("") == false) {
		response.sendRedirect(request.getContextPath() + objtela.getRedirect());	   
	   	return;
   	}
%>
<html>
<%
	String path = "/br/com/visualmix/generico/erro/";
	telas.adicionar(diretorio, request.getContextPath() + path + diretorio + "/Event.jsp", "Erro do Sistema");   	
	out.print(objtela.getHtml());
%>
<script>
</script>
</html>

