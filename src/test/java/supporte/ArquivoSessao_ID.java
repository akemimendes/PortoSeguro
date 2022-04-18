package supporte;

import org.openqa.selenium.remote.SessionId;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

public class ArquivoSessao_ID {
    public static SessionId Criarsessao_id(SessionId session_id) throws IOException {
        // cria arquivo sessao id e colocar o valor do id no txt
        File filesessionID = new File("D:\\itauonline\\status\\sessionid.txt");
        filesessionID.createNewFile();
        FileWriter arq = new FileWriter("D:\\itauonline\\status\\sessionid.txt");
        PrintWriter GravarArq = new PrintWriter(arq);
        GravarArq.printf(String.valueOf(session_id));
        GravarArq.flush();
        GravarArq.close();

        return session_id;
    }


}
