package basico;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import supporte.LerArquivo;
import supporte.Navegador;
import supporte.ReuseBrowser;
import supporte.VerificarElemento;
import tests.IniciarPorto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static tests.IniciarPorto.ARQRESUMO;


public class AbrirPorto {
    private static SessionId session_id;
    private static URL url;
    private WebDriver driver;
    private Logar login;


    public WebDriver verificarSessao() throws IOException, ExceptionPorto, InterruptedException {
        //capturar navegador

        File fileSessao = new File( "/home/robertinho/itauonline/status/sessionid.txt" );
        if (fileSessao.exists()) {
            try {
                System.out.println( "sessão existe" );
                session_id = LerArquivo.LerArquivoSessao( session_id );
                url = LerArquivo.LerArquivourl( url );
                driver = ReuseBrowser.createDriverFromSession( session_id, url );
                //driver.manage().window().maximize();
                //chamar o consultar
                //consultarOrcamento();
                System.out.println( "retornou da consulta" );
            } catch (Exception e) {
                this.abrir();
            }
        } else {
            System.out.println( "sessão não existe" );
            this.abrir();
            login.criarSessao( (ChromeDriver) driver );
        }

        return driver;
    }


    public WebDriver abrir() throws IOException, InterruptedException, ExceptionPorto {
        driver = Navegador.retornarNavegador();
        System.out.println( "abriu o navegador" );
        login = new Logar( driver );
        login.Logar();

        if (VerificarElemento.verificar( driver, "notificacoesURL", "ID", 120 )) {
            System.out.println( "executou o login" );
            Thread.sleep( 3000 );
            driver.findElement( By.id( "buorgig" ) ).click();
            Thread.sleep( 1000 );
            try {
                WebElement mensagem = driver.findElement( By.xpath( "//*[@id=\"modal-aviso\"]/a/span" ) );
                JavascriptExecutor jsColi = (JavascriptExecutor) driver;
                jsColi.executeScript( "arguments[0].click();", mensagem );

                WebDriverWait wait = new WebDriverWait( driver, 500 );
                //wait.until( ExpectedConditions.elementToBeClickable( By.id( "tutorialCloseBt" ) ) );
                System.out.println( "vai fechar 1" );
                Thread.sleep( 1000 );
                WebElement letrax = driver.findElement( By.id( "tutorialCloseBt" ) );

                jsColi.executeScript( "arguments[0].click();", letrax );
                Thread.sleep( 1000 );
                System.out.println( "vai fechar 2" );
                jsColi.executeScript( "arguments[0].click();", letrax );
                Thread.sleep( 2000 );
            } catch (Exception e) {
                WebElement mensagem = driver.findElement( By.xpath( "//*[@id=\"modal-aviso\"]/a/span" ) );
                JavascriptExecutor jsColi = (JavascriptExecutor) driver;
                jsColi.executeScript( "arguments[0].click();", mensagem );
                WebDriverWait wait = new WebDriverWait( driver, 500 );
                wait.until( ExpectedConditions.elementToBeClickable( By.id( "tutorialCloseBt" ) ) );
                System.out.println( "vai fechar 1" );
                Thread.sleep( 1000 );
                WebElement letrax = driver.findElement( By.id( "tutorialCloseBt" ) );
                jsColi = (JavascriptExecutor) driver;
                jsColi.executeScript( "arguments[0].click();", letrax );
                Thread.sleep( 1000 );
                System.out.println( "vai fechar 2" );
                jsColi.executeScript( "arguments[0].click();", letrax );
                Thread.sleep( 2000 );
            }


        }

        return driver;
    }


    public void menuPorto() throws InterruptedException, IOException {
        System.out.println( "iniciar processo para abrir o porto print web" );
        Thread.sleep( 3000 );
        WebDriverWait wait = new WebDriverWait( driver, 60 );
        wait.until( ExpectedConditions.elementToBeClickable( By.xpath( "/html/body/nav[2]/div/div[2]/a" ) ) );
        Thread.sleep( 1000 );


        driver.findElement( By.xpath( "/html/body/nav[2]/div/div[2]/a" ) ).click();
        Thread.sleep( 1000 );
        driver.findElement( By.xpath( "/html/body/nav[2]/div/div[2]/div/ul[1]/li[2]/a" ) ).click();
        Thread.sleep( 1000 );
        driver.findElement( By.xpath( "/html/body/nav[2]/div/div[2]/div/ul[1]/li[2]/div/ul/li[1]/a" ) ).click();
        Thread.sleep( 2000 );
        System.out.println( "nova aba" );
        //passando para nova aba
        List<String> abas = new ArrayList( driver.getWindowHandles() );
        driver.switchTo().window( abas.get( 1 ) );

        if (VerificarElemento.verificar( driver, "formIndex:TipoBuscaDetalhada", "ID", 30 )) {
            System.out.println( "abriu a janela para consultar o orçamento" );
            login.criarSessao( (ChromeDriver) driver );

        } else {
            System.out.println( "não abriu a janela de orçamento" );
        }

    }

    public void abrirCalculo() throws InterruptedException, IOException, ExceptionPorto {


        System.out.println( "vai testar se o botao localizar" );
        WebDriverWait wait = new WebDriverWait( driver, 500 );
        wait.until( ExpectedConditions.elementToBeClickable( By.id( "formIndex:numero" ) ) );
        Thread.sleep( 1000 );
        driver.findElement( By.xpath( "//*[@id=\"btnOrcamento\"]/a/div/div[1]" ) ).click();
        Thread.sleep( 1000 );
        driver.findElement( By.xpath( "//*[@id=\"formHeaderEndosso\"]/div[2]/div[1]/a/div/div" ) ).click();
        Thread.sleep( 1000 );

        System.out.println( "abriu o calculo consultado" );
        List<String> abas = new ArrayList( driver.getWindowHandles() );
        driver.switchTo().window( abas.get( 2 ) );

        if (VerificarElemento.verificar( driver, "checkbox-5", "ID", 30 )) {
            System.out.println( "abriu a janela novo calculo" );

        } else {
            System.out.println( "não abriu a janela de novo calculo" );
        }

    }
}

