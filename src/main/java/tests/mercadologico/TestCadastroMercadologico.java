package tests.mercadologico;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;

import tests.BaseTestLogin;

public class TestCadastroMercadologico  extends BaseTestLogin{

	public String status;
	
	@Test
	public void testCadastroMercadologicoValido() throws Exception {

		String mercadologico = String.valueOf(data.getCodUltimoIdMercadologico() +1);
		
		try {

			setUp();
			LoginVisualStore();
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
			Assert.assertEquals(mercadologico,String.valueOf(data.getCodUltimoIdMercadologico()));
			status = "Sucesso!";
		} catch (NoSuchElementException e) {
			System.out.println("Erro: Elemento nao encontrado."+ e.getMessage());
			status = "Erro: Elemento nao encontrado."+ e.getMessage();
		} catch (AssertionError e) {
			System.out.println("O teste falhou em uma assercao: " + e.getMessage());
			status = "O teste falhou em uma assercao: " + e.getMessage();
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
			status = "Erro inesperado: " + e.getMessage();
		} finally {
			driver.close(); // Fecha o driver, independentemente do resultado
		}
	}
	
	
}
