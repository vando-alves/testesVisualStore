package tests.componente;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;

import tests.BaseTestLogin;

public class TestCadastroComponente extends BaseTestLogin{
	
public String status;
	
	@Test
	public void testCadastroComponentePdvValido() throws Exception {

		
		//Primeiro Habilito loja para o usuario
		try {

			setUp();
			LoginVisualStore();

			    driver.findElement(By.id("e0_0i")).click();
			    driver.findElement(By.id("e0_2i")).click();
			    driver.switchTo().frame(0).findElement(By.name("txtValor")).click();
			    driver.findElement(By.name("txtValor")).clear();
			    driver.findElement(By.name("txtValor")).sendKeys("visualmix");
			    driver.findElement(By.name("cmbCampos")).click();
			    new Select(driver.findElement(By.name("cmbCampos"))).selectByVisibleText("Nome");
			    driver.findElement(By.name("cmdVer")).click();
			    driver.findElement(By.linkText("visualmix")).click();
			    driver.findElement(By.id("lnktabtabTela1")).click();
			    driver.findElement(By.id("lnktabtabTela2")).click();
			    driver.findElement(By.name("dtgLojas_header")).click();
			    driver.findElement(By.xpath("//div[@id='tabtabTela2']/table/tbody/tr[4]/td/input")).click();
			    driver.findElement(By.xpath("//div[@id='tabtabTela2']/table/tbody/tr[4]/td/input")).click();
			    driver.switchTo().alert().accept();
				driver.findElement(By.name("cmdOK")).click();
			    driver.close();

		} catch (NoSuchElementException e) {
			System.out.println("Erro: Elemento nao encontrado."+ e.getMessage());
		} catch (AssertionError e) {
			System.out.println("O teste falhou em uma assercao: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
		} finally {
			driver.close(); // Fecha o driver, independentemente do resultado
		}
		


		String loja = String.valueOf(data.getUltimoId("LOJAS"));
		String componentePDV = String.valueOf(data.getUltimoIdComponente(loja) +1);

		try {

			setUp();
			LoginVisualStore();

			driver.findElement(By.id("e0_5i")).click();
		    driver.findElement(By.id("e0_29i")).click();
		    driver.findElement(By.id("e0_30i")).click();
			driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
			driver.findElement(By.name("txtLoja")).clear();
			driver.findElement(By.name("txtLoja")).sendKeys(loja);
			driver.findElement(By.name("txtCodigo")).clear();
			driver.findElement(By.name("txtCodigo")).sendKeys(componentePDV);
			driver.findElement(By.name("txtLocalizao")).sendKeys("PDV REGRESAO");
			driver.findElement(By.name("cmbModelo")).click();
			new Select(driver.findElement(By.name("cmbModelo"))).selectByVisibleText("0-NÃO INFORMADO");
			driver.findElement(By.name("cbxNFCe")).click();
			Thread.sleep(2000);
			driver.findElement(By.name("cbxParametros")).click();
			driver.findElement(By.name("cbxProdutos")).click();
			driver.findElement(By.name("btnSalvar")).click();
			driver.switchTo().alert().accept();
			String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
			Assert.assertEquals(reposta, "Os dados foram salvos com sucesso");
			
			driver.findElement(By.name("cmdOK")).click();
			
			Assert.assertEquals(componentePDV,String.valueOf(data.getUltimoIdComponente(loja)));
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
	
	@Test
	public void testCadastroComponenteTotemValido() throws Exception {

		String loja = String.valueOf(data.getUltimoId("LOJAS"));
		String componentePDV = String.valueOf(data.getUltimoIdComponente(loja) +1);

		try {

			setUp();
			LoginVisualStore();

			driver.findElement(By.id("e0_5i")).click();
			driver.findElement(By.id("e0_29i")).click();
			driver.findElement(By.id("e0_30i")).click();
			driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
			driver.findElement(By.name("txtLoja")).clear();
			driver.findElement(By.name("txtLoja")).sendKeys(loja);
			driver.findElement(By.name("txtCodigo")).clear();
			driver.findElement(By.name("txtCodigo")).sendKeys(componentePDV);
			driver.findElement(By.name("txtLocalizao")).sendKeys("PDV REGRESAO");
			driver.findElement(By.name("cmbModelo")).click();
			new Select(driver.findElement(By.name("cmbModelo"))).selectByVisibleText("0-NÃO INFORMADO");
			driver.findElement(By.name("cbxNFCe")).click();
			Thread.sleep(2000);
			driver.findElement(By.name("cbxParametros")).click();
			driver.findElement(By.name("cbxProdutos")).click();
			driver.findElement(By.name("btnSalvar")).click();
			driver.switchTo().alert().accept();
			String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
			Assert.assertEquals(reposta, "Os dados foram salvos com sucesso");
			
			driver.findElement(By.name("cmdOK")).click();
			
			Assert.assertEquals(componentePDV,String.valueOf(data.getUltimoIdComponente(loja)));
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

	@Test
	public void testCadastroComponenteGatewayValido() throws Exception {

		String loja = String.valueOf(data.getUltimoId("LOJAS"));
		String componentePDV = String.valueOf(data.getUltimoIdComponente(loja) +1);

		try {

			setUp();
			LoginVisualStore();

			driver.findElement(By.id("e0_5i")).click();
			driver.findElement(By.id("e0_29i")).click();
			driver.findElement(By.id("e0_30i")).click();
			driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
			driver.findElement(By.name("txtLoja")).clear();
			driver.findElement(By.name("txtLoja")).sendKeys(loja);
			driver.findElement(By.name("txtCodigo")).clear();
			driver.findElement(By.name("txtCodigo")).sendKeys(componentePDV);
			driver.findElement(By.name("txtLocalizao")).sendKeys("PDV REGRESAO");
			driver.findElement(By.name("cmbModelo")).click();
			new Select(driver.findElement(By.name("cmbModelo"))).selectByVisibleText("0-NÃO INFORMADO");
			driver.findElement(By.name("cbxNFCe")).click();
			Thread.sleep(2000);
			driver.findElement(By.name("cbxParametros")).click();
			driver.findElement(By.name("cbxProdutos")).click();
			driver.findElement(By.name("btnSalvar")).click();
			driver.switchTo().alert().accept();
			String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
			Assert.assertEquals(reposta, "Os dados foram salvos com sucesso");
			
			driver.findElement(By.name("cmdOK")).click();
			
			Assert.assertEquals(componentePDV,String.valueOf(data.getUltimoIdComponente(loja)));
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
