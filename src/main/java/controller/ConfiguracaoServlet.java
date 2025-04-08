package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.ConfigDAO;
import dao.UserDao;
import model.ConfigDTO;

/**
 * Servlet implementation class ConfiguracaoServlet
 */
@WebServlet("/ConfiguracaoServlet")
public class ConfiguracaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConfigDTO ConfigDTO = new ConfigDTO();
		
		ConfigDTO.setCaminhoDriver(request.getParameter("driver"));
		ConfigDTO.setIp(request.getParameter("ip"));
		ConfigDTO.setPorta(Integer.valueOf(request.getParameter("porta")));
		ConfigDTO.setUsuario(request.getParameter("usuario"));
		ConfigDTO.setSenha(request.getParameter("password"));
		// Checkbox retorna null se não marcado
		if(Boolean.valueOf(request.getParameter("exibi")!= null)) {
		ConfigDTO.setExibe_teste(1);
		}else {
		ConfigDTO.setExibe_teste(0);
		}
		

		ConfigDAO  configDAO = new ConfigDAO();
		configDAO.Save(ConfigDTO);
		
		 // Redireciona para a página com um parâmetro de sucesso
        response.sendRedirect("configuracao.jsp?success=true");
	}
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        ConfigDAO  configDAO = new ConfigDAO();
	        ConfigDTO ConfigDTO = new ConfigDTO();
			
	        ConfigDTO = configDAO.Read();
	        // Simulando dados vindos do banco
	        JSONObject json = new JSONObject();
	        json.put("driver", ConfigDTO.getCaminhoDriver());
	        json.put("ip", ConfigDTO.getIp());
	        json.put("porta", ConfigDTO.getPorta());
	        json.put("usuario", ConfigDTO.getUsuario());
	        json.put("password", ConfigDTO.getSenha());
	        if( ConfigDTO.getExibe_teste() == 0) {
	        	json.put("exibi", false);
	        }else {
	        	json.put("exibi", true);
	        }
	        

	        response.getWriter().write(json.toString());
	    }
}
