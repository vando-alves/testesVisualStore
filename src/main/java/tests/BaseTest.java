package tests;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import dao.ConfigDAO;
import io.github.bonigarcia.wdm.WebDriverManager;
import model.ConfigDTO;

public class BaseTest {

	protected WebDriver driver;
	protected ConfigDTO configDTO;
	
	  @Before
	    public void setUp() {
		  
			try {
		
		    configDTO = ConfigDAO.getConfig();
			
			// baixa e configura automaticamente o ChromeDriver
		    WebDriverManager.chromedriver().setup();
		    
		    
			ChromeOptions options = new ChromeOptions();
			

		    //System.setProperty("webdriver.chrome.whitelistedIps", "");
			//System.setProperty("webdriver.chrome.driver", configDTO.getCaminhoDriver());

			
			if(configDTO.getExibe_teste() == 0) {
				options.addArguments("--headless"); // Ativa o modo headless
			}
            
            options.addArguments("--disable-gpu"); // Melhora compatibilidade em alguns sistemas
            //options.addArguments("--window-size=1920,1080"); // Define um tamanho de tela padrão
            options.addArguments("--remote-allow-origins=*"); // Corrige alguns erros de conexão

			//System.setProperty("webdriver.chrome.whitelistedIps", "");
			//System.setProperty("webdriver.chrome.driver","D:\\PROJETOS\\testesVisualStore\\src\\test\\java\\driver\\chromedriver.exe");

			driver = new ChromeDriver();
	
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.get("http://"+configDTO.getIp()+":"+configDTO.getPorta()+"/vm_visualstore_adm/");
			
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
