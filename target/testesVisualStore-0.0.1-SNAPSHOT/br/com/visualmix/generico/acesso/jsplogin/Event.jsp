<%@ page language="java"%>
<%@ page session="true"%>
<%@ page import="br.com.visualmix.generico.conexao.Application"%>
<%@ page import="br.com.maxicode.propriedades.Propriedades"%>
<%@ page import="br.com.maxicode.util.Funcoes"%>
<%@ page import="java.io.StringWriter"%>
<%@ page import="br.com.maxicode.core.Conexao" %>
<jsp:useBean id='telas' scope='session'
	class='br.com.visualmix.generico.jsptelas.Telas'
	type="br.com.visualmix.generico.jsptelas.Telas" />
<jsp:useBean id='login' scope='session'
	class='br.com.visualmix.generico.acesso.jsplogin.Login'
	type="br.com.visualmix.generico.acesso.jsplogin.Login" />
<jsp:useBean id='sistem' scope='session'
	class='br.com.maxicode.html.Sistema'
	type="br.com.maxicode.html.Sistema" />
<%
	Conexao conexao;
	// Abertura de Conexão e parametrização
    boolean	istablet = false;
    if (session.getAttribute("istablet") != null) {
		istablet = (Boolean)session.getAttribute("istablet");
	} 

	if (Application.Inicializou == false) {
		//Guarda o parametro para login caso exista
		login.setRequest(request);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return;
	}

	try {
		Application.initialize();
	} catch (Exception e) {
		e.printStackTrace();
		session.invalidate();
		out.println("<html><center>"
						+ "<br><br><br><br>Erro na Inicialização<br><br><br>Verificar Arquivo: "
						+ Propriedades.CaminhoAbsoluto + "</center></html>");
		return;
	}

    conexao = (Conexao)session.getAttribute("conexaodasessao");
	if (conexao == null || !conexao.testaConexao()) {
		conexao = Application.getInstance().getNovaConexao();
		session.setAttribute("conexaodasessao", conexao);
	}

	telas.setConexao(conexao);
	login.setConexao(conexao);

	login.setRequest(request);
	login.setResponse(response);
	
	String evento = login.getEvent();
	String indice = login.getEventIndex();
	
	login.isOnlyOneLanguage();
	login.loadLanguageFromCookies();

	if (evento.toLowerCase().equals("lang")) {
		if(Funcoes.isByte(request.getParameter("languageId"))){
			login.setLanguageIsChanged(true);
			login.setLanguageId(Byte.parseByte(request.getParameter("languageId")));
			login.addLanguageIntoCookies(login.getLanguageId());
		} else {
			login.addDefaultLanguageIntoCookies();
		}
	}
	
	if (evento.toLowerCase().equals("logar") | login.isLoginAutomatico()) {
		if (login.Logar()) {
			conexao.setUsuario(login.objLogin.getUsuario());
			telas.items.clear();
		    if (istablet) {
				response.sendRedirect(request.getContextPath() + "/tablet.jsp");		    	
		    } else {
				response.sendRedirect(request.getContextPath() + "/sistema.jsp");
		    }
			return;
		}
	}

	if (login.getRedirect().equals("")) {
		StringWriter str = new StringWriter();
		login.frmlogin.linMensagem.getHtml(str);
		//login.frmlogin.getHtml(str, true);
		pageContext.forward("Show.jsp?mensagem=" + str.toString() + "&languageId=" + login.getLanguageId());
		
	} else {
		response.sendRedirect(request.getContextPath() + login.getRedirect());
	}
%>