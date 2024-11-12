package tests.fornecedor;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import tests.BaseTestLogin;

public class TestCadastroFornecedor extends BaseTestLogin {

	public String status;
	
	@Test
	public void testCadastroFornecedorValido() throws Exception {

		String fornecedor = String.valueOf(data.getUltimoId("FORNECEDORES") + 1);

		try {

			setUp();
			LoginVisualStore();
			
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
			String reposta = driver
					.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
			Assert.assertEquals(reposta, "Os dados foram salvos com sucesso");
			driver.findElement(By.name("cmdOK")).click();
			Thread.sleep(2500);
			Assert.assertEquals(fornecedor, String.valueOf(data.getUltimoId("FORNECEDORES")));
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
