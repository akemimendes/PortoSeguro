package basico;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import supporte.Pdf;

import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.*;


public class AceitaCobertura {
    Variaveis dados;
    WebDriver driver;
    WebDriverWait wait;
    Robot r;
    String aceitaAzul;
    Pdf trataPDF;


    public void verificaAceita(WebDriver driver, Variaveis dados) throws InterruptedException, IOException, UnsupportedFlavorException, AWTException {
        Thread.sleep( 1000 );
        wait = new WebDriverWait( driver, 30 );
        String aceita = "";
        trataPDF = new Pdf();

        r = new Robot();
        this.dados = dados;
        this.driver = driver;

        try {
            driver.findElement( By.xpath( "//*[contains(text(), 'Risco não aceito pela Cia')]" ) );
            aceita = "nao";
            dados.setAceita( "nao" );
        } catch (Exception e) {
            aceita = "sim";
            dados.setAceita( "sim" );
            wait.until( ExpectedConditions.visibilityOfElementLocated( By.id( "btn-gerar-pdf" ) ) );
        }

        if (aceita.contains( "nao" )) {
            System.out.println( "Restricao!" );
            String textoQueSeraEscrito;
            WebElement corpoPag = driver.findElement( By.xpath( "//*[@id=\"modal-body-status\"]/div/h4" ) );

            textoQueSeraEscrito = corpoPag.getText();
            FileWriter arquivo;

            File file = new File( "/home/robertinho/itauonline/arquivos/P_" + dados.getNrlinha() );


            arquivo = new FileWriter( file, true );
            if (file.exists()) {
                arquivo.append( textoQueSeraEscrito );
            } else {
                arquivo.write( textoQueSeraEscrito );
            }

            System.gc();
            arquivo.close();
            System.out.println( "Salvou rstricao" );


            File arqOrigem = null;
            File arqDestino = null;

            arqOrigem = new File( "/home/robertinho/itauonline/arquivos/" + dados.getNrlinha() );
            arqDestino = new File( "/home/robertinho/itauonline/erro/" + dados.getNrlinha() );
            if (arqOrigem.exists() && arqOrigem.isFile()) {
                InputStream in = new FileInputStream( arqOrigem );
                OutputStream out = new FileOutputStream( arqDestino );
                // Transferindo bytes de entrada para saída
                byte[] buffer = new byte[1024];
                int lenght;
                while ((lenght = in.read( buffer )) > 0) {
                    out.write( buffer, 0, lenght );
                }
                in.close();
                out.close();
                boolean i = true;
                while (i) {
                    if (arqOrigem.delete()) {
                        i = false;
                    } else {
                        System.gc();
                    }
                }

            }

            System.gc();
            arquivo.close();
            arqOrigem = new File( "/home/robertinho/itauonline/arquivos/P_" + dados.getNrlinha() );
            arqDestino = new File( "/home/robertinho/itauonline/erro/P_" + dados.getNrlinha() );
            if (arqOrigem.exists() && arqOrigem.isFile()) {
                InputStream in = new FileInputStream( arqOrigem );
                OutputStream out = new FileOutputStream( arqDestino );
                // Transferindo bytes de entrada para saída
                byte[] buffer = new byte[1024];
                int lenght;
                while ((lenght = in.read( buffer )) > 0) {
                    out.write( buffer, 0, lenght );
                }
                in.close();
                out.close();
                boolean i = true;
                while (i) {
                    if (arqOrigem.delete()) {
                        i = false;
                    } else {
                        System.gc();
                    }
                }

            }

            System.gc();
            arquivo.close();

        } else {


            if (driver.findElements( By.xpath( "//*[contains(text(), 'Recusado')]" ) ).size() > 0) {
                aceitaAzul = "nao";
                FileWriter arquivo;

                File file = new File( "/home/robertinho/itauonline/erro/A_" + dados.getNrlinha() );
                String textoQueSeraEscrito = "Azul recusado";
                arquivo = new FileWriter( file, true );
                arquivo.write( textoQueSeraEscrito );

            } else {
                aceitaAzul = "sim";

            }
            this.coberturaPorto();
            if (aceitaAzul.contains( "sim" )) {
                this.coberturaAzul();
            }
            Thread.sleep( 5000 );
            System.out.println( "Vai tentar imprimir" );

            wait.until( ExpectedConditions.elementToBeClickable( By.id( "btn-gerar-pdf" ) ) );
            Thread.sleep( 1000 );

            WebElement btnPDF = driver.findElement( By.id( "btn-gerar-pdf" ) );
            JavascriptExecutor jsPDF = (JavascriptExecutor) driver;
            jsPDF.executeScript( "arguments[0].click();", btnPDF );

            Thread.sleep( 5000 );

            this.imprimir( "Porto" );
            trataPDF.moverPdfDownloadParaPastaPdf( dados, "Porto" );


            if (aceitaAzul.contains( "sim" )) {
                this.imprimir( "Azul" );
                trataPDF.moverPdfDownloadParaPastaPdf( dados, "Azul" );
            }
        }
    }

    public void imprimir(String cia) throws InterruptedException {
        //verificar imprimir

        wait.until( ExpectedConditions.visibilityOfElementLocated( By.id( "btn-fechar-modal-gerar-pdf" ) ) );
        Thread.sleep( 1000 );
        if (aceitaAzul.contains( "sim" )) {
            driver.findElement( By.xpath( "//*[@id=\"btn-lbl-\"]/fp-icon" ) ).click();
            driver.findElement( By.xpath( "//*[@id=\"btn-lbl-\"]/fp-icon" ) ).click();
        }

        Thread.sleep( 1000 );
        if ((cia.contains( "Azul" )) && (aceitaAzul.contains( "sim" ))) {
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_SPACE );
            r.keyRelease( KeyEvent.VK_SPACE );
        } else if (cia.contains( "Porto" )) {
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );

            r.keyPress( KeyEvent.VK_SPACE );
            r.keyRelease( KeyEvent.VK_SPACE );
        }

        driver.findElement( By.id( "btn-fechar-modal-gerar-pdf" ) ).click();
        wait.until( ExpectedConditions.visibilityOfElementLocated( By.xpath( "//*[contains(text(), 'Solicitação atendida. Obrigada por aguardar.')]" ) ) );


        Thread.sleep( 1000 );
//       driver.findElement( By.id( "btn-fechar-modal-gerar-pdf" ) ).click();
        //      wait.until( ExpectedConditions.visibilityOfElementLocated( By.xpath( "//*[contains(text(), 'Solicitação atendida. Obrigada por aguardar.')]" ) ) );
       /* Thread.sleep( 2000 );
        for (int i = 0; i < 20; i++) {
            r.keyPress( KeyEvent.VK_DELETE );
            r.keyRelease( KeyEvent.VK_DELETE );
        }
        Thread.sleep( 1000 );
        Screen screen = new Screen();
        screen.type( dados.getNrlinha() );
        r.keyPress( KeyEvent.VK_ENTER );
        r.keyRelease( KeyEvent.VK_ENTER );*/
        Thread.sleep( 1000 );
    }

    private void preencherCobertura(String cia) throws InterruptedException {
        System.out.println( "fator de ajuste" );
        WebElement fatajust = driver.findElement( By.id( "input-range-valor-base" ) );
        ((JavascriptExecutor) driver).executeScript( "$ ( arguments [ 0]) . val ( " + dados.getFatAjust() + ") . change ( )", fatajust );
        Thread.sleep( 1000 );
        //franquia
        driver.findElement( By.id( "btn-lbl-btn-slct-franquia-seguro" ) ).click();
        Thread.sleep( 1000 );


        if (dados.getFranquia().toUpperCase().contains( "NORMAL" ) || (dados.getFranquia().toUpperCase().contains( "OBRI" ))) {

            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );

        }
        System.out.println( "franquia" );
        Thread.sleep( 1000 );
        r.keyPress( KeyEvent.VK_ENTER );
        r.keyRelease( KeyEvent.VK_ENTER );
        Thread.sleep( 1000 );
        //dm


        driver.findElement( By.xpath( "//*[@id=\"btn-lbl-\"]" ) ).click();
        Thread.sleep( 1000 );
        r.keyPress( KeyEvent.VK_HOME );
        r.keyRelease( KeyEvent.VK_HOME );
        Thread.sleep( 1000 );
        if (dados.getDm().contains( "75000" )) {
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
        } else if (dados.getDm().contains( "100000" )) {
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
        } else if (dados.getDm().contains( "150000" )) {
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
        } else if (dados.getDm().contains( "200000" )) {
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );

        }
        Thread.sleep( 1000 );
        r.keyPress( KeyEvent.VK_ENTER );
        r.keyRelease( KeyEvent.VK_ENTER );
        Thread.sleep( 1000 );

        System.out.println( "dm" );

        //dc


        driver.findElement( By.xpath( "/html/body/app-root/app-index/div[1]/div[2]/article/div[4]/div/div/div[3]/div/div/app-coberturas[2]/div/div/app-slider/div/div/div[2]/fp-dropdown/div/div[1]/button" ) ).click();
        Thread.sleep( 1000 );
        r.keyPress( KeyEvent.VK_HOME );
        r.keyRelease( KeyEvent.VK_HOME );
        Thread.sleep( 1000 );
        if (dados.getDc().contains( "75000" )) {
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
        } else if (dados.getDc().contains( "100000" )) {
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
        } else if (dados.getDc().contains( "150000" )) {
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
        } else if (dados.getDc().contains( "200000" )) {
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
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
        System.out.println( "dc" );

        if (cia.contains( "Azul" )) {
            //dmo
            int valDMO = Integer.parseInt( dados.getDmo() );
            dados.setDmo( "10000" );
            valDMO = 10000;
            if (valDMO > 0) {
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_SPACE );
                r.keyRelease( KeyEvent.VK_SPACE );
                Thread.sleep( 1000 );
                WebElement dmo = driver.findElement( By.id( "input-range-DanosMoraiseEstéticos" ) );
                ((JavascriptExecutor) driver).executeScript( "$ ( arguments [ 0]) . val ( " + dados.getDmo() + ") . change ( )", dmo );

                driver.findElement( By.xpath( "//*[contains(text(), ' Danos Morais e Estéticos ')]" ) ).click();
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_TAB );
                r.keyRelease( KeyEvent.VK_TAB );
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_TAB );
                r.keyRelease( KeyEvent.VK_TAB );
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_HOME );
                r.keyRelease( KeyEvent.VK_HOME );
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_DOWN );
                r.keyRelease( KeyEvent.VK_DOWN );
                Thread.sleep( 1000 );
                if (dados.getDmo().contains( "10000" )) {
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );


                } else if (dados.getDmo().contains( "20000" )) {
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );

                } else if (dados.getDmo().contains( "30000" )) {
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );


                } else if (dados.getDmo().contains( "40000" )) {
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );


                }
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_ENTER );
                r.keyRelease( KeyEvent.VK_ENTER );
                Thread.sleep( 1000 );

            }
        }
        System.out.println( "dmo" );
        //app
        if ((aceitaAzul.contains( "nao" )) && (cia.contains( "Porto" ))) {
            r.keyPress( KeyEvent.VK_SPACE );
            r.keyRelease( KeyEvent.VK_SPACE );
        }
        WebElement app = driver.findElement( By.id( "input-range-AcidentesPessoaisPassageiros" ) );
        ((JavascriptExecutor) driver).executeScript( "$ ( arguments [ 0]) . val ( " + dados.getApp() + ") . change ( )", app );
        System.out.println( "app" );
        try {
            driver.findElement( By.xpath( "/html/body/app-root/app-index/div[1]/div[2]/article/div[4]/div/div/div[3]/div/div/app-coberturas[3]/div/div/app-slider/div/div/div[2]/fp-dropdown/div/div[1]/button/span" ) ).click();
        } catch (Exception e) {
            driver.findElement( By.xpath( "/html/body/app-root/app-index/div[1]/div[2]/article/div[4]/div/div/div[3]/div/div/app-coberturas[4]/div/div/app-slider/div/div/div[2]/fp-dropdown/div/div[1]/button/span" ) ).click();
        }
        Thread.sleep( 1000 );
        r.keyPress( KeyEvent.VK_HOME );
        r.keyRelease( KeyEvent.VK_HOME );
        Thread.sleep( 1000 );
        if (dados.getApp().contains( "2000" )) {
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
        } else if (dados.getApp().contains( "5000" )) {
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
        } else if (dados.getApp().contains( "10000" )) {
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
        } else if (dados.getApp().contains( "15000" )) {
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
            r.keyPress( KeyEvent.VK_DOWN );
            r.keyRelease( KeyEvent.VK_DOWN );
        }
        r.keyPress( KeyEvent.VK_ENTER );
        r.keyRelease( KeyEvent.VK_ENTER );
        Thread.sleep( 1000 );

        r.keyPress( KeyEvent.VK_TAB );
        r.keyRelease( KeyEvent.VK_TAB );
        Thread.sleep( 1000 );
        System.out.println( "dmo2" );
        if (cia.contains( "Porto" )) {
            //dmo
            int valDMO = Integer.parseInt( dados.getDmo() );
            dados.setDmo( "10000" );
            valDMO = 10000;
            if (valDMO > 0) {
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_SPACE );
                r.keyRelease( KeyEvent.VK_SPACE );
                Thread.sleep( 1000 );
                WebElement dmo = driver.findElement( By.id( "input-range-DanosMoraiseEstéticos" ) );
                ((JavascriptExecutor) driver).executeScript( "$ ( arguments [ 0]) . val ( " + dados.getDmo() + ") . change ( )", dmo );


                driver.findElement( By.xpath( "//*[contains(text(), ' Danos Morais e Estéticos ')]" ) ).click();
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_TAB );
                r.keyRelease( KeyEvent.VK_TAB );
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_TAB );
                r.keyRelease( KeyEvent.VK_TAB );
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_HOME );
                r.keyRelease( KeyEvent.VK_HOME );
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_DOWN );
                r.keyRelease( KeyEvent.VK_DOWN );
                Thread.sleep( 1000 );
                if (dados.getDmo().contains( "10000" )) {
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );


                } else if (dados.getDmo().contains( "20000" )) {
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );

                } else if (dados.getDmo().contains( "30000" )) {
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );


                } else if (dados.getDmo().contains( "40000" )) {
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );
                    r.keyPress( KeyEvent.VK_DOWN );
                    r.keyRelease( KeyEvent.VK_DOWN );


                }
                Thread.sleep( 1000 );
                r.keyPress( KeyEvent.VK_ENTER );
                r.keyRelease( KeyEvent.VK_ENTER );

            }
        }

        System.out.println( "dmo3" );
        Thread.sleep( 1000 );
        r.keyPress( KeyEvent.VK_ENTER );
        r.keyRelease( KeyEvent.VK_ENTER );
        r.keyPress( KeyEvent.VK_ENTER );
        r.keyRelease( KeyEvent.VK_ENTER );
        Thread.sleep( 1000 );
        r.keyPress( KeyEvent.VK_TAB );
        r.keyRelease( KeyEvent.VK_TAB );
        r.keyPress( KeyEvent.VK_TAB );
        r.keyRelease( KeyEvent.VK_TAB );
    }

    private void coberturaAzul() throws InterruptedException {
        System.out.println( "nao teve restricao azul, calculou" );
        Thread.sleep( 1000 );
        //alterar cobertura da porto
        wait = new WebDriverWait( driver, 10000 );
       /* wait.until( ExpectedConditions.elementToBeClickable( By.id( "btnVoltarOferta" ) ) );
        Thread.sleep( 1000 );
        driver.findElement( By.id( "btnVoltarOfertaEnd" ) ).click();*/
        Thread.sleep( 1000 );
        wait.until( ExpectedConditions.elementToBeClickable( By.id( "bt-personalizar-oferta-2" ) ) );
        Thread.sleep( 1000 );
        driver.findElement( By.id( "bt-personalizar-oferta-2" ) ).click();
        System.out.println( "clicou para alterar as cob da azul" );

        wait.until( ExpectedConditions.elementToBeClickable( By.id( "input-range-valor-base" ) ) );

        preencherCobertura( "Azul" );
        //vidro
        if (dados.getVidro().contains( "NAO" )) {
            r.keyPress( KeyEvent.VK_SPACE );
            r.keyRelease( KeyEvent.VK_SPACE );
        }

        //ass24h

        if (dados.getAss24().contains( "3" )) {
            driver.findElement( By.id( "checkbox-63" ) ).click();
            Thread.sleep( 1000 );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
        } else if (dados.getAss24().contains( "4" )) {
            driver.findElement( By.id( "checkbox-60" ) ).click();
            Thread.sleep( 1000 );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );

        } else {
            driver.findElement( By.id( "checkbox-59" ) ).click();
            Thread.sleep( 1000 );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
        }
        Thread.sleep( 1000 );
        //carro reserva

        if (dados.getCarroRes().contains( "7" )) {
            driver.findElement( By.id( "checkbox-66" ) ).click();
        } else if (dados.getCarroRes().contains( "15" )) {
            driver.findElement( By.id( "checkbox-69" ) ).click();
        } else if (dados.getCarroRes().contains( "30" )) {
            driver.findElement( By.id( "checkbox-72" ) ).click();
        }


        Thread.sleep( 1000 );
        //calcular
        wait.until( ExpectedConditions.elementToBeClickable( By.id( "btn-salvar" ) ) );
        Thread.sleep( 1000 );

        WebElement btnPDF = driver.findElement( By.id( "btn-salvar" ) );
        JavascriptExecutor jsPDF = (JavascriptExecutor) driver;
        jsPDF.executeScript( "arguments[0].click();", btnPDF );

        Thread.sleep( 1000 );
        wait.until( ExpectedConditions.elementToBeClickable( By.id( "btnVoltarOferta" ) ) );
        Thread.sleep( 1000 );
        driver.findElement( By.id( "btnVoltarOfertaEnd" ) ).click();
        Thread.sleep( 1000 );

    }

    private void coberturaPorto() throws InterruptedException {

        System.out.println( "nao teve restricao porto, calculou" );
        Thread.sleep( 1000 );
        //alterar cobertura da porto
        driver.findElement( By.id( "bt-personalizar-oferta-1" ) ).click();
        System.out.println( "clicou para alterar as cob da porto" );
        Thread.sleep( 7000 );
        //fator de ajuste
        wait = new WebDriverWait( driver, 10000 );
        wait.until( ExpectedConditions.elementToBeClickable( By.id( "input-range-valor-base" ) ) );
        Thread.sleep( 2000 );
        System.out.println( "fator de ajuste" );
        preencherCobertura( "Porto" );


        //vidro
        if (dados.getVidro().contains( "NAO" )) {
            r.keyPress( KeyEvent.VK_SPACE );
            r.keyRelease( KeyEvent.VK_SPACE );
        }
        System.out.println( "vidro" );
        //ass24h
        Thread.sleep( 1000 );
        if (dados.getAss24().contains( "3" )) {
            driver.findElement(By.xpath( "//*[contains(text(), 'Pacote Assistências')]//*[contains(@class='radiobutton__input')]" ) ).click();

          /*  r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );*/
            r.keyPress( KeyEvent.VK_DOWN);
            r.keyRelease( KeyEvent.VK_DOWN);

           /* r.keyPress( KeyEvent.VK_SHIFT );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_SHIFT );*/


            //driver.findElement( By.id( "checkbox-188" ) ).click();
           System.out.println("clicou ass 24h");



            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
        } else if (dados.getAss24().contains( "4" )) {
            driver.findElement( By.id( "checkbox-191" ) ).click();
            Thread.sleep( 1000 );

            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );

        } else {
            driver.findElement( By.id( "checkbox-187" ) ).click();
            Thread.sleep( 1000 );

            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
        }
        Thread.sleep( 1000 );
        System.out.println( "ass24h " + dados.getAss24() );
        //carro reserva
        if (dados.getCarroRes().contains( "NAO" )) {
            r.keyPress( KeyEvent.VK_SPACE );
            r.keyRelease( KeyEvent.VK_SPACE );
        } else {
            if (dados.getCarroRes().contains( "7 BAS" )) {
                try {
                    driver.findElement( By.id( "checkbox-194" ) ).click();
                } catch (Exception e) {
                    driver.findElement( By.id( "checkbox-196" ) ).click();
                }

                Thread.sleep( 1000 );

            } else if (dados.getCarroRes().contains( "15 BAS" )) {
                try {
                    driver.findElement( By.id( "checkbox-197" ) ).click();
                } catch (Exception e) {
                    driver.findElement( By.id( "checkbox-199" ) ).click();
                }

                Thread.sleep( 1000 );

            } else if (dados.getCarroRes().contains( "30 BAS" )) {
                try {
                    driver.findElement( By.id( "checkbox-200" ) ).click();
                } catch (Exception e) {
                    driver.findElement( By.id( "checkbox-202" ) ).click();
                }

                Thread.sleep( 1000 );

            } else if (dados.getCarroRes().contains( "7 COM" )) {

                try {
                    driver.findElement( By.id( "checkbox-206" ) ).click();
                } catch (Exception e) {
                    driver.findElement( By.id( "checkbox-208" ) ).click();
                }
                Thread.sleep( 1000 );

            } else if (dados.getCarroRes().contains( "15 COM" )) {
                try {
                    driver.findElement( By.id( "checkbox-209" ) ).click();
                } catch (Exception e) {
                    driver.findElement( By.id( "checkbox-211" ) ).click();
                }
                Thread.sleep( 1000 );

            } else if (dados.getCarroRes().contains( "30 COM" )) {
                try {
                    driver.findElement( By.id( "checkbox-212" ) ).click();
                } catch (Exception e) {
                    driver.findElement( By.id( "checkbox-213" ) ).click();
                }

                Thread.sleep( 1000 );

            }
        }
        System.out.println( "creser " + dados.getCarroRes() );
        if (driver.findElements( By.xpath( "//*[contains(text(), 'Auto+Residencial')]" ) ).size() > 0) {
            driver.findElement( By.xpath( "//*[contains(text(), 'Auto+Residencial')]" ) ).click();
            Thread.sleep( 1000 );
            //remover residencial
            r.keyPress( KeyEvent.VK_SHIFT );
            r.keyPress( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_TAB );
            r.keyRelease( KeyEvent.VK_SHIFT );
            r.keyPress( KeyEvent.VK_SPACE );
            r.keyRelease( KeyEvent.VK_SPACE );
            Thread.sleep( 1000 );
        }
        System.out.println( "resid" );
        //calcular

        try {


            wait.until( ExpectedConditions.elementToBeClickable( By.id( "recalcular" ) ) );
            Thread.sleep( 1000 );

            WebElement btnPDF = driver.findElement( By.id( "recalcular" ) );
            JavascriptExecutor jsPDF = (JavascriptExecutor) driver;
            jsPDF.executeScript( "arguments[0].click();", btnPDF );
        } catch (Exception e) {
            wait.until( ExpectedConditions.elementToBeClickable( By.id( "btn-salvar" ) ) );
            Thread.sleep( 1000 );

            WebElement btnPDF = driver.findElement( By.id( "btn-salvar" ) );
            JavascriptExecutor jsPDF = (JavascriptExecutor) driver;
            jsPDF.executeScript( "arguments[0].click();", btnPDF );

            Thread.sleep( 1000 );
            wait.until( ExpectedConditions.elementToBeClickable( By.id( "btnVoltarOferta" ) ) );
            Thread.sleep( 1000 );
            driver.findElement( By.id( "btnVoltarOfertaEnd" ) ).click();
        }

        Thread.sleep( 1000 );


    }

}



