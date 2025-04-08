package tests;

import java.io.IOException;
import java.net.URI;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.maxicode.core.Conexao;
import br.com.maxicode.core.SQLCreator;
import br.com.maxicode.db.IRecordSet;
import br.com.maxicode.util.Funcoes;
import br.com.maxicode.util.UtilDate;
import br.com.visualmix.generico.conexao.Application;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class Regressao {

    String ip = "127.0.0.1";
	String usuario = "visualmix";
	String senha = "1";
	private RegressaoDAL data = new RegressaoDAL();
	public boolean resultadoTeste;


	// TESTE DE LOGIN
		/*@Test
		 *
		 * public void testCasadinha_db() {
		 * 
		 * 
		 * System.out.println(data.getTipoVendas());
		 * 
		 * 
		 * WebDriver driver = LoginVisualStore();
		 * 
		 * driver.findElement(By.id("e0_137i")).click();
		 * driver.findElement(By.id("e0_147i")).click();
		 * driver.findElement(By.id("e0_148i")).click();
		 * driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		 * driver.findElement(By.name("txtLoja")).click();
		 * driver.findElement(By.name("txtLoja")).click(); //ERROR: Caught exception
		 * [ERROR: Unsupported command [doubleClick | name=txtLoja | ]]
		 * driver.findElement(By.name("cmbMotivo")).click(); new
		 * Select(driver.findElement(By.name("cmbMotivo"))).selectByVisibleText(
		 * "Desistência"); driver.findElement(By.name("psqClienteCpfCnpj")).click();
		 * driver.findElement(By.name("psqClienteCpfCnpj")).click(); //ERROR: Caught
		 * exception [ERROR: Unsupported command [doubleClick | name=psqClienteCpfCnpj |
		 * ]] driver.findElement(By.name("psqClienteCpfCnpj")).clear();
		 * driver.findElement(By.name("psqClienteCpfCnpj")).sendKeys("1");
		 * driver.findElement(By.name("psqClienteCpfCnpj")).click();
		 * driver.findElement(By.name("psqClienteCpfCnpj")).click(); //ERROR: Caught
		 * exception [ERROR: Unsupported command [doubleClick | name=psqClienteCpfCnpj |
		 * ]] driver.findElement(By.name("psqClienteCpfCnpj")).clear();
		 * driver.findElement(By.name("psqClienteCpfCnpj")).sendKeys("06538365540");
		 * driver.findElement(By.name("txtObservacao")).click(); acceptNextAlert = true;
		 * driver.findElement(By.name("txtObservacao")).clear();
		 * driver.findElement(By.name("txtObservacao")).sendKeys("qeweqteq");
		 * driver.findElement(By.name("btnSalvar")).click();
		 * assertTrue(closeAlertAndGetItsText().
		 * matches("^O sistema está prestes a salvar os dados\\. Deseja realmente salvá-los[\\s\\S]$"
		 * )); driver.findElement(By.name("cmdOK")).click();
		 * driver.findElement(By.id("lnktabtabTela1")).click();
		 * driver.findElement(By.name("btnProdutosIncluir")).click();
		 * driver.findElement(By.name("psqProduto")).click();
		 * driver.findElement(By.name("psqProduto")).click(); //ERROR: Caught exception
		 * [ERROR: Unsupported command [doubleClick | name=psqProduto | ]]
		 * driver.findElement(By.name("psqProduto")).clear();
		 * driver.findElement(By.name("psqProduto")).sendKeys("1");
		 * driver.findElement(By.name("psqVendedor")).click();
		 * driver.findElement(By.name("psqVendedor")).click(); //ERROR: Caught exception
		 * [ERROR: Unsupported command [doubleClick | name=psqVendedor | ]]
		 * driver.findElement(By.name("psqVendedor")).clear();
		 * driver.findElement(By.name("psqVendedor")).sendKeys("1");
		 * driver.findElement(By.name("cmbAliquota")).click(); new
		 * Select(driver.findElement(By.name("cmbAliquota"))).selectByVisibleText(
		 * "2-teste"); driver.findElement(By.name("txtQuantidade")).click();
		 * driver.findElement(By.name("txtQuantidade")).click(); //ERROR: Caught
		 * exception [ERROR: Unsupported command [doubleClick | name=txtQuantidade | ]]
		 * driver.findElement(By.name("txtQuantidade")).clear();
		 * driver.findElement(By.name("txtQuantidade")).sendKeys("5");
		 * driver.findElement(By.name("txtDesconto")).click();
		 * driver.findElement(By.name("txtDesconto")).click(); //ERROR: Caught exception
		 * [ERROR: Unsupported command [doubleClick | name=txtDesconto | ]]
		 * driver.findElement(By.name("txtDesconto")).clear();
		 * driver.findElement(By.name("txtDesconto")).sendKeys("2");
		 * driver.findElement(By.name("txtPrecoUnitario")).click();
		 * driver.findElement(By.name("txtPrecoUnitario")).click(); //ERROR: Caught
		 * exception [ERROR: Unsupported command [doubleClick | name=txtPrecoUnitario |
		 * ]] driver.findElement(By.name("txtPrecoUnitario")).clear();
		 * driver.findElement(By.name("txtPrecoUnitario")).sendKeys("5");
		 * driver.findElement(By.name("txtAcrescimo")).click();
		 * driver.findElement(By.name("txtAcrescimo")).click(); //ERROR: Caught
		 * exception [ERROR: Unsupported command [doubleClick | name=txtAcrescimo | ]]
		 * acceptNextAlert = true; driver.findElement(By.name("txtAcrescimo")).clear();
		 * driver.findElement(By.name("txtAcrescimo")).sendKeys("1");
		 * driver.findElement(By.name("btnSalvar")).click();
		 * assertTrue(closeAlertAndGetItsText().
		 * matches("^O sistema está prestes a salvar os dados\\. Deseja realmente salvá-los[\\s\\S]$"
		 * )); driver.findElement(By.name("cmdOK")).click();
		 * driver.findElement(By.linkText("Encerrar")).click();
		 * driver.findElement(By.name("dtsPesquisa_button_ver")).click();
		 * driver.findElement(By.xpath(
		 * "//tr[@id='dtsPesquisa_datagrid_lista_line5']/td[7]/a")).click();
		 * acceptNextAlert = true; driver.findElement(By.name("btnAtualizar")).click();
		 * driver.findElement(By.name("btnConfirmar")).click();
		 * assertTrue(closeAlertAndGetItsText().
		 * matches("^O sistema está prestes a salvar os dados\\. Deseja realmente salvá-los[\\s\\S]$"
		 * )); //ERROR: Caught exception [ERROR: Unsupported command [selectWindow |
		 * win_ser_1 | ]] driver.get(
		 * "http://localhost:8091/vm_visualstore_adm/Event.jsp?classe=br.com.visualmix.visualstore.devolucao.cadastro.devol.processo.jsptela.Tela&beanid=8&evento=gerarPdf::"
		 * ); driver.close();
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //WebDriver driver = LoginVisualStore(); //driver.close();
		 * 
		 * }
		 */
	
	@SuppressWarnings("deprecation")
	public int segundos(Date data) {
	
		if (data == null) {
			return 0;
		}
		return ((data.getHours() * 3600) + (data.getMinutes() * 60) + data.getSeconds());
		
	}

	
	public static String format(int valor, String mascara) {
		DecimalFormat  formatador  = new DecimalFormat(mascara);
		return formatador.format(valor);
	}
	
	
	// TESTE DE CADASTRO LOJA
		@Test
		public void teste() throws InterruptedException {

			System.out.println("Teste ");
			
			data.exportarFinalizadoraMdb(1);
			
		}
	


	// ***************************************
	// CADASTROS
	// ***************************************

	// TESTE DE CADASTRO LOJA
	@Test
	public void testCadastroLoja() throws InterruptedException {
		
		String loja = String.valueOf(data.getUltimoId("LOJAS") +1);
		
		WebDriver driver = LoginVisualStore();

		
		driver.findElement(By.id("e0_5i")).click();
		driver.findElement(By.id("e0_20i")).click();
		driver.findElement(By.id("e0_21i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("txtCodigo")).sendKeys(loja);
		driver.findElement(By.name("txtRazaoSoc")).sendKeys("regresao seleniun");
		driver.findElement(By.name("txtDescricao")).sendKeys("regresao seleniun");
		driver.findElement(By.name("txtEndereco")).sendKeys("getulio vargas");
		driver.findElement(By.name("txtNumero")).sendKeys("433");
		driver.findElement(By.name("txtComplemento")).sendKeys("sem complemento");
		driver.findElement(By.name("txtBairro")).sendKeys("Jardim Belval");
		driver.findElement(By.name("txtCep")).sendKeys("06420190");

		driver.findElement(By.name("psqCodMunic")).clear();
		driver.findElement(By.name("psqCodMunic")).sendKeys("3505708");
		driver.findElement(By.name("psqCodMunic")).sendKeys(Keys.TAB);
		driver.findElement(By.name("txtCGC")).click();
		driver.findElement(By.name("txtCGC")).clear();
		driver.findElement(By.name("txtCGC")).sendKeys("01.548.637/0001-80");
		driver.findElement(By.name("txtCnae")).sendKeys("0000000");
		driver.findElement(By.name("txtCae")).sendKeys("0000000000");
		driver.findElement(By.name("txtInscEstadual")).sendKeys("00000000000000000");
		driver.findElement(By.name("txtInscMunicipal")).sendKeys("0000000000000000");
		driver.findElement(By.name("txtFunc")).sendKeys("10");
		driver.findElement(By.name("txtGerente")).sendKeys("vando");
		driver.findElement(By.name("txtTelefone")).sendKeys("199999996");
		driver.findElement(By.name("txtArea")).sendKeys("1");

		driver.findElement(By.name("cmbCrt")).click();
		new Select(driver.findElement(By.name("cmbCrt"))).selectByVisibleText("3 - Regime Normal");
		driver.findElement(By.name("chkAtivo")).click();
		driver.findElement(By.name("usaNFCE")).click();
		driver.findElement(By.name("btnSalvar")).click();
        driver.switchTo().alert().accept();
   
		String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
		Assert.assertEquals(reposta, "Os dados foram salvos com sucesso");

		//TODO VERIFICAR SE LOJA CADASTROU COM SUCESSO
		Thread.sleep(2500);
		Assert.assertEquals(loja,String.valueOf(data.getUltimoId("LOJAS")));
		driver.close();
	}

	// TESTE DE CADASTRO PERFIL
	@Test
	public void testCadastroPerfil() throws InterruptedException {
		WebDriver driver = LoginVisualStore();
		
		driver.findElement(By.id("e0_0i")).click();
	    driver.findElement(By.id("e0_1i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("txtDescricao")).sendKeys("Regressao selenium");
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		
		String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
		

		 try {
			    Assert.assertTrue(reposta.contains("com sucesso"));
				driver.findElement(By.name("cmdOK")).click();
				
				String codigo = driver.findElement(By.name("txtCodigo")).getAttribute("value");
				Thread.sleep(2500);
				Assert.assertEquals(codigo,String.valueOf(data.getUltimoId("ACESSO_PERFIL")));

				driver.findElement(By.id("lnktabtabTela1")).click();
				driver.findElement(By.name("dtgPermissao_header")).click();
				driver.findElement(By.id("lnktabtabTela2")).click();

				driver.findElement(By.xpath("//div[@id='tabtabTela2']/table/tbody/tr/td/div/table/tbody/tr/td")).click();
				driver.findElement(By.name("btnSalvar")).click();
				driver.switchTo().alert().accept();
				driver.findElement(By.name("cmdOK")).click();
	            resultadoTeste = true; // O teste passou
	        } catch (NoSuchElementException e) {
	            System.out.println("Erro: Elemento não encontrado.");
	            resultadoTeste = false; // Define o teste como falho
	            throw e; // Relança a exceção para que o JUnit registre a falha
	        } catch (AssertionError e) {
	            resultadoTeste = false; // O teste falhou em uma asserção
	            System.out.println("O teste falhou em uma asserção: " + e.getMessage());
	            throw e; // Relança a exceção para que o JUnit registre a falha
	        } catch (Exception e) {
	            System.out.println("Erro inesperado: " + e.getMessage());
	            resultadoTeste = false; // O teste falhou em uma asserção
	            throw e; // Relança a exceção para que o JUnit registre a falha
	        } finally {
	            driver.close(); // Fecha o driver, independentemente do resultado
	        }
	}

	// TESTE DE CADASTRO USUARIO
	@Test
	public void testCadastroUsuario() throws InterruptedException {
		
		int loja = data.getUltimoId("LOJAS");
		String NiveisPDV = data.getNiveisPDV();
		String usuario = String.valueOf(data.getUltimoId("ACESSO_OPERADORES") +1);
		
		WebDriver driver = LoginVisualStore();
		driver.findElement(By.id("e0_0i")).click();
		driver.findElement(By.id("e0_2i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("cmbLoja")).click();
		new Select(driver.findElement(By.name("cmbLoja"))).selectByVisibleText(Funcoes.format(loja, "0000" ) +"-"+ data.getDescLoja(String.valueOf(loja)));
		driver.findElement(By.name("txtLogin")).sendKeys("usuario regresao seleniun " + usuario);
		driver.findElement(By.name("txtIdentificacao")).sendKeys(usuario);
		driver.findElement(By.name("txtNome")).sendKeys("usuario regresao seleniun");
		driver.findElement(By.name("txtTelefone")).sendKeys("11968315270"); 
		driver.findElement(By.name("cmbSituacao")).click();
		new Select(driver.findElement(By.name("cmbSituacao"))).selectByVisibleText("0-Ativo");
		driver.findElement(By.name("txtEmail1")).sendKeys("vando.Jesus29@gmail.com");
		driver.findElement(By.name("txtEmail2")).sendKeys("vando.Jesus29@gmail.com");
		driver.findElement(By.name("txtEmail3")).sendKeys("vando.Jesus29@gmail.com");
		driver.findElement(By.name("txtSenha")).sendKeys("1");
		driver.findElement(By.name("txtSenhaConf")).sendKeys("1");

		driver.findElement(By.name("txtSenhaPdv")).sendKeys("1");
	    driver.findElement(By.name("txtSenhaPdvConf")).sendKeys("1");
		driver.findElement(By.name("cmbNivel")).click();
		new Select(driver.findElement(By.name("cmbNivel"))).selectByVisibleText(NiveisPDV);
		driver.findElement(By.name("txtCodigoIntegracao")).sendKeys(usuario);
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();

		// ABA PERMISSOES
		driver.findElement(By.id("lnktabtabTela1")).click();
		driver.findElement(By.name("dtgPermissao_header")).click();
		driver.findElement(By.xpath("//div[@id='tabtabTela1']/table/tbody/tr[4]/td/input")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();

		// ABA LOJA
		driver.findElement(By.id("lnktabtabTela2")).click();
		driver.findElement(By.name("dtgLojas_header")).click();
		driver.findElement(By.xpath("//div[@id='tabtabTela2']/table/tbody/tr[4]/td/input")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();

		driver.close();

	}

	// TESTE DE CADASTRO FORNECEDOR
	@Test
	public void testCadastroFornecedor() throws InterruptedException {
		
		String fornecedor = String.valueOf(data.getUltimoId("FORNECEDORES") +1);
		
		WebDriver driver = LoginVisualStore();

		driver.findElement(By.id("e0_5i")).click();
	    driver.findElement(By.id("e0_6i")).click();
	    driver.findElement(By.id("e0_8i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("txtCodigo")).sendKeys(fornecedor);
		driver.findElement(By.name("txtRazaoSoc")).sendKeys("AMBEV SELENIUN");
		driver.findElement(By.name("txtNomeFant")).sendKeys("AMBEV SELENIUN");
		driver.findElement(By.name("txtEndereco")).sendKeys("R GETULIO VARGAS");
		driver.findElement(By.name("txtEstado")).sendKeys("SP");
		driver.findElement(By.name("txtCidade")).sendKeys("BARUERI");
		driver.findElement(By.name("txtBairro")).sendKeys("JARDIM BELVAL");
		driver.findElement(By.name("txtcep")).sendKeys("06420-190");
		driver.findElement(By.name("txtCxaPostal")).clear();
		driver.findElement(By.name("txtCxaPostal")).sendKeys("0");
		driver.findElement(By.name("txtFone")).sendKeys("11968315270");
		driver.findElement(By.name("cmbFrete")).click();
		new Select(driver.findElement(By.name("cmbFrete"))).selectByVisibleText("C - CIF");
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
		 try {
			    Assert.assertEquals(reposta, "Os dados foram salvos com sucesso");
	        	driver.findElement(By.name("cmdOK")).click();
	    		Thread.sleep(2500);
	    		Assert.assertEquals(fornecedor,String.valueOf(data.getUltimoId("FORNECEDORES")));
	            resultadoTeste = true; // O teste passou
	        } catch (NoSuchElementException e) {
	            System.out.println("Erro: Elemento não encontrado.");
	            resultadoTeste = false; // Define o teste como falho
	            throw e; // Relança a exceção para que o JUnit registre a falha
	        } catch (AssertionError e) {
	            resultadoTeste = false; // O teste falhou em uma asserção
	            System.out.println("O teste falhou em uma asserção: " + e.getMessage());
	            throw e; // Relança a exceção para que o JUnit registre a falha
	        } catch (Exception e) {
	            System.out.println("Erro inesperado: " + e.getMessage());
	            resultadoTeste = false; // O teste falhou em uma asserção
	            throw e; // Relança a exceção para que o JUnit registre a falha
	        } finally {
	            driver.close(); // Fecha o driver, independentemente do resultado
	        }
	}
	
	// TESTE DE CADASTRO MERCADOLÃ“GICO
	@Test
	public void testCadastroMercadologico() throws InterruptedException {

		String mercadologico = String.valueOf(data.getUltimoIdMercadologico() +1);
		
		WebDriver driver = LoginVisualStore();
		driver.findElement(By.id("e0_5i")).click();
		driver.findElement(By.id("e0_6i")).click();
	    driver.findElement(By.id("e0_9i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("cmbNiveis")).click();
		new Select(driver.findElement(By.name("cmbNiveis"))).selectByVisibleText("01");
		driver.findElement(By.name("txtDescricao")).sendKeys("MERCADOLOGICO REGRESSÃO SELENIUN");
		driver.findElement(By.name("txtCodigo")).sendKeys(mercadologico);
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		Thread.sleep(2500);
		Assert.assertEquals(mercadologico,String.valueOf(data.getUltimoIdMercadologico()));
		driver.close();
		
		
		
	}

	// TESTE DE CADASTRO PRODUTO
	@Test
	public void testCadastroProduto() throws InterruptedException {
		
		String produto = String.valueOf(data.getUltimoIdProduto() +1);
		
		WebDriver driver = LoginVisualStore();
		
		driver.findElement(By.id("e0_5i")).click();
	    driver.findElement(By.id("e0_6o")).click();
		driver.findElement(By.id("e0_7i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("txtProdutoId")).clear();
		driver.findElement(By.name("txtProdutoId")).sendKeys(produto);
		driver.findElement(By.name("txtDescricaoCompleta")).sendKeys("produto regresao seleniun");
		driver.findElement(By.name("txtDescricaoReduzida")).sendKeys("produto regresao");
		driver.findElement(By.name("txtFornecedor")).clear();
		driver.findElement(By.name("txtFornecedor")).sendKeys(String.valueOf(data.getUltimoId("FORNECEDORES")));
		driver.findElement(By.name("txtReferencia")).sendKeys("teste referncia");
		driver.findElement(By.name("txtDescricaoBalanca")).sendKeys("teste desc balanca");
		driver.findElement(By.name("cmbSituacao")).click();
		new Select(driver.findElement(By.name("cmbSituacao"))).selectByVisibleText("Ativo");
		driver.findElement(By.name("cbxFabricacaoPropria")).click();
		driver.findElement(By.name("chkLocalProducao")).click();
		driver.findElement(By.name("cbxEmiteEtiqueta")).click();
		driver.findElement(By.name("cbxPesoVariavel")).click();
		driver.findElement(By.name("cbxPrePesado")).click();
		driver.findElement(By.name("cbxQtdDecimal")).click();

	
		// INCLUIR MERCADOLOGICO
		driver.findElement(By.id("lnktabtabTela2")).click();
		new Select(driver.findElement(By.name("cmbMercadoLogico1")))
				.selectByVisibleText(data.getUltimoIdMercadologico());
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();
		
		
		// INCLUIR EMBALAGEM

		driver.findElement(By.id("lnktabtabTela3")).click();
		driver.findElement(By.name("btnIncluirEmbalagens")).click();
		driver.findElement(By.name("txtDescricao")).click();
		driver.findElement(By.name("txtDescricao")).clear();
		driver.findElement(By.name("txtDescricao")).sendKeys("UND");
		driver.findElement(By.name("txtSequencia")).clear();
		driver.findElement(By.name("txtSequencia")).sendKeys("1");
		driver.findElement(By.name("cmbTipoValidade")).click();
		new Select(driver.findElement(By.name("cmbTipoValidade"))).selectByVisibleText("Dias");
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();
		driver.findElement(By.name("btnFechar")).click();
		
		
		// INCLUIR CODIGO AUTOMACAO

		driver.findElement(By.name("btnIncluirAutomacao")).click();
		driver.findElement(By.name("cmbSeqEmbalagem")).click();
		new Select(driver.findElement(By.name("cmbSeqEmbalagem"))).selectByVisibleText("1 - UND / 0.0");
		driver.findElement(By.name("cmbTipoCodigo")).click();
		new Select(driver.findElement(By.name("cmbTipoCodigo"))).selectByVisibleText("Ean");
		driver.findElement(By.name("txtCodAutomacao")).clear();
		driver.findElement(By.name("txtCodAutomacao")).sendKeys(Funcoes.format(data.getUltimoIdProduto(), "000000000000")+"0");
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();
		driver.findElement(By.name("btnFechar")).click();
		

		// EMBALAGEM LOJA

		driver.findElement(By.id("lnktabtabTela4")).click();
		driver.findElement(By.name("btnIncluirEmbLoja")).click();
		driver.findElement(By.name("cmbSeqEmbalagem")).click();
		new Select(driver.findElement(By.name("cmbSeqEmbalagem"))).selectByVisibleText("1 - UND / 0.0");
		driver.findElement(By.name("txtLoja")).clear();
		driver.findElement(By.name("txtLoja")).sendKeys(String.valueOf(data.getUltimoId("LOJAS")));
		driver.findElement(By.name("txtPrecoVenda")).clear();
		driver.findElement(By.name("txtPrecoVenda")).sendKeys("3,99");
		driver.findElement(By.id("lnktabtabTela1")).click();
		driver.findElement(By.name("txtSituacaoTributaria")).clear();
		driver.findElement(By.name("txtSituacaoTributaria")).sendKeys("0");
		driver.findElement(By.name("txtAliquota")).clear();
		driver.findElement(By.name("txtAliquota")).sendKeys("11,00");
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();

		Assert.assertEquals(produto,String.valueOf(data.getUltimoIdProduto()));

		driver.close();
	}

	// TESTE DE CADASTRO KIT
	@Test
	public void testCadastroProdutokit() {
		
		String produto = String.valueOf(data.getUltimoIdProduto());
		String fornecedor = String.valueOf(data.getUltimoId("FORNECEDORES"));
		
		WebDriver driver = LoginVisualStore();
		driver.findElement(By.id("e0_5i")).click();
		driver.findElement(By.id("e0_6i")).click();
		driver.findElement(By.id("e0_7i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("txtProdutoId")).clear();
		driver.findElement(By.name("txtProdutoId")).sendKeys(produto);
		driver.findElement(By.name("txtDescricaoCompleta")).sendKeys("kit regresao seleniun");
		driver.findElement(By.name("txtDescricaoReduzida")).sendKeys("kit regresao");
		driver.findElement(By.name("txtFornecedor")).clear();
		driver.findElement(By.name("txtFornecedor")).sendKeys(fornecedor);
		driver.findElement(By.name("txtReferencia")).sendKeys("teste referncia");
		driver.findElement(By.name("txtDescricaoBalanca")).sendKeys("teste desc balanca");
		driver.findElement(By.name("cmbSituacao")).click();
		new Select(driver.findElement(By.name("cmbSituacao"))).selectByVisibleText("Ativo");
		driver.findElement(By.name("cbxFabricacaoPropria")).click();
		driver.findElement(By.name("chkLocalProducao")).click();
		driver.findElement(By.name("cbxEmiteEtiqueta")).click();
		driver.findElement(By.name("cbxPesoVariavel")).click();
		driver.findElement(By.name("cbxPrePesado")).click();
		driver.findElement(By.name("cbxQtdDecimal")).click();

		// INCLUIR MERCADOLOGICO
		driver.findElement(By.id("lnktabtabTela2")).click();
		new Select(driver.findElement(By.name("cmbMercadoLogico1")))
				.selectByVisibleText("000022 - MERCADOLOGICO REGREÃ‡ÃƒO SELENIUN");
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();

		// INCLUIR EMBALAGEM

		driver.findElement(By.id("lnktabtabTela3")).click();
		driver.findElement(By.name("btnIncluirEmbalagens")).click();
		driver.findElement(By.name("cmbTipoValidade")).click();
		new Select(driver.findElement(By.name("cmbTipoValidade"))).selectByVisibleText("Dias");
		driver.findElement(By.name("txtDescricao")).clear();
		driver.findElement(By.name("txtDescricao")).sendKeys("UND");
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();
		driver.findElement(By.name("btnFechar")).click();

		driver.close();
	}

	// TESTE DE CADASTRO KIT
	@Test
	public void testCadastrokit() {

		String loja = String.valueOf(data.getUltimoId("LOJAS"));
		String produto = String.valueOf(data.getUltimoIdProduto());
		String fornecedor = String.valueOf(data.getUltimoId("FORNECEDORES"));
		
		
		WebDriver driver = LoginVisualStore();

		// INCLUIR KIT
		driver.findElement(By.id("e0_5i")).click();
		driver.findElement(By.id("e0_6i")).click();
		driver.findElement(By.id("e0_10i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("txtCodigo")).clear();
		driver.findElement(By.name("txtCodigo")).sendKeys("27");
		driver.findElement(By.name("txtLoja")).clear();
		driver.findElement(By.name("txtLoja")).sendKeys(loja);
		driver.findElement(By.name("txtProdutoId")).clear();
		driver.findElement(By.name("txtProdutoId")).sendKeys("37");
		driver.findElement(By.name("cmbEmbalagem")).click();
		driver.findElement(By.name("cmbTipoPreco")).click();
		new Select(driver.findElement(By.name("cmbTipoPreco"))).selectByVisibleText("PreÃ§o Kit");
		driver.findElement(By.name("txtPrecoVenda")).clear();
		driver.findElement(By.name("txtPrecoVenda")).sendKeys("1");
		driver.findElement(By.name("txtFatorVenda")).clear();
		driver.findElement(By.name("txtFatorVenda")).sendKeys("1");
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();

		driver.close();
	}
	
	
	// TESTE DE CADASTRO COMPONENTE PDV
	@Test
	public void testCadastroComponentePDV() throws InterruptedException {
		
		String loja = String.valueOf(data.getUltimoId("LOJAS"));
		String componentePDV = String.valueOf(data.getUltimoIdComponente(loja) +1);

		WebDriver driver = LoginVisualStore();

		   driver.findElement(By.id("e0_5i")).click();
		    driver.findElement(By.id("e0_28i")).click();
		    driver.findElement(By.id("e0_29i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("txtLoja")).clear();
		driver.findElement(By.name("txtLoja")).sendKeys(loja);
		driver.findElement(By.name("txtCodigo")).clear();
		driver.findElement(By.name("txtCodigo")).sendKeys(componentePDV);
		driver.findElement(By.name("txtLocalizao")).sendKeys("PDV REGRESAO");
		driver.findElement(By.name("cmbModelo")).click();
		new Select(driver.findElement(By.name("cmbModelo"))).selectByVisibleText("0-NÃO INFORMADO");
		driver.findElement(By.name("cbxNFCe")).click();
		driver.findElement(By.name("cbxParametros")).click();
		driver.findElement(By.name("cbxProdutos")).click();
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
		Assert.assertEquals(reposta, "Os dados foram salvos com sucesso");
		
		driver.findElement(By.name("cmdOK")).click();
		
		Assert.assertEquals(componentePDV,String.valueOf(data.getUltimoIdComponente(loja)));
		driver.close();
	}

	// TESTE DE CADASTRO COMPONENTE TOTEM
	@Test
	public void testCadastroComponenteTotem() throws InterruptedException {

		String loja = String.valueOf(data.getUltimoId("LOJAS"));
		String componentePDV = String.valueOf(data.getUltimoIdComponente(loja) +1);
		
		WebDriver driver = LoginVisualStore();

		driver.findElement(By.id("e0_5i")).click();
	    driver.findElement(By.id("e0_28i")).click();
	    driver.findElement(By.id("e0_29i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("txtLoja")).clear();
		driver.findElement(By.name("txtLoja")).sendKeys(loja);
		driver.findElement(By.name("txtCodigo")).click();
		driver.findElement(By.name("txtCodigo")).clear();
		driver.findElement(By.name("txtCodigo")).sendKeys(componentePDV);
		driver.findElement(By.name("cmbTipo")).click();
		new Select(driver.findElement(By.name("cmbTipo"))).selectByVisibleText("Totem");
		driver.findElement(By.name("txtLocalizao")).sendKeys("totem regresao");
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		
		String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
		Assert.assertEquals(reposta, "Os dados foram salvos com sucesso");
		
		driver.findElement(By.name("cmdOK")).click();
		
		Assert.assertEquals(componentePDV,String.valueOf(data.getUltimoIdComponente(loja)));

		driver.close();
	}

	// TESTE DE CADASTRO COMPONENTE GATEWAY
	@Test
	public void testCadastroComponenteGateway() throws InterruptedException {

		String loja = String.valueOf(data.getUltimoId("LOJAS"));
		String componentePDV = String.valueOf(data.getUltimoIdComponente(loja) +1);
		
		WebDriver driver = LoginVisualStore();

		driver.findElement(By.id("e0_5i")).click();
	    driver.findElement(By.id("e0_28i")).click();
	    driver.findElement(By.id("e0_29i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("txtLoja")).clear();
		driver.findElement(By.name("txtLoja")).sendKeys(loja);
		driver.findElement(By.name("txtCodigo")).clear();
		driver.findElement(By.name("txtCodigo")).sendKeys(componentePDV);
		driver.findElement(By.name("cmbTipo")).click();
		new Select(driver.findElement(By.name("cmbTipo"))).selectByVisibleText("Gateway");
		driver.findElement(By.name("txtLocalizao")).clear();
		driver.findElement(By.name("txtLocalizao")).sendKeys("gataway regresao");
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
		Assert.assertEquals(reposta, "Os dados foram salvos com sucesso");
		
		driver.findElement(By.name("cmdOK")).click();
		
		Assert.assertEquals(componentePDV,String.valueOf(data.getUltimoIdComponente(loja)));

		driver.close();
	}


	// ***************************************
	// CADASTROS PROMOÃ‡OES4zew3a
	// ***************************************

	// TESTE DE CADASTRO CUPONAGEM
	@Test
	public void testCadastroCuponagem() throws InterruptedException {

		String loja = String.valueOf(data.getUltimoId("LOJAS"));
		String cuponagem = String.valueOf(data.getUltimoId("CUPOM") +1);
		
		WebDriver driver = LoginVisualStore();

		
	    driver.findElement(By.id("e0_5i")).click();
	    driver.findElement(By.id("e0_46o")).click();
	    driver.findElement(By.id("e0_52i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("txtCodigo")).sendKeys(cuponagem);
		driver.findElement(By.name("txtDescricao")).sendKeys("cuponagem regresao");
		driver.findElement(By.name("txtPdv")).sendKeys("0");
		driver.findElement(By.id("lnktabtabTela1")).click();
		driver.findElement(By.name("txtMinimo")).sendKeys("1");
		driver.findElement(By.id("lnktabtabTela2")).click();
		driver.findElement(By.name("txtQtdeMax")).sendKeys("1");
		driver.findElement(By.name("txtPercDesc")).sendKeys("2");
		driver.findElement(By.name("txtVlrDesc")).sendKeys("3");
		driver.findElement(By.name("txtVlrMax")).sendKeys("4");
		driver.findElement(By.name("btnSalvar")).click();
		String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
		Assert.assertTrue(reposta.contains("Salvo com sucesso"));
		
		driver.findElement(By.name("cmdOK")).click();
		
		Assert.assertEquals(cuponagem,String.valueOf(data.getUltimoId("CUPOM")));
		
		driver.findElement(By.id("lnktabtabTela3")).click();
		driver.findElement(By.name("txtLojas")).sendKeys(loja);
		driver.findElement(By.name("btnSalvarLojas")).click();
		driver.close();

	}

	// TESTE DE CADASTRO CASADINHA
	@Test
	public void testCadastroCasadinha() throws InterruptedException {

		//testCadastroProduto();
		String produto =  String.valueOf(data.getUltimoIdProduto());
		
		String loja = String.valueOf(data.getUltimoId("LOJAS"));
		String casadinha = String.valueOf(data.getUltimoId("CASADINHA") +1);
		
		WebDriver driver = LoginVisualStore();
	    driver.findElement(By.id("e0_5i")).click();
	    driver.findElement(By.id("e0_46i")).click();
	    driver.findElement(By.id("e0_47i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("txtDescricao")).sendKeys("Casadinha regresao seleniun " + casadinha);
		driver.findElement(By.name("txtCuponagem")).sendKeys("0");
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();

		driver.findElement(By.id("lnktabtabTela1")).click();
		driver.findElement(By.name("pesProdutoOrigem0000000000")).sendKeys(produto);
		driver.findElement(By.name("pesProdutoOrigem0000000000")).sendKeys(Keys.TAB);
		driver.findElement(By.name("cmbEmabalagemOrigem1")).click();
		new Select(driver.findElement(By.name("cmbEmabalagemOrigem1"))).selectByVisibleText("1 - UND/0000");
		driver.findElement(By.name("quantidadeOrigem2")).clear();
		driver.findElement(By.name("quantidadeOrigem2")).click();
		driver.findElement(By.name("quantidadeOrigem2")).sendKeys("1");
		driver.findElement(By.name("btnAdd6")).click();
		driver.findElement(By.name("btnSalvarOrigem")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();

		driver.findElement(By.id("lnktabtabTela2")).click();
		driver.findElement(By.name("cmbTipoDescontoPromovido")).click();
		new Select(driver.findElement(By.name("cmbTipoDescontoPromovido"))).selectByVisibleText("Valor de desconto");
		driver.findElement(By.name("txtValorPromovido")).click();
		driver.findElement(By.name("txtValorPromovido")).clear();
		driver.findElement(By.name("txtValorPromovido")).sendKeys("1");
		driver.findElement(By.name("txtQuantidadePromovido")).click();
		driver.findElement(By.name("txtQuantidadePromovido")).clear();
		driver.findElement(By.name("txtQuantidadePromovido")).sendKeys("1");
		driver.findElement(By.name("pesProduto0000000000")).sendKeys(produto);
		driver.findElement(By.name("pesProduto0000000000")).sendKeys(Keys.TAB);
		driver.findElement(By.name("cmbEmbalegmPromovido1")).click();
		new Select(driver.findElement(By.name("cmbEmbalegmPromovido1"))).selectByVisibleText("1 - UND/0000");
		driver.findElement(By.name("cmbTipoDescontoProvido2")).click();
		new Select(driver.findElement(By.name("cmbTipoDescontoProvido2"))).selectByVisibleText("Valor de desconto");

		driver.findElement(By.name("txtValorPromovido3")).click();

		driver.findElement(By.name("txtValorPromovido3")).clear();
		driver.findElement(By.name("txtValorPromovido3")).sendKeys("1");
		driver.findElement(By.name("btnAdd5")).click();
		driver.findElement(By.name("btnSalvarPromovido")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();
		
		driver.findElement(By.id("lnktabtabTela3")).click();
		driver.findElement(By.name("txtCodigoBin")).click();
		driver.findElement(By.name("txtCodigoBin")).clear();
		driver.findElement(By.name("txtCodigoBin")).sendKeys("1234");
		driver.findElement(By.xpath("//div[@id='tabtabTela3']/table/tbody/tr[6]/td/input")).click();
		driver.findElement(By.name("cmdOK")).click();

		driver.findElement(By.id("lnktabtabTela7")).click();
		driver.findElement(By.name("dtgLojas_header")).click();
		driver.findElement(By.xpath("//div[@id='tabtabTela7']/table/tbody/tr[4]/td/input")).click();
		driver.switchTo().alert().accept();

		String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
		Assert.assertTrue(reposta.contains("salvos com sucesso"));
		
		driver.findElement(By.name("cmdOK")).click();
		
		Assert.assertEquals(casadinha,String.valueOf(data.getUltimoId("CASADINHA")));
		
		driver.close();
	}

	// TESTE DE CADASTRO APARTIDE
	@Test
	public void testCadastroApartiDe() {

		WebDriver driver = LoginVisualStore();

		   driver.findElement(By.id("e0_5i")).click();
		    driver.findElement(By.id("e0_46o")).click();
		    driver.findElement(By.id("e0_53i")).click();
		driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
		driver.findElement(By.name("txtProdutoId")).sendKeys(String.valueOf(data.getUltimoIdProduto()));
		driver.findElement(By.name("cmbSeqEmbalagem")).click();
		driver.findElement(By.name("cmbTipo")).click();
		new Select(driver.findElement(By.name("cmbTipo"))).selectByVisibleText(data.getTipoVendas());
		driver.findElement(By.name("txtQtdeMaxima")).clear();
		driver.findElement(By.name("txtQtdeMaxima")).sendKeys("10");
		driver.findElement(By.name("txtQtd1")).clear();
		driver.findElement(By.name("txtQtd1")).sendKeys("2");
		driver.findElement(By.name("cmbTipoDesconto1")).click();
		new Select(driver.findElement(By.name("cmbTipoDesconto1"))).selectByVisibleText("Preço de Venda");
		
		driver.findElement(By.name("txtValor1")).clear();
		driver.findElement(By.name("txtValor1")).sendKeys("3,50");
		driver.findElement(By.name("txtQtd2")).clear();
		driver.findElement(By.name("txtQtd2")).sendKeys("1");
		driver.findElement(By.name("cmbTipoDesconto2")).click();
		new Select(driver.findElement(By.name("cmbTipoDesconto2"))).selectByVisibleText("Percentual Desconto");
		driver.findElement(By.name("txtValor2")).clear();
		driver.findElement(By.name("txtValor2")).sendKeys("3,00");
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("cmdOK")).click();
		
		String atacado =  driver.findElement(By.name("txtCodigoOperacao")).getAttribute("value");

		driver.findElement(By.id("lnktabtabTela2")).click();
		driver.findElement(By.name("dtgLojas_header")).click();
		driver.findElement(By.name("btnSalvar")).click();
		driver.switchTo().alert().accept();
		
		 String reposta = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cadastro de Atacado'])[1]/following::td[4]")).getText();
		 Assert.assertTrue(reposta.contains("com sucesso"));
		
		driver.findElement(By.name("cmdOK")).click();
		
		Assert.assertEquals(atacado,String.valueOf(data.getUltimoAtacado()));
		driver.close();
	}

	
	
	
	
	// TESTE DE ALTERAR SENHA
	@Test
	public void testAlterarSenha() {

		WebDriver driver = LoginVisualStore();
		/*
		 * driver.findElement(By.id("e0_293i")).click();
		 * driver.switchTo().frame(0).findElement(By.name("txtSenha")).sendKeys(senha);
		 * driver.findElement(By.name("txtSenhaNova")).sendKeys("2");
		 * driver.findElement(By.name("txtSenhaConf")).sendKeys("2");
		 * driver.findElement(By.name("btnSalvar")).click();
		 * driver.switchTo().alert().accept();
		 * driver.findElement(By.name("cmdOK")).click();
		 * 
		 * //ERROR: Caught exception [ERROR: Unsupported command [selectFrame |
		 * relative=parent | ]] driver.findElement(By.id("e0_296i")).click();
		 * driver.get(
		 * "http://192.168.0.6:8091/vm_visualstore_adm/br/com/visualmix/generico/acesso/jsplogin/Event.jsp"
		 * ); driver.findElement(By.id("senha")).click();
		 * driver.findElement(By.id("senha")).clear();
		 * driver.findElement(By.id("senha")).sendKeys("2");
		 * driver.findElement(By.id("btnEnviar")).click();
		 * driver.findElement(By.id("senha")).click();
		 * driver.findElement(By.id("senha")).clear();
		 * driver.findElement(By.id("senha")).sendKeys("1");
		 * driver.findElement(By.id("lblMensagem")).click();
		 * driver.findElement(By.id("lblMensagem")).click(); //ERROR: Caught exception
		 * [ERROR: Unsupported command [doubleClick | id=lblMensagem | ]]
		 * driver.findElement(By.id("lblMensagem")).click();
		 * driver.findElement(By.xpath(
		 * "//div[@id='desktop']/form/table/tbody/tr/td[2]/center/table")).click();
		 * driver.findElement(By.id("senha")).click();
		 * driver.findElement(By.id("senha")).click(); //ERROR: Caught exception [ERROR:
		 * Unsupported command [doubleClick | id=senha | ]]
		 * driver.findElement(By.id("btnEnviar")).click();
		 * driver.get("http://192.168.0.6:8091/vm_visualstore_adm/sistema.jsp");
		 * 
		 * 
		 * driver.close();
		 */
	}


	// ***************************************
	// GERAÇÕES
	// ***************************************

	// 	GERAÃ‡ÃƒO DE CARGAS
	// ***************************************
	
	// TESTE GERAR CARGA DE PRODUTO
	@Test
	public void testGeraCargaProduto() throws InterruptedException {

		WebDriver driver = LoginVisualStore();

		driver.findElement(By.xpath("//*[@id=\"e0_149i\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"e0_151i\"]")).click();
		driver.switchTo().frame(0).findElement(By.name("cmbOpcao")).click();
		new Select(driver.findElement(By.name("cmbOpcao"))).selectByVisibleText("Gerar Produtos para o Dia Atual");
		driver.findElement(By.name("chk11000")).click();
		driver.findElement(By.name("btnGerar")).click();
		String teste = driver.findElement(By.xpath("//*[@id=\"dtgLista_line1\"]/td[4]/a")).getText();
		
        // Esperar 1 minuto
        Thread.sleep(60000);
 
        String descricao = data.getStatusProcessos( Integer.valueOf(teste));
        
		while( descricao == "") {
			  Thread.sleep(60000);
		}
		
		Assert.assertTrue(descricao.contains("gerado com Sucesso"));
		
		driver.close();
	}
	
	

	// TESTE GERAR CARGA DE PARAMETRO
	@Test
	public void testGeraCargaParametro() throws InterruptedException {
		
		WebDriver driver = LoginVisualStore();
		
		driver.findElement(By.id("e0_151i")).click();
		driver.findElement(By.id("e0_154i")).click();
		driver.switchTo().frame(0);
		driver.findElement(By.name("chk11000")).click();
		driver.findElement(By.name("cmbConverter")).click();
		driver.switchTo().alert().accept();

		
	    String teste = driver.findElement(By.xpath("//*[@id=\"dtgLista_line1\"]/td[4]/a")).getText();
		
        // Esperar 1 minuto
        Thread.sleep(60000);
 
        String descricao = data.getStatusProcessos( Integer.valueOf(teste));
        
		while( descricao == "") {
			  Thread.sleep(60000);
		}
		
		Assert.assertTrue(descricao.contains("realizado com Sucesso"));
		
		driver.close();
	}
	
	
	public void ConverterLV() {
		
		
	}
	
	// USO PARA LOGAR NO VS E FAZER OUTROS TESTE
	public WebDriver LoginVisualStore() {
		 WebDriver driver = null;
		
		try {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		//options.addArguments("--whitelisted-ips=*");
		
		System.setProperty("webdriver.chrome.whitelistedIps", "");
		System.setProperty("webdriver.chrome.driver",
			"D:\\PROJETOS\\testesVisualStore\\src\\test\\java\\driver\\chromedriver.exe");

		 driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("http://" + ip + ":8090/vm_visualstore_adm/");
		driver.findElement(By.id("usuarios")).sendKeys(usuario);
		driver.findElement(By.id("senha")).clear();
		driver.findElement(By.id("senha")).sendKeys(senha);
		driver.findElement(By.id("btnEnviar")).click();
		String teste = driver.findElement(By.id("framework")).getText();

		Assert.assertEquals(teste, "Visual Mix Framework");

		
		}catch (Exception e) {
			
			System.err.println("Eroo :"+ e);
		}
		return driver;
	}

}
