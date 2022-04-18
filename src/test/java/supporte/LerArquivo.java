package supporte;

import org.openqa.selenium.remote.SessionId;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class LerArquivo {
    public static URL LerArquivourl(URL url) throws IOException {
        Scanner ler = new Scanner("D:\\itauonline\\status\\url.txt");
        String nome1 = ler.nextLine();
        FileReader arq = new FileReader(nome1);
        BufferedReader LerArquivo = new BufferedReader(arq);
        String linkurl = LerArquivo.readLine();
        url = new URL(linkurl);
        arq.close();
        return url;
    }
    public static SessionId LerArquivoSessao(SessionId session_id) throws IOException {
        Scanner ler1 = new Scanner("D:\\itauonline\\status\\sessionid.txt");
        String nome2 = ler1.nextLine();
        FileReader arq1 = new FileReader(nome2);
        BufferedReader LerArquivo1 = new BufferedReader(arq1);
        String iddasessao = LerArquivo1.readLine();
        session_id = new SessionId(iddasessao);
        arq1.close();
        return session_id;
    }
}
