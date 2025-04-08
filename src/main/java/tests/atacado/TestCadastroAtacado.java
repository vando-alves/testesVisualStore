package tests.atacado;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;

import tests.BaseTestLogin;

public class TestCadastroAtacado extends BaseTestLogin{
	
	
public String status;
	
	@Test
	public void testCadastroAtacadoValido() throws Exception {

		try {

			setUp();
			LoginVisualStore();
			
			driver.findElement(By.id("e0_5i")).click();
		    driver.findElement(By.id("e0_48i")).click();
			    driver.findElement(By.id("e0_55i")).click();
			driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
			driver.findElement(By.name("txtProdutoId")).sendKeys(String.valueOf(data.getUltimoIdProduto()));
			driver.findElement(By.name("cmbSeqEmbalagem")).click();
			driver.findElement(By.name("cmbTipo")).click();
			new Select(driver.findElement(By.name("cmbTipo"))).selectByIndex(1);
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
			
			 String reposta = driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
			 Assert.assertTrue(reposta.contains("com sucesso"));
			
			driver.findElement(By.name("cmdOK")).click();
			
			Assert.assertEquals(atacado,String.valueOf(data.getUltimoAtacado()));
			
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
