package testesRegressao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class RegressaoEvidencias {
	
	
	public static void printar(WebDriver driver) {
	
        // Captura uma screenshot da página
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Salva a screenshot em um arquivo
            Files.copy(screenshotFile.toPath(), new File("C:/Users/NB-VANDO.ALVES/Music/screenshot.png").toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}
