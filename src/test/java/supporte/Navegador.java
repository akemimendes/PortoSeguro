package supporte;

import basico.Logar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Navegador {

    public static WebDriver retornarNavegador() throws IOException, InterruptedException {

        ChromeDriver driver=null;


        System.setProperty("webdriver.chrome.driver", "/home/robertinho/drivers/chromedriver");

        File file1 = new File("/home/robertinho/itauonline/status/logar.txt");

        if (file1.exists() && file1.isFile()) {
            System.out.println("iniciar o abrir");
            driver = new ChromeDriver();

            driver.get("https://corretor.portoseguro.com.br/corretoronline/");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        }
        return driver;
    }


}
