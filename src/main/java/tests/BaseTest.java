package tests;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

	protected WebDriver driver;
	
	  @Before
	    public void setUp() {
		  
			try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			//options.addArguments("--whitelisted-ips=*");
			
			System.setProperty("webdriver.chrome.whitelistedIps", "");
			System.setProperty("webdriver.chrome.driver",
				"D:\\PROJETOS\\testesVisualStore\\src\\test\\java\\driver\\chromedriver.exe");

			driver = new ChromeDriver();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.get("http://127.0.0.1:8090/vm_visualstore_adm/");
			
			}catch (Exception e) {
				System.err.println("Erro de conexao do  ChromeDriver(): "+ e);
			}
	    }
	  
	  @After
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	
}
