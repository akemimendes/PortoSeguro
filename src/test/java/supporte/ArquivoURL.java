package supporte;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

public class ArquivoURL {
    public static URL CriarUrL(URL url) throws IOException, IOException {
//cria arquivo url e coloca o valor da url no txt
        File fileURL = new File("/home/robertinho/itauonline/status/url.txt");
        fileURL.createNewFile();
        FileWriter arq1 = new FileWriter("/home/robertinho/itauonline/status/url.txt");
        PrintWriter GravarArq1 = new PrintWriter(arq1);
        GravarArq1.printf(String.valueOf(url));
        GravarArq1.flush();
        GravarArq1.close();

        return url;
    }

}
