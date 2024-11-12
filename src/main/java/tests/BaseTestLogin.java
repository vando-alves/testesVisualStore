package tests;

import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;

public class BaseTestLogin extends BaseTest{

	protected  RegressaoDAL data = new RegressaoDAL();

	
	 @Before
	public void LoginVisualStore() {
		
		driver.findElement(By.id("usuarios")).sendKeys("visualmix");
		driver.findElement(By.id("senha")).clear();
		driver.findElement(By.id("senha")).sendKeys("1");
		driver.findElement(By.id("btnEnviar")).click();
		String teste = driver.findElement(By.id("framework")).getText();

		Assert.assertEquals(teste, "Visual Mix Framework");
	}
	
}
