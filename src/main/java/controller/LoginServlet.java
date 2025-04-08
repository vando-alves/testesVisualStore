package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        
	        UserDao userDAO = new UserDao();

	        if (userDAO.ValidateUser(username, password)) {
	            request.getRequestDispatcher("sistema.jsp").forward(request, response);
	        } else {
	            request.getRequestDispatcher("error.jsp").forward(request, response);
	        }
	    }
}
