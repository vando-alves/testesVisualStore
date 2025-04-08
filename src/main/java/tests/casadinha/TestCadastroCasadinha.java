package tests.casadinha;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import tests.BaseTestLogin;

public class TestCadastroCasadinha extends BaseTestLogin {
	
public String status;
	
	@Test
	public void testCadastroCasadinhaValido() throws Exception {

		String loja = String.valueOf(data.getUltimoId("LOJAS"));
		String casadinha = String.valueOf(data.getUltimoId("CASADINHA") +1);
		String produto =  String.valueOf(data.getUltimoIdProduto());

		try {

			setUp();
			LoginVisualStore();

			 driver.findElement(By.id("e0_5i")).click();
			    driver.findElement(By.id("e0_48i")).click();
			    driver.findElement(By.id("e0_49i")).click();
				driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
				driver.findElement(By.name("txtDescricao")).sendKeys("Casadinha regresao seleniun " + casadinha);
				driver.findElement(By.name("txtCuponagem")).sendKeys("0");
				driver.findElement(By.name("btnSalvar")).click();
				driver.switchTo().alert().accept();
				driver.findElement(By.name("cmdOK")).click();

				driver.findElement(By.id("lnktabtabTela1")).click();
				driver.findElement(By.name("pesProdutoOrigem0000000000")).sendKeys(produto);
				driver.findElement(By.name("pesProdutoOrigem0000000000")).sendKeys(Keys.TAB);
				Thread.sleep(3000);
				driver.findElement(By.name("cmbEmabalagemOrigem1")).click();
				new Select(driver.findElement(By.name("cmbEmabalagemOrigem1"))).selectByIndex(1);
				driver.findElement(By.name("quantidadeOrigem2")).clear();
				driver.findElement(By.name("quantidadeOrigem2")).click();
				driver.findElement(By.name("quantidadeOrigem2")).sendKeys("1");
				driver.findElement(By.name("btnAdd6")).click();
				Thread.sleep(3000);
				driver.findElement(By.name("btnSalvarOrigem")).click();
				driver.switchTo().alert().accept();
				driver.findElement(By.name("cmdOK")).click();

				driver.findElement(By.id("lnktabtabTela2")).click();
				driver.findElement(By.name("cmbTipoDescontoPromovido")).click();
				new Select(driver.findElement(By.name("cmbTipoDescontoPromovido"))).selectByIndex(1);
				driver.findElement(By.name("txtValorPromovido")).click();
				driver.findElement(By.name("txtValorPromovido")).clear();
				driver.findElement(By.name("txtValorPromovido")).sendKeys("1");
				driver.findElement(By.name("txtQuantidadePromovido")).click();
				driver.findElement(By.name("txtQuantidadePromovido")).clear();
				driver.findElement(By.name("txtQuantidadePromovido")).sendKeys("1");
				driver.findElement(By.name("pesProduto0000000000")).sendKeys(produto);
				driver.findElement(By.name("pesProduto0000000000")).sendKeys(Keys.TAB);
				Thread.sleep(3000);
				driver.findElement(By.name("cmbEmbalegmPromovido1")).click();
				new Select(driver.findElement(By.name("cmbEmbalegmPromovido1"))).selectByIndex(1);
				driver.findElement(By.name("cmbTipoDescontoProvido2")).click();
				new Select(driver.findElement(By.name("cmbTipoDescontoProvido2"))).selectByIndex(1);

				driver.findElement(By.name("txtValorPromovido3")).click();

				driver.findElement(By.name("txtValorPromovido3")).clear();
				driver.findElement(By.name("txtValorPromovido3")).sendKeys("1");
				driver.findElement(By.name("btnAdd5")).click();
				Thread.sleep(3000);
				driver.findElement(By.name("btnSalvarPromovido")).click();
				driver.switchTo().alert().accept();
				driver.findElement(By.name("cmdOK")).click();
				
				driver.findElement(By.id("lnktabtabTela3")).click();
				driver.findElement(By.name("txtCodigoBin")).click();
				driver.findElement(By.name("txtCodigoBin")).clear();
				driver.findElement(By.name("txtCodigoBin")).sendKeys("1234");
				driver.findElement(By.xpath("//div[@id='tabtabTela3']/table/tbody/tr[7]/td/input")).click();
				driver.findElement(By.name("cmdOK")).click();

				driver.findElement(By.id("lnktabtabTela7")).click();
				driver.findElement(By.name("dtgLojas_header")).click();
				driver.findElement(By.xpath("//div[@id='tabtabTela7']/table/tbody/tr[4]/td/input")).click();
				driver.switchTo().alert().accept();

				String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
				Assert.assertTrue(reposta.contains("salvos com sucesso"));
				
				driver.findElement(By.name("cmdOK")).click();
				
				Assert.assertEquals(casadinha,String.valueOf(data.getUltimoId("CASADINHA")));
			
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
