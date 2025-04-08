package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tests.Regressao;
import tests.atacado.TestCadastroAtacado;
import tests.casadinha.TestCadastroCasadinha;
import tests.componente.TestCadastroComponente;
import tests.cuponagem.TestCadastroCuponagem;
import tests.fornecedor.TestCadastroFornecedor;
import tests.kit.TestCadastrokit;
import tests.loja.TestCadastroLoja;
import tests.mercadologico.TestCadastroMercadologico;
import tests.perfil.TestCadastroPerfil;
import tests.produto.TestCadastroProduto;
import tests.usuario.TestCadastroUsuario;

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
		case "testecadastroloja":

			TestCadastroLoja testCadastroLoja = new TestCadastroLoja();

			try {
				testCadastroLoja.testCadastroLojaValido();
				response.getWriter().write(testCadastroLoja.status);
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
			
		case "testcadastrousuario":
			
			TestCadastroUsuario testCadastroUsuario = new TestCadastroUsuario();

			try {
				testCadastroUsuario.testCadastroUsuarioValido();
				response.getWriter().write(testCadastroUsuario.status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			break;
			
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
			

		case "testecadastromercadologico":

			TestCadastroMercadologico testCadastroMercadologico = new TestCadastroMercadologico();

			try {
				testCadastroMercadologico.testCadastroMercadologicoValido();
				response.getWriter().write(testCadastroMercadologico.status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "testecadastroproduto":

			TestCadastroProduto testCadastroProduto = new TestCadastroProduto();

			try {
				testCadastroProduto.testCadastroProdutoValido();
				response.getWriter().write(testCadastroProduto.status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "testecadastrokit":

			TestCadastroProduto testCadastroProdutoKit = new TestCadastroProduto();
			
			try {
				testCadastroProdutoKit.testCadastroProdutoKitValido();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			TestCadastrokit testCadastrokit = new TestCadastrokit();

			try {
				testCadastrokit.testCadastroKitValido();
				response.getWriter().write(testCadastrokit.status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		
		case "testecadastrocomponentepdv":

			TestCadastroComponente testCadastroComponente = new TestCadastroComponente();

			try {
				testCadastroComponente.testCadastroComponentePdvValido();
				response.getWriter().write(testCadastroComponente.status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "testecadastrocomponentetotem":

			TestCadastroComponente testCadastroComponentetotem = new TestCadastroComponente();

			try {
				testCadastroComponentetotem.testCadastroComponenteTotemValido();
				response.getWriter().write(testCadastroComponentetotem.status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "testecadastrocomponentegateway":

			TestCadastroComponente testCadastroComponentegateway = new TestCadastroComponente();

			try {
				testCadastroComponentegateway.testCadastroComponenteGatewayValido();
				response.getWriter().write(testCadastroComponentegateway.status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "testecadastrocuponagem":

			TestCadastroCuponagem testCadastroCuponagem = new TestCadastroCuponagem();

			try {
				testCadastroCuponagem.testCadastroCuponagemValido();
				response.getWriter().write(testCadastroCuponagem.status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;	
			
		case "testecadastrocasadinha":

			TestCadastroCasadinha testCadastroCasadinha = new TestCadastroCasadinha();

			try {
				testCadastroCasadinha.testCadastroCasadinhaValido();
				response.getWriter().write(testCadastroCasadinha.status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;	
			
		case "testecadastroatacado":

			TestCadastroAtacado testCadastroAtacado = new TestCadastroAtacado();

			try {
				testCadastroAtacado.testCadastroAtacadoValido();
				response.getWriter().write(testCadastroAtacado.status);
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
