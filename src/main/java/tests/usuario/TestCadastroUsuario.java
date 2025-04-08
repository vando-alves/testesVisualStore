package tests.usuario;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.maxicode.util.Funcoes;
import tests.BaseTestLogin;

public class TestCadastroUsuario  extends BaseTestLogin{
	
public String status;
	
	@Test
	public void testCadastroUsuarioValido() throws Exception {

		int loja = data.getUltimoId("LOJAS");
		String NiveisPDV = data.getNiveisPDV();
		String usuario = String.valueOf(data.getUltimoId("ACESSO_OPERADORES") +1);
		
		try {

			setUp();
			LoginVisualStore();
			
			driver.findElement(By.id("e0_0i")).click();
			driver.findElement(By.id("e0_2i")).click();
			driver.switchTo().frame(0).findElement(By.linkText("Incluir")).click();
			driver.findElement(By.name("cmbLoja")).click();
			new Select(driver.findElement(By.name("cmbLoja"))).selectByVisibleText(Funcoes.format(loja, "0000" ) +"-"+ data.getDescLoja(String.valueOf(loja)));
			Thread.sleep(2000);
			driver.findElement(By.name("txtLogin")).sendKeys("usuario_" + usuario);
			driver.findElement(By.name("txtIdentificacao")).sendKeys(usuario);
			driver.findElement(By.name("txtNome")).sendKeys("usuario regresao seleniun");
			driver.findElement(By.name("txtTelefone")).sendKeys("11968315270"); 
			driver.findElement(By.name("cmbSituacao")).click();
			new Select(driver.findElement(By.name("cmbSituacao"))).selectByVisibleText("0-Ativo");
			driver.findElement(By.name("txtEmail1")).sendKeys("vando.Jesus29@gmail.com");
			driver.findElement(By.name("txtEmail2")).sendKeys("vando.Jesus29@gmail.com");
			driver.findElement(By.name("txtEmail3")).sendKeys("vando.Jesus29@gmail.com");
			driver.findElement(By.name("txtSenha")).sendKeys("1");
			driver.findElement(By.name("txtSenhaConf")).sendKeys("1");

			driver.findElement(By.name("txtSenhaPdv")).sendKeys("1");
		    driver.findElement(By.name("txtSenhaPdvConf")).sendKeys("1");
			driver.findElement(By.name("cmbNivel")).click();
			new Select(driver.findElement(By.name("cmbNivel"))).selectByVisibleText(NiveisPDV);
			driver.findElement(By.name("txtCodigoIntegracao")).sendKeys(usuario);
			driver.findElement(By.name("btnSalvar")).click();
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
			String reposta = driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td")).getText();
			Assert.assertEquals(reposta, "Gravado com sucesso.");
			driver.findElement(By.name("cmdOK")).click();
			
			// ABA PERMISSOES
			driver.findElement(By.id("lnktabtabTela1")).click();
			driver.findElement(By.name("dtgPermissao_header")).click();
			driver.findElement(By.xpath("//div[@id='tabtabTela1']/table/tbody/tr[4]/td/input")).click();
			driver.switchTo().alert().accept();
			driver.findElement(By.name("cmdOK")).click();

			// ABA LOJA
			driver.findElement(By.id("lnktabtabTela2")).click();
			driver.findElement(By.name("dtgLojas_header")).click();
			driver.findElement(By.xpath("//div[@id='tabtabTela2']/table/tbody/tr[4]/td/input")).click();
			driver.switchTo().alert().accept();
			driver.findElement(By.name("cmdOK")).click();
			
			status = "Sucesso!";
		} catch (NoSuchElementException e) {
			System.out.println("Erro: Elemento nao encontrado." + e.getMessage());
			status = "Erro: Elemento nao encontrado." + e.getMessage();
		} catch (AssertionError e) {
			System.out.println("O teste falhou em uma asserção: " + e.getMessage());
			status = "O teste falhou em uma assercao: " + e.getMessage();
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
			status = "Erro inesperado: " + e.getMessage();
		} finally {
			driver.close(); // Fecha o driver, independentemente do resultado
		}
	}

}
