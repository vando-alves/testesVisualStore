package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DatabspDao;
import tests.Regressao;
import tests.fornecedor.TestCadastroFornecedor;
import tests.perfil.TestCadastroPerfil;

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

			TestCadastroFornecedor testCadastroFornecedor = new TestCadastroFornecedor();

			try {
				testCadastroFornecedor.testCadastroFornecedorValido();
				response.getWriter().write(testCadastroFornecedor.status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "testecadastroperfil":
			
			TestCadastroPerfil testCadastroPerfil = new TestCadastroPerfil();

			try {
				testCadastroPerfil.testCadastroPerfilValido();
				response.getWriter().write(testCadastroPerfil.status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
