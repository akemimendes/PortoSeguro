package basico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import supporte.ArquivoSessao_ID;
import supporte.ArquivoURL;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class Logar {

    private WebDriverWait wait;
    private WebDriver driver;

    public Logar(WebDriver driver) {
        this.driver = driver;

    }


    public void Logar() throws InterruptedException, IOException,ExceptionPorto {
        File file = new File("/home/robertinho/itauonline/n_cotacao/senha.txt");
        if (file.exists()) {
            Scanner ler = new Scanner("/home/robertinho/itauonline/n_cotacao/senha.txt");
            String arquivo = ler.nextLine();
            FileReader arq = new FileReader(arquivo);
            BufferedReader lerArq = new BufferedReader(arq);
            String usuario = lerArq.readLine();
            String senha = lerArq.readLine();
            String susep = lerArq.readLine();
            Thread.sleep(3000);
            System.out.println("cookies");
            WebDriverWait wait = new WebDriverWait( driver,50);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler")));
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();
            System.out.println("Clicar em corretor para iniciar o login");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("entrar")));
            driver.findElement(By.name("entrar")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logonPrincipal")));
            for(int i=0;i<usuario.length();){
                char c =usuario.charAt(i);
                String s = new StringBuilder().append(c).toString();
                driver.findElement(By.id("logonPrincipal")).sendKeys(s);
                i++;
            }
            Thread.sleep(1000);
            driver.findElement(By.name("password")).sendKeys(senha);
            Thread.sleep(1000);
            driver.findElement(By.id("inputLogin")).click();
            Thread.sleep(5000);
        }else{
            throw new ExceptionPorto("Erro: Arquivo senha não existe");
        }
    }




    public void criarSessao(ChromeDriver driver) throws IOException {
        //gravar sessao
        HttpCommandExecutor executor = (HttpCommandExecutor) driver.getCommandExecutor();
        URL url = executor.getAddressOfRemoteServer();
        SessionId session_id = driver.getSessionId();
        url = ArquivoURL.CriarUrL(url);
        session_id = ArquivoSessao_ID.Criarsessao_id(session_id);
    }
}
