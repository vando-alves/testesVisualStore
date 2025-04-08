package tests.cuponagem;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import tests.BaseTestLogin;

public class TestCadastroCuponagem extends BaseTestLogin{
	
public String status;
	
	@Test
	public void testCadastroCuponagemValido() throws Exception {

		String loja = String.valueOf(data.getUltimoId("LOJAS"));
		String cuponagem = String.valueOf(data.getUltimoId("CUPOM") +1);

		try {

			setUp();
			LoginVisualStore();

		    driver.findElement(By.id("e0_5i")).click();
		    driver.findElement(By.id("e0_48i")).click();
		    driver.findElement(By.id("e0_54i")).click();
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
			
			Thread.sleep(2000);
			String reposta =driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
			Assert.assertTrue(reposta.contains("Salvo com sucesso"));
			
			driver.findElement(By.name("cmdOK")).click();
			
			Assert.assertEquals(cuponagem,String.valueOf(data.getUltimoId("CUPOM")));
			
			driver.findElement(By.id("lnktabtabTela3")).click();
			driver.findElement(By.name("txtLojas")).sendKeys(loja);
			driver.findElement(By.name("btnSalvarLojas")).click();
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
