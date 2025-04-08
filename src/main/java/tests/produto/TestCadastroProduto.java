package tests.produto;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import br.com.maxicode.util.Funcoes;
import tests.BaseTestLogin;

public class TestCadastroProduto extends BaseTestLogin{

public String status;
	
	@Test
	public void testCadastroProdutoValido() throws Exception {

		String produto = String.valueOf(data.getUltimoIdProduto() +1);
		
		try {

			setUp();
			LoginVisualStore();

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
			new Select(driver.findElement(By.name("cmbMercadoLogico1"))).selectByIndex(1);
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
			status = "Sucesso!";
		} catch (NoSuchElementException e) {
			System.out.println("Erro: Elemento nao encontrado."+ e.getMessage());
			status = "Erro: Elemento nao encontrado."+ e.getMessage();
		} catch (AssertionError e) {
			System.out.println("O teste falhou em uma assercao: " + e.getMessage());
			status = "O teste falhou em uma assercao: " + e.getMessage();
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
			status ="Erro inesperado: " + e.getMessage();
		} finally {
			driver.close(); // Fecha o driver, independentemente do resultado
		}
	}
	
	
	public void testCadastroProdutoKitValido() throws Exception {

		String produto = String.valueOf(data.getUltimoIdProduto() +1);
		
		try {

			setUp();
			LoginVisualStore();

			driver.findElement(By.id("e0_5i")).click();
		    driver.findElement(By.id("e0_6o")).click();
			driver.findElement(By.id("e0_7i")).click();
			driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
			driver.findElement(By.name("txtProdutoId")).clear();
			driver.findElement(By.name("txtProdutoId")).sendKeys(produto);
			driver.findElement(By.name("txtDescricaoCompleta")).sendKeys("produto kit");
			driver.findElement(By.name("txtDescricaoReduzida")).sendKeys("produto kit");
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
			status = "Sucesso!";
		} catch (NoSuchElementException e) {
			System.out.println("Erro: Elemento nao encontrado."+ e.getMessage());
			status = "Erro: Elemento nao encontrado."+ e.getMessage();
		} catch (AssertionError e) {
			System.out.println("O teste falhou em uma assercao: " + e.getMessage());
			status = "O teste falhou em uma assercao: " + e.getMessage();
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
			status ="Erro inesperado: " + e.getMessage();
		} finally {
			driver.close(); // Fecha o driver, independentemente do resultado
		}
	}
	
}
