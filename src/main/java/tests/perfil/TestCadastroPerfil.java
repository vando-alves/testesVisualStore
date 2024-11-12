package tests.perfil;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import tests.BaseTestLogin;

public class TestCadastroPerfil extends BaseTestLogin {

	public String status;

	@Test
	public void testCadastroPerfilValido() throws Exception {

		try {
			
			setUp();
			LoginVisualStore();
			
			driver.findElement(By.id("e0_0i")).click();
			driver.findElement(By.id("e0_1i")).click();
			driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
			driver.findElement(By.name("txtDescricao")).sendKeys("Regressao selenium");
			driver.findElement(By.name("btnSalvar")).click();
			driver.switchTo().alert().accept();

			String reposta = driver
					.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();

			Assert.assertTrue(reposta.contains("com sucesso"));
			driver.findElement(By.name("cmdOK")).click();

			String codigo = driver.findElement(By.name("txtCodigo")).getAttribute("value");
			Thread.sleep(2500);
			Assert.assertEquals(codigo, String.valueOf(data.getUltimoId("ACESSO_PERFIL")));

			driver.findElement(By.id("lnktabtabTela1")).click();
			driver.findElement(By.name("dtgPermissao_header")).click();
			driver.findElement(By.id("lnktabtabTela2")).click();

			driver.findElement(By.xpath("//div[@id='tabtabTela2']/table/tbody/tr/td/div/table/tbody/tr/td")).click();
			driver.findElement(By.name("btnSalvar")).click();
			driver.switchTo().alert().accept();
			driver.findElement(By.name("cmdOK")).click();
			status = "Sucesso!";
		} catch (NoSuchElementException e) {
			System.out.println("Erro: Elemento não encontrado.");
			status = "Falha!";
		} catch (AssertionError e) {
			System.out.println("O teste falhou em uma asserção: " + e.getMessage());
			status = "Falha!";
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
			status = "Falha!";
		} finally {
			driver.close(); // Fecha o driver, independentemente do resultado
		}

	}

}
