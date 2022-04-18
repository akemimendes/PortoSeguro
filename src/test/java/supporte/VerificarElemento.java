package supporte;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;

public class VerificarElemento {

    public static boolean verificar(WebDriver driver, String elemento) {
        System.out.println("testar se o elemento está visivel");
        boolean verificar = false;

        if (driver.findElements(By.xpath(elemento)).size()>0){
            verificar = true;
        }

       /* try {
          //  driver.findElement(By.id(elemento)).isDisplayed();
            driver.findElement(By.xpath(elemento)).isDisplayed();
            verificar = true;
            System.out.println("achou o elemento");
        } catch (NoSuchElementException e) {
            verificar = false;
            System.out.println("não achou o elemento");

        }*/
        return verificar;
    }


    public static boolean verificar(WebDriver driver, String elemento, String tipo, int tempo) {
        System.out.println("testar se o elemento está visivel");
        boolean verificar = false;
        boolean controlaRepeat = false;
        int conta = 0;
        while (!verificar) {
            try {
                if (tipo.toUpperCase().contains("ID")) {
                    driver.findElement(By.id(elemento)).isDisplayed();
                } else if (tipo.toUpperCase().contains("NAME")) {
                    driver.findElement(By.name(elemento)).isDisplayed();
                } else if (tipo.toUpperCase().contains("XPATH")) {
                    driver.findElement(By.xpath(elemento)).isDisplayed();
                }
                verificar = true;
                controlaRepeat = true;
                System.out.println("achou o elemento");
            } catch (NoSuchElementException e) {
                verificar = false;
                conta = conta++;
                System.out.println("não achou o elemento");

            } finally {
                if (conta < tempo) {
                    continue;
                }

            }
        }
        return controlaRepeat;
    }
}


