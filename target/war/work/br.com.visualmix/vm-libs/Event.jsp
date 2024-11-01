<%@ page 
	language="java"
	session="true" 
	import="br.com.maxicode.core.Conexao" 
	import="br.com.maxicode.html.Folder"
	import="br.com.visualmix.generico.conexao.Application"
	import="br.com.visualmix.generico.erro.jsptelaerro.TelaErro"
	import="br.com.visualmix.generico.utils.GenericoAuxiliar"
%>
<jsp:useBean id='telas' scope='session' class='br.com.visualmix.generico.jsptelas.Telas' type="br.com.visualmix.generico.jsptelas.Telas" />
<jsp:useBean id='login' scope='session' class='br.com.visualmix.generico.acesso.jsplogin.Login' type="br.com.visualmix.generico.acesso.jsplogin.Login" />
<jsp:useBean id='sistem' scope='session' class='br.com.maxicode.html.Sistema' type="br.com.maxicode.html.Sistema" />
<%
    String classe 	= request.getParameter("classe");	
	String bean 	= request.getParameter("beanid");
	String permissoes = request.getParameter("permissoes");
	Folder objtela;
	Conexao conexao;
	sistem.setSession(session, request);
	//Abertura de Conexão e parametrização
	try {			
		if (Application.Inicializou == false) {
			sistem.exit();
	   		out.print(sistem.getHtml());
	   		return;
	    }
		if (Application.getInstance() == null) {
			sistem.exit();
	   		out.print(sistem.getHtml());
	   		return;
		}
		if (login.objLogin == null) {
	    	sistem.exit();
	   		out.print(sistem.getHtml());
	   		return;
		}
	    if (login.objLogin.getUsuario()	<= 0) {
	    	sistem.exit();
	   		out.print(sistem.getHtml());
	   		return;
		}
	} catch (Exception e) {
		e.printStackTrace();
		sistem.exit();
   		out.print(sistem.getHtml());
   		return;
	}	
	    	
	if (bean == null || bean.equals("")) {
	   bean = classe;
	}

	boolean	istablet = false;
	if (session.getAttribute("istablet") != null) {
		istablet = (Boolean)session.getAttribute("istablet");
	} 
    sistem.setTablet(istablet);
	
	try {
		conexao = (Conexao)session.getAttribute("conexaodasessao");
		if (conexao == null || !conexao.testaConexao()) {
			conexao = Application.getInstance().getNovaConexao();
			conexao.setUsuario(login.objLogin.getUsuario());
			session.setAttribute("conexaodasessao", conexao);	
		}
	

		objtela = (bean.equals("new") ? null : (Folder)session.getAttribute(bean));
		session.setAttribute(bean, objtela);	
	
	   	if (objtela == null) {
		    try {
		        Class<?> classDefinition = Class.forName(classe);
		        objtela = (Folder)classDefinition.newInstance();
		    } catch (InstantiationException e) {
		        System.out.println(e);
	         	return;
		    } catch (IllegalAccessException e) {
		        System.out.println(e);
	        	return;
		    } catch (ClassNotFoundException e) {
		        System.out.println(e);
	        	return;
		    }
		    if (permissoes != null) {
		       if (conexao.permissao(permissoes) == false) {
		           Exception erro = new Exception(permissoes + " - Sem permissao de acesso");		          
                   sistem.setObject(TelaErro.ERRO_ID, (Object)erro);
          	       response.sendRedirect("br/com/visualmix/generico/erro/jsptelaerro/Event.jsp");
     	          return;
		       } 
		    }
	        if (bean.equals("new")) {
		        bean = objtela.toString();
		    }
			objtela.setTablet(istablet);
	        objtela.setBeanName(bean);
	      	objtela.setLanguageId(login.objLogin.getLanguageId());
	      	objtela.setUsuario(login.objLogin.getUsuario());
	      	//objtela.setConexao(login.objLogin.getConexao());
	      	objtela.setConexao(conexao);
	        objtela.setRequest(request);   		
	      	objtela.setResponse(response);   
	      	objtela.setSistema(sistem);
	      	objtela.setLogAcesso(Application.logacesso);
	      	objtela.Initialize();      	
	      	objtela.initialize();
	      	session.setAttribute(bean, objtela);
	      	
	   	} else {
	      	objtela.setRequest(request);   		
	      	objtela.setResponse(response);   		
	   	}
	} catch (Exception e) {
        //System.out.println(e);
        e.printStackTrace();
        sistem.setObject(TelaErro.ERRO_ID, (Object)e);
    	response.sendRedirect("br/com/visualmix/generico/erro/jsptelaerro/Event.jsp");
     	return;
    }
   	String ajaxcall = request.getParameter("ajaxcall");
   	
   	if (ajaxcall != null) {
		if (ajaxcall.equals("call")) {
	    	String funcao = request.getParameter("funcao");
	  	   	String parametro = request.getParameter("parametro");
		   	String[] params = parametro.split(GenericoAuxiliar.VSEP1);
                  objtela.ajax.clear();
		   	objtela.executeajax(funcao, params);	   
	       	out.print(objtela.ajax.getJSON());
                  objtela.ajax.clear();
	    }
       	return;
   	}
      
   	if (objtela.getEvent().toLowerCase().equals("encerrar")) {
    	objtela.terminate();
		//Application.getInstance().closeConexao(objtela.getConexao());
    	telas.remover(bean);
	   	session.removeAttribute(bean);
       	response.sendRedirect(request.getContextPath() + objtela.getCall("/br/com/visualmix/generico/jsptelas/Event.jsp", "Encerrar", "", bean));
       	return;
   	}

   	if (objtela.getEvent().toLowerCase().equals("tablet")) {
       	response.sendRedirect(request.getContextPath() + objtela.getCall("/tabletmenu.jsp", "", "", bean));
       	return;
   	}
	
    objtela.exit = false;
    sistem.setReload(false);
    
    try {
   		objtela.execute();
    } catch (Exception e) {
//    	System.out.println(e);
        e.printStackTrace();
        sistem.setObject(TelaErro.ERRO_ID, (Object)e);
     	response.sendRedirect("br/com/visualmix/generico/erro/jsptelaerro/Event.jsp");
      	return;
    }
    
   	if (objtela.getRedirect().equals("") == false) {
          if (objtela.getRedirect().toLowerCase().startsWith("http://")) {
	       response.sendRedirect(objtela.getRedirect());
          } else {
	       response.sendRedirect(request.getContextPath() + objtela.getRedirect());
          }
	    return;
   	}
   	
   	if(sistem.isReload()) {
            sistem.setReload(false);
   		out.print(sistem.getHtml());
   		return;
   	}
   	
   	if (objtela.exit) {
   	    return;
   	}%><html><%
	if(objtela.adicionarTela) {
		telas.adicionar(bean, request.getContextPath() + "/Event.jsp?classe=" + classe + "&beanid=" + bean,  objtela.getTitle());   	
	}
	out.print(objtela.getHtml());
%><script>
if(window.parent.document.getElementById("idUser").value != <%=login.objLogin.getUsuario()%>){
	window.parent.location.reload();
}
</script>
</html>

