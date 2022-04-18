package basico;

import java.io.*;
import java.util.Scanner;

public class LerArquivoPorto {
    private String nrlinha;
    private Variaveis arquivoP;


    public Variaveis lerArquivoNr(String nrlinha) throws IOException {
        this.nrlinha = nrlinha;
        arquivoP = new Variaveis();

        File file = new File("/home/robertinho/itauonline/arquivos/" + nrlinha);
        if (file.exists()) {
            Scanner ler = new Scanner("/home/robertinho/itauonline/arquivos/" + nrlinha);
            String arquivo = ler.nextLine();
            FileReader arq = new FileReader(arquivo);
            BufferedReader lerArq = new BufferedReader(arq);
            arquivoP.setNomeSegur(lerArq.readLine());
            arquivoP.setCpfSegur(lerArq.readLine());
            arquivoP.setCep1(lerArq.readLine());
            arquivoP.setCep2(lerArq.readLine());
            arquivoP.setDataSegur(lerArq.readLine());
            arquivoP.setEstadoCivil(lerArq.readLine());
            arquivoP.setSexoCondu(lerArq.readLine());
            arquivoP.setTipoSeguro(lerArq.readLine());
            arquivoP.setClasseBonus(lerArq.readLine());
            arquivoP.setFranquia(lerArq.readLine());
            arquivoP.setAnoModelo(lerArq.readLine());
            arquivoP.setZeroKm(lerArq.readLine());
            arquivoP.setVeiculo(lerArq.readLine());
            arquivoP.setQtdPortas(lerArq.readLine());
            arquivoP.setCiaAnterior(lerArq.readLine());
            arquivoP.setKmTrab(lerArq.readLine());
            arquivoP.setComercial(lerArq.readLine());
            arquivoP.setTipoRes(lerArq.readLine());
            arquivoP.setGrgTrab(lerArq.readLine());
            arquivoP.setGrgEscola(lerArq.readLine());
            arquivoP.setDirige25(lerArq.readLine());
            arquivoP.setSexo25(lerArq.readLine());
            arquivoP.setFatAjust(lerArq.readLine());
            arquivoP.setDm(lerArq.readLine());
            arquivoP.setDc(lerArq.readLine());
            lerArq.readLine();
            arquivoP.setApp(lerArq.readLine());
            arquivoP.setDmo(lerArq.readLine());
            arquivoP.setCarroRes(lerArq.readLine());
            arquivoP.setAss24(lerArq.readLine());
            arquivoP.setVidro(lerArq.readLine());
            lerArq.readLine();
            arquivoP.setComissao(lerArq.readLine());
            arquivoP.setGrgRes(lerArq.readLine());
            arquivoP.setCpfcond(lerArq.readLine());
            arquivoP.setNomeCond(lerArq.readLine());
            arquivoP.setDatacond(lerArq.readLine());
            arquivoP.setAlienado(lerArq.readLine());
            arquivoP.setPortAut(lerArq.readLine());
            arquivoP.setRelacaoseg(lerArq.readLine());

            lerArq.readLine();
            lerArq.readLine();
            lerArq.readLine();
            lerArq.readLine();
            arquivoP.setTipoPessoa(lerArq.readLine());

        }

        return arquivoP;
    }

}
