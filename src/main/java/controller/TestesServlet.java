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
@WebServlet("/teste")
public class TestesServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	    	Regressao regressao = new Regressao();
	    	
	    	regressao.testAlterarSenha();
	    }
}
