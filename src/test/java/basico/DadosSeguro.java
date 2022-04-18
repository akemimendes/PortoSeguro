package basico;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import supporte.VerificarElemento;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import static tests.IniciarPorto.ARQRESUMO;


public class DadosSeguro {


    public void preencherDadosSeguro(WebDriver driver, Variaveis dados) throws InterruptedException, AWTException {
        try {

            System.out.println( "inciar o preenchimento da tela cliente" );
            WebDriverWait wait = new WebDriverWait( driver, 100 );
            Robot r = new Robot();
            wait.until( ExpectedConditions.visibilityOfElementLocated( By.xpath( "//*[contains(text(), 'Dados do Segurado')]" ) ) );
            if (!dados.getClasseBonus().contains( "0" )) {
                Thread.sleep( 2000 );
                driver.findElement( By.name( "tipo-seguro-6" ) ).click();
                Thread.sleep( 1000 );
                driver.findElement( By.id( "btn-lbl-select-bonus" ) ).click();
                int bonus = Integer.parseInt( dados.getClasseBonus() );
                r.keyPress( KeyEvent.VK_DOWN );
                r.keyRelease( KeyEvent.VK_DOWN );
                Thread.sleep( 1000 );
                for (int i = 0; i < bonus; i++) {
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                }
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_TAB );
                r.keyRelease( KeyEvent.VK_TAB );
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_ENTER );
                r.keyRelease( KeyEvent.VK_ENTER );
                Thread.sleep( 1000 );
                driver.findElement( By.id( "btn-lbl-congenere" ) ).click();
                driver.findElement( By.id( "congenere-filter" ) ).sendKeys( dados.getCiaAnterior() );
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_TAB );
                r.keyRelease( KeyEvent.VK_TAB );
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_ENTER );
                r.keyRelease( KeyEvent.VK_ENTER );
                Thread.sleep( 1000 );

            }

            driver.findElement( By.id( "ipt-cpfCnpj-segurado" ) ).sendKeys( dados.getCpfSegur() );
            Thread.sleep( 3000 );
            try {
                driver.findElement( By.xpath( "//*[@id=\"btn-lbl-select-sexo-novo-segurado\"]/span" ) ).click();
                if (dados.getSexoCondu().contains( "M" )) {
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                } else {
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                }
                r.keyPress( KeyEvent.VK_ENTER );
                r.keyRelease( KeyEvent.VK_ENTER );
                Thread.sleep( 1000 );
            } catch (Exception e) {
                System.out.println( "Carregou o sexo" );
            }


            driver.findElement( By.xpath( "//*[@id=\"tabFormularioVertical-consultaPlacaAnchor\"]/span" ) ).click();

            wait.until( ExpectedConditions.elementToBeClickable( By.xpath( "//*[@id=\"tabFormularioVertical-consultaPlacaAnchor\"]/span" ) ) );


            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            if (!dados.getClasseBonus().contains( "0" )) {
                r.keyPress( KeyEvent.VK_TAB );
                r.keyRelease( KeyEvent.VK_TAB );

            }
            r.keyPress( KeyEvent.VK_SPACE );
            r.keyRelease( KeyEvent.VK_SPACE );
            Thread.sleep( 2000 );
            //ano fabric
            driver.findElement( By.id( "btn-lbl-select-ano-fabricacao" ) ).click();
            int ano = Integer.parseInt( dados.getAnoModelo() );
            Calendar cal = Calendar.getInstance();
            int anoAtual = cal.get( Calendar.YEAR );
            System.out.println( "Ano atual" + anoAtual );
            int qtdseta = anoAtual - ano;
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            Thread.sleep( 1000 );
            for (int i = 0; i < qtdseta; i++) {
                r.keyPress( KeyEvent.VK_DOWN );
                r.keyRelease( KeyEvent.VK_DOWN );
            }
            Thread.sleep( 1000 );
            r.keyPress( KeyEvent.VK_ENTER );
            r.keyRelease( KeyEvent.VK_ENTER );
            Thread.sleep( 1000 );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            Thread.sleep( 1000 );
            //ano modelo
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            Thread.sleep( 1000 );
            r.keyPress( KeyEvent.VK_ENTER );
            r.keyRelease( KeyEvent.VK_ENTER );
            Thread.sleep( 2000 );

            driver.findElement( By.id( "ipt-inp-modeloVeiculo" ) ).sendKeys( dados.getVeiculo() );
            Thread.sleep( 3000 );
            //  driver.findElement( By.xpath( "//*[@id=\"autocomplete-item-372\"]" ) ).click();
            driver.findElement( By.xpath( "/html/body/app-root/app-index/div/div[1]/form/div/div[2]/div/div[2]/app-consulta-placa/div/div[3]/div/fp-autocomplete/div/div/div/button" ) ).click();
            Thread.sleep( 1000 );
            int anoMod = Integer.parseInt( dados.getAnoModelo() );
            //zero
            if ((dados.getZeroKm().contains( "S" ) && (anoMod > (anoAtual - 2)))) {
                driver.findElement( By.id( "btn-lbl-btn-slct-tipo-uso" ) ).click();
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_SHIFT );
                r.keyPress( KeyEvent.VK_TAB );
                r.keyRelease( KeyEvent.VK_TAB );
                r.keyRelease( KeyEvent.VK_SHIFT );
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_SPACE );
                r.keyRelease( KeyEvent.VK_SPACE );
            }
            Thread.sleep( 1000 );
            //utilizacao
            driver.findElement( By.id( "btn-lbl-btn-slct-tipo-uso" ) ).click();
            Thread.sleep( 1000 );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            Thread.sleep( 1000 );
            r.keyPress( KeyEvent.VK_ENTER );
            r.keyRelease( KeyEvent.VK_ENTER );
            //antifurto
            wait.until( ExpectedConditions.visibilityOfElementLocated( By.id( "btn-delete-tags-multislct-question-127-0" ) ) );
            driver.findElement( By.id( "btn-delete-tags-multislct-question-127-0" ) ).click();
            Thread.sleep( 2000 );
            driver.findElement( By.id( "btn-lbl-multislct-question-127" ) ).click();
            Thread.sleep( 1000 );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_SPACE );
            r.keyRelease( KeyEvent.VK_SPACE );
            Thread.sleep( 2000 );
            //cep
            driver.findElement( By.id( "ipt-ipt-cep-component-endereco" ) ).sendKeys( dados.getCep1() + dados.getCep2() );
            if (dados.getTipoPessoa().contains( "JURI" )) {
                Thread.sleep( 1000 );
                driver.findElement( By.id( "ipt-inp-cpfCnpj0" ) ).sendKeys( dados.getCpfcond() );

            }

            //calcular
            Thread.sleep( 1000 );
            wait.until( ExpectedConditions.elementToBeClickable( By.id( "bt-buscar-ofertas-novo" ) ) );

            driver.findElement( By.id( "bt-buscar-ofertas-novo" ) ).click();
            System.out.println( "Calculou" );
            Thread.sleep( 6000 );

        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }
}