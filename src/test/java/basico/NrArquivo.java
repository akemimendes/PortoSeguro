package basico;

import java.io.*;
import java.util.ArrayList;

public class NrArquivo {

    public ArrayList<String> carregarArquivos() {
        ArrayList<String> listaNrlinha = new ArrayList<String>();

        File[] files = new File("/home/robertinho/itauonline/arquivos/").listFiles();
        try {
            for (File file : files) {
                listaNrlinha.add(file.getName());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaNrlinha;
    }

    public void deleteArquivo(String nrtxt) throws IOException {
        File arqOrigem = new File("/home/robertinho/itauonline/arquivos/" + nrtxt);
        File arqDestino = new File("/home/robertinho/itauonline/arquivos/" + nrtxt);
        if (arqOrigem.exists() && arqOrigem.isFile()) {
            InputStream in = new FileInputStream(arqOrigem);
            OutputStream out = new FileOutputStream(arqDestino);
            // Transferindo bytes de entrada para saída
            byte[] buffer = new byte[1024];
            int lenght;
            while ((lenght = in.read(buffer)) > 0) {
                out.write(buffer, 0, lenght);
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

    }


}


