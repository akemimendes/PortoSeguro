package tests;

import basico.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import supporte.Pdf;
import supporte.VerificarElemento;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class IniciarPorto {

    private WebDriver driver;
    public static Resumo ARQRESUMO = Resumo.getInstance();


    @Test
    public void ativarPorto() throws IOException, InterruptedException, ExceptionPorto {


        //instanciando classes que serão utilizadas
        NrArquivo nrlinha = new NrArquivo();
        LerArquivoPorto arquivo = new LerArquivoPorto();
        AbrirPorto abrirNavegador = new AbrirPorto();

        DadosSeguro telaCliente = new DadosSeguro();
        AceitaCobertura telaVeiculo = new AceitaCobertura();
        Pdf trataPDF = new Pdf();

        //fim instanciando


        //chamar o abrir que retornar o navegador com a consulta do calculo realizada
        driver = abrirNavegador.abrir();
        abrirNavegador.menuPorto();
        abrirNavegador.abrirCalculo();

        //carregar numlinha dos arquivos na pasta
        ArrayList<String> listaNr = nrlinha.carregarArquivos();
        System.out.println( "carregou lista com numero dos arquivos" );
        if (listaNr.size() == 0) {
            JOptionPane.showMessageDialog( null, "Acabou todos os arquivos!" );
        }

        while (listaNr.size() > 0) {

            try {
                //deletar arquivos na pasta dwonload
                trataPDF.deletarArquivosDownload();

                //setar no objeto as informações contida no arquivo
                Variaveis variaveis = arquivo.lerArquivoNr( listaNr.get( 0 ) );
                variaveis.setNrlinha( listaNr.get( 0 ) );
                System.out.println( "Nlinha=" + variaveis.getNrlinha() );

                telaCliente.preencherDadosSeguro( driver, variaveis );
                telaVeiculo.verificaAceita( driver, variaveis );

                 //abrir um novo calculo
                //cancelar janela pra continuar no prox calc
                driver.close();
                List<String> abas = new ArrayList( driver.getWindowHandles() );
                driver.switchTo().window( abas.get( 1 ) );
                WebDriverWait wait = new WebDriverWait( driver, 60 );
                wait.until( ExpectedConditions.elementToBeClickable( By.xpath( "/html/body/div[13]/div/div[3]/div/div/div[2]/form/div[1]/a" ) ) );
                Thread.sleep( 1000 );
                driver.findElement( By.xpath( "/html/body/div[13]/div/div[3]/div/div/div[2]/form/div[1]/a" ) ).click();
                Thread.sleep( 1000 );
                driver.findElement( By.xpath( "/html/body/div[13]/div/div[3]/div/div/div[2]/form/div[2]/div[1]/a" ) ).click();
                Thread.sleep( 2000 );
                System.out.println( "nova aba" );
                //passando para nova aba
                abas = new ArrayList( driver.getWindowHandles() );
                driver.switchTo().window( abas.get( 2 ) );

                if (VerificarElemento.verificar( driver, "formIndex:TipoBuscaDetalhada", "ID", 30 )) {
                    System.out.println( "abriu a janela para consultar o orçamento" );


                }
                //mover arquivo para prontos
                nrlinha.deleteArquivo( listaNr.get( 0 ) );

                //carregar novamente os arquivos da pasta
                listaNr = nrlinha.carregarArquivos();
                if (listaNr.size() == 0) {
                    JOptionPane.showMessageDialog( null, "Acabou todos os arquivos!" );
                }

            } catch (Exception e) {
                driver.close();
                List<String> abas = new ArrayList( driver.getWindowHandles() );
                driver.switchTo().window( abas.get( 1 ) );
                WebDriverWait wait = new WebDriverWait( driver, 60 );
                wait.until( ExpectedConditions.elementToBeClickable( By.xpath( "/html/body/div[13]/div/div[3]/div/div/div[2]/form/div[1]/a" ) ) );
                Thread.sleep( 1000 );
                driver.findElement( By.xpath( "/html/body/div[13]/div/div[3]/div/div/div[2]/form/div[1]/a" ) ).click();
                Thread.sleep( 1000 );
                driver.findElement( By.xpath( "/html/body/div[13]/div/div[3]/div/div/div[2]/form/div[2]/div[1]/a" ) ).click();
                Thread.sleep( 2000 );
                System.out.println( "nova aba" );
                abas = new ArrayList( driver.getWindowHandles() );
                driver.switchTo().window( abas.get( 2 ) );
                System.out.println( e.getMessage() );
                //driver = abrirNavegador.verificarSessao();
            } finally {
                continue;
            }
        }
    }
}
