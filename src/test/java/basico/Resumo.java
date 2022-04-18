package basico;

import java.io.*;
import java.util.Scanner;

public class Resumo {

    private String tipoVariacao;
    private String tipoCalculo;
    private String trocaCpf;
    private String duplicarCalc;
    private String azulRoubo;
    private String azulLeve;
    private static Resumo arqResumo;

    public static Resumo getInstance(){
        if (arqResumo==null){
            try {
                arqResumo=carregarResumo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arqResumo;
    }

    public static Resumo carregarResumo() throws IOException {
        Resumo arqResumo=new Resumo();
        File file = new File("D:\\itauonline\\n_cotacao\\resumo.txt");
        if (file.exists()) {
            Scanner ler = new Scanner("D:\\itauonline\\n_cotacao\\resumo.txt");
            String resumo = ler.nextLine();
            FileReader arqRes = new FileReader(resumo);
            BufferedReader lerResumo = new BufferedReader(arqRes);
            lerResumo.readLine();
            arqResumo.setTipoVariacao(lerResumo.readLine());
            lerResumo.readLine();
            arqResumo.setTrocaCpf(lerResumo.readLine());
            lerResumo.readLine();
            arqResumo.setDuplicarCalc(lerResumo.readLine());
            lerResumo.readLine();
            arqResumo.setTipoCalculo(lerResumo.readLine());
            lerResumo.readLine();
            arqResumo.setAzulRoubo(lerResumo.readLine());
            lerResumo.readLine();
            arqResumo.setAzulLeve(lerResumo.readLine());
        }
        return arqResumo;

    }

    public String getTipoVariacao() {
        return tipoVariacao;
    }

    public void setTipoVariacao(String tipoVariacao) {
        this.tipoVariacao = tipoVariacao;
    }

    public String getTipoCalculo() {
        return tipoCalculo;
    }

    public void setTipoCalculo(String tipoCalculo) {
        this.tipoCalculo = tipoCalculo;
    }

    public String getTrocaCpf() {
        return trocaCpf;
    }

    public void setTrocaCpf(String trocaCpf) {
        this.trocaCpf = trocaCpf;
    }

    public String getDuplicarCalc() {
        return duplicarCalc;
    }

    public void setDuplicarCalc(String duplicarCalc) {
        this.duplicarCalc = duplicarCalc;
    }

    public String getAzulRoubo() {
        return azulRoubo;
    }

    public void setAzulRoubo(String azulRoubo) {
        this.azulRoubo = azulRoubo;
    }

    public String getAzulLeve() {
        return azulLeve;
    }

    public void setAzulLeve(String azulLeve) {
        this.azulLeve = azulLeve;
    }
}
