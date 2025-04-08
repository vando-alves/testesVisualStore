package tests.kit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import tests.BaseTestLogin;

public class TestCadastrokit extends BaseTestLogin {

	public String status;
	
	@Test
	public void testCadastroKitValido() throws Exception {

		
		
		
		String loja = String.valueOf(data.getUltimoId("LOJAS"));
		int ultimoProduto = data.getUltimoIdProduto();
		String produto = String.valueOf(ultimoProduto - 1);
		String produtoKit = String.valueOf(ultimoProduto);
		String fornecedor = String.valueOf(data.getUltimoId("FORNECEDORES"));
		
		try {

			setUp();
			LoginVisualStore();

			// INCLUIR KIT
			driver.findElement(By.id("e0_5i")).click();
			driver.findElement(By.id("e0_6i")).click();
			driver.findElement(By.id("e0_10i")).click();
			driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
			driver.findElement(By.name("txtCodigo")).clear();
			driver.findElement(By.name("txtCodigo")).sendKeys(produtoKit);
			driver.findElement(By.name("txtLoja")).clear();
			driver.findElement(By.name("txtLoja")).sendKeys(loja);
			driver.findElement(By.name("txtProdutoId")).clear();
			driver.findElement(By.name("txtProdutoId")).sendKeys(produto);
			driver.findElement(By.name("cmbEmbalagem")).click();
			driver.findElement(By.name("cmbTipoPreco")).click();
			new Select(driver.findElement(By.name("cmbTipoPreco"))).selectByIndex(1);
			driver.findElement(By.name("txtPrecoVenda")).clear();
			driver.findElement(By.name("txtPrecoVenda")).sendKeys("1");
			driver.findElement(By.name("txtFatorVenda")).clear();
			driver.findElement(By.name("txtFatorVenda")).sendKeys("1");
			driver.findElement(By.name("btnSalvar")).click();
			driver.switchTo().alert().accept();
			driver.findElement(By.name("cmdOK")).click();
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
