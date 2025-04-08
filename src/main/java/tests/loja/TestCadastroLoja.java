package tests.loja;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import tests.BaseTestLogin;

public class TestCadastroLoja extends BaseTestLogin{

public String status;
	
	@Test
	public void testCadastroLojaValido() throws Exception {

		String loja = String.valueOf(data.getUltimoId("LOJAS") +1);

		try {
			
			
			setUp();
			LoginVisualStore();
			
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
	   
	        Thread.sleep(3000);
			String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
			Assert.assertEquals(reposta, "Os dados foram salvos com sucesso");

			//TODO VERIFICAR SE LOJA CADASTROU COM SUCESSO
			Thread.sleep(2500);
			Assert.assertEquals(loja,String.valueOf(data.getUltimoId("LOJAS")));
			status = "Sucesso!";
		} catch (NoSuchElementException e) {
			System.out.println("Erro: Elemento nao encontrado." + e.getMessage());
			status = "Erro: Elemento nao encontrado." + e.getMessage();
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
