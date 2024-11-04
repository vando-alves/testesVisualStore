package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DatabspDao;
import testeregressao.Regressao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/testes")
public class TestesServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	    	 String acao = request.getParameter("acao");
	    	 
	    	 switch (acao) {
	            case "testecadastrofornecedor":
	            	
	            Regressao regressao = new Regressao();
	    	    	
				try {
					regressao.testCadastroFornecedor();
					
					 if(regressao.resultadoTeste == true) {
			                response.getWriter().write("Sucesso!");
				      }else { 
				    	  response.getWriter().write("Falha!");
				      }
	               
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	            	
	                break;
	            case "testecadastroperfil":
	            	 Regressao regressao1 = new Regressao();
		    	    	
	 				try {
	 					regressao1.testCadastroPerfil();
	 					
	 					 if(regressao1.resultadoTeste == true) {
	 			                response.getWriter().write("Sucesso!");
	 				      }else { 
	 				    	  response.getWriter().write("Falha!");
	 				      }
	 	               
	 					
	 				} catch (InterruptedException e) {
	 					e.printStackTrace();
	 				}
	 				 break;
	            case "iniciarLogout":
	                // Lógica para iniciar o teste de logout
	                response.getWriter().write("Teste de logout iniciado!");
	                break;
	            default:
	                response.getWriter().write("Ação desconhecida!");
	        }
	    	 
	    	
	    }
}
