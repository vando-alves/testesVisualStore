package tests;

import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;

import dao.ConfigDAO;
import model.ConfigDTO;

public class BaseTestLogin extends BaseTest{

	protected  RegressaoDAL data = new RegressaoDAL();

	
	 @Before
	public void LoginVisualStore() {
		driver.findElement(By.id("usuarios")).sendKeys(configDTO.getUsuario());
		driver.findElement(By.id("senha")).clear();
		driver.findElement(By.id("senha")).sendKeys(configDTO.getSenha());
		driver.findElement(By.id("btnEnviar")).click();
		String teste = driver.findElement(By.id("framework")).getText();

		Assert.assertEquals(teste, "Visual Mix Framework");
	}
	
}
