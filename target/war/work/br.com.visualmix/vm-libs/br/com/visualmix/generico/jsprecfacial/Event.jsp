<%@ page language="java"%>
<%@ page session="true"%>
<%@ page import="br.com.visualmix.generico.jspfacialrec.Tela"%>
<jsp:useBean id='login' scope='session'
	class='br.com.visualmix.generico.acesso.jsplogin.Login'
	type="br.com.visualmix.generico.acesso.jsplogin.Login" />
<%
	String absPath = request.getContextPath(); 
	System.out.println(absPath);
	String resource = "resources";
	
%>
   	<script src="<%= absPath %>/<%= resource %>/js/acesso_load_image.js"></script>
   	<script src="<%= absPath %>/<%= resource %>/js/acesso_camera_manager.js"></script>
   	<script src="<%= absPath %>/<%= resource %>/js/acesso_jpeg_camera_no_flash.js"></script>
   	<script src="<%= absPath %>/<%= resource %>/js/jquery-3.3.1.min.js"></script>
   	<script src="<%= absPath %>/<%= resource %>/js/jquery-1.9.1.js"></script>
	<script src="<%= absPath %>/<%= resource %>/js/jquery-ui.js"></script>
	<script src="<%= absPath %>/<%= resource %>/js/recFacial.js"></script>
	
<% 
	String bean = "camerapopup";
	Tela objTela = (Tela) session.getAttribute(bean);

	if (objTela == null) {
		objTela = new Tela();
		objTela.setLanguageId(login.objLogin.getLanguageId());
		objTela.setUsuario(login.objLogin.getUsuario());
		objTela.setConexao(login.objLogin.getConexao());
		objTela.setRequest(request);
		objTela.initialize();
		session.setAttribute(bean, objTela);
	}
	objTela.setRequest(request);
	objTela.setResponse(response);
	
	String evento = objTela.getEvent();
	String indice = objTela.getEventIndex();

	try {
		objTela.objBdyTela.header.setTitle(request.getParameter("titulo"));
	} catch (Exception e) {
		objTela.objBdyTela.header.setTitle("");
	}
	
%>
<html>
	<%
		out.print(objTela.getHtml(false));
	%>
	
</html>
<script>

	
	function encerrou() {
		parent.closeframe();	
	}

	function selecionou_foto(){
		var srcCaptura = $("#srcimgCaptura").attr('src');
		if(srcCaptura === "<%=absPath %>/<%= resource %>/images/camerasemfoto.jpg"){
			alert("Tire uma foto.");
			return;
		}
		
		parent.frames['desktop'].recFacial.capturou($("#srcimgCaptura").attr('src'));
		parent.closeframe();
       	
	}

	initCamera();	
	
</script>