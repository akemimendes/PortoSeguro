package tests;

import basico.AbrirPorto;
import basico.ExceptionPorto;
import basico.Resumo;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;

public class TesteArray {

    @Test
    public void testeLista() throws InterruptedException, ExceptionPorto, IOException {

        AbrirPorto abrir=new AbrirPorto();
        WebDriver driver=abrir.verificarSessao();

      /*  ArrayList<String> cias=new ArrayList<String>();
        cias.add("Porto");
        cias.add("HDI");
        cias.add("Sompo");

        for(int i=0;i<cias.size();){
            System.out.println(cias.get(i).toString());
            i++;
        }

        cias.remove(1);
        System.out.println("remove hdi");
        for(int i=0;i<cias.size();){
            System.out.println(cias.get(i).toString());
            i++;
        }*/
    }
}
