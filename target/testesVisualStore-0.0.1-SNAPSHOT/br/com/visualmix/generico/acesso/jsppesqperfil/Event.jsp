<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="br.com.visualmix.generico.acesso.jsppesqperfil.Tela" %>
<%@ page import="br.com.visualmix.generico.utils.GenericoAuxiliar" %>
<jsp:useBean id='telas' scope='session' class='br.com.visualmix.generico.jsptelas.Telas' type="br.com.visualmix.generico.jsptelas.Telas" />
<jsp:useBean id='login' scope='session' class='br.com.visualmix.generico.acesso.jsplogin.Login' type="br.com.visualmix.generico.acesso.jsplogin.Login" />
<%
	String diretorio = "jsppesqperfil";
  
   	Tela objtela = (Tela)session.getAttribute(diretorio);
   
   	if (objtela == null) {
            String permissoes = request.getParameter("permissoes");
		if (permissoes != null) {
		    login.objLogin.getConexao().permissao(permissoes);
		 }
    	      objtela = new Tela();
      	objtela.setLanguageId(login.objLogin.getLanguageId());
      	objtela.setUsuario(login.objLogin.getUsuario());
      	objtela.Initialize();
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
  	
   	if (objtela.getEvent().equals("Encerrar")) {
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
	String path = "/br/com/visualmix/generico/acesso/";
	telas.adicionar(diretorio, request.getContextPath() + path + diretorio + "/Event.jsp",objtela.objBdyTela.header.getTitle());   	
	out.print(objtela.getHtml());
%>
<script>
</script>
</html>

