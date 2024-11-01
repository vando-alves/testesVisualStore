<%@ page 
	language="java"
	session="true"
	import="br.com.visualmix.generico.jsppesquisar.Tela" 
	import="br.com.visualmix.generico.utils.GenericoAuxiliar"
	import="br.com.visualmix.generico.conexao.Application"
%>
<jsp:useBean id='login' scope='session'
	class='br.com.visualmix.generico.acesso.jsplogin.Login'
	type="br.com.visualmix.generico.acesso.jsplogin.Login" />
<%
	String bean = "pesquisar";
	Tela objTela = (Tela) session.getAttribute(bean);

	if(!login.objLogin.getConexao().testaConexao()){
		session.setAttribute("conexaodasessao", null);
		session.setAttribute("pesquisar", null);
		Application.getInstance().getConexaoPrincipal().close();
		%>
			<script type="text/javascript">
					window.parent.location.href = '../acesso/jsplogin/Event.jsp';
			</script>
		<%
		System.out.println("(debug-pesquisar) vamos tentar o sendRedirect");	
		return; 		
	}
	
	if (objTela == null) {
		objTela = new Tela();
		objTela.setLanguageId(login.objLogin.getLanguageId());
		objTela.setUsuario(login.objLogin.getUsuario());
		objTela.setConexao(login.objLogin.getConexao());
		objTela.setRequest(request);
		objTela.initialize();
		session.setAttribute(bean, objTela);
	}
	
    boolean	istablet = false;
    if (session.getAttribute("istablet") != null) {
		istablet = (Boolean)session.getAttribute("istablet");
	}
   	objTela.setTablet(istablet);
   	
	
	objTela.setRequest(request);
	objTela.setResponse(response);
	// Captura evento a ser executado
	String evento = objTela.getEvent();
	String indice = objTela.getEventIndex();

	if (evento.toUpperCase().equals("EXECUTAR")) {
		try {
	objTela.objBdyTela.header.setTitle(request.getParameter("titulo"));
		} catch (Exception e) {
	objTela.objBdyTela.header.setTitle("");
		}

		String param[] = objTela.getParameter().split(GenericoAuxiliar.VSEP1);
		switch(param.length){
	case 1:
		objTela.Executar(objTela.getParameter(), "", "", "10");
		break;
	case 2:
		objTela.Executar(param[0], param[1], "", "10");
		break;
	case 3:
		objTela.Executar(param[0], param[1], param[2], "10");
		break;
	case 4:
		objTela.Executar(param[0], param[1], param[2], param[3]);
		break;
		}
	} else {
		objTela.execute();
	}

	if (objTela.getEvent().equals("Encerrar")) {
		objTela.terminate();
		session.removeAttribute(bean);
		return;
	}
   	if (objTela.getEvent().toLowerCase().equals("tablet")) {
		objTela.terminate();
		session.removeAttribute(bean);
		return;
   	}

	if (objTela.getRedirect().equals("") == false) {
		response.sendRedirect(request.getContextPath()
		+ objTela.getRedirect());
		return;
	}
%>
<html>

<script>

	function cancelou() {
    
   		//parent.window.frames['desktop'].<%=objTela.getObjectName()%>.cancelou();
   		parent.frames['desktop'].<%=objTela.getObjectName()%>.cancelou();
	    parent.closeframe();
    
    	//window.opener.<%=objTela.getObjectName()%>.cancelou();
	    //window.close();
	}

	function selecionou() {

    	selecao = <%=objTela.objBdyTela.dtgLista.getNameId()%>_tag();
    
	    if (selecao != '') {
    		parent.frames['desktop'].<%=objTela.getObjectName()%>.selecionou(selecao);
    		//parent.window.frames['desktop'].<%=objTela.getObjectName()%>.selecionou(selecao);
       		parent.closeframe();
       		//window.opener.<%=objTela.getObjectName()%>.selecionou(selecao);
       		//window.close();
       	}
	}

</script>
<%
	out.print(objTela.getHtml(false));
%>
</html>
