package supporte;

import basico.Variaveis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Pdf {
    public static String nomeSeguradoraPastaLocal = "itauonline";
    public static String nomeComputador = System.getProperty( "user.name" );
    public static String diretorioPastaDownload = "/home/" + nomeComputador + "/Downloads/";
    public static String diretorioPastaPdf = "/home/" + nomeComputador + '/' + nomeSeguradoraPastaLocal + "/pdf/";
    File nomePdfDownload = null;
    String nomeDownload = null;

    private void buscarDownloadPdf() throws IOException, InterruptedException {

        Thread.sleep( 7000 );
        //  preparar ambiente de comparação de PDF
        File[] ListaDeArquivosDownload;
        ArrayList<String> ListaDeNomesArquivosDownload = new ArrayList();
        Integer contadorTempoEspera = 0;

        // LOOP PARA AGUARDA O PDF SER BAIXADO E PEGAR SEU NOME DE PDF
        while (true) {
            // aguarda o dowload por 40s no maximo
            Thread.sleep( 1000 );
            if (contadorTempoEspera >= 50) {
                break;
            }
            contadorTempoEspera++;

            try {
                ListaDeArquivosDownload = new File( diretorioPastaDownload ).listFiles();
                for (File file : ListaDeArquivosDownload) {
                    ListaDeNomesArquivosDownload.add( file.getName() );
                }
                nomeDownload = ListaDeNomesArquivosDownload.get( 0 );
                nomePdfDownload = new File( diretorioPastaDownload + nomeDownload );
                // verifica se existe um arquivo ( confirmação )
                if (nomePdfDownload.exists()) {

                    // nao pode mover um pdf que ainda está em processo de Download
                    if (!nomeDownload.toLowerCase().contains( ".crdownload" )) {
                        break;
                    }
                    if (contadorTempoEspera > 25) {
                        if (nomeDownload.contains( ".crdownload" )) {
                            File correcaoNomePdfDowload = new File( diretorioPastaDownload + nomeDownload
                                    .replace( ".crdownload", "" ) );
                            nomePdfDownload.renameTo( correcaoNomePdfDowload );
                            Thread.sleep( 3000 );
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println( e.getMessage() );
                System.out.println( "Arquivo ainda não existe!" );

            }
        }
    }


    public void deletarArquivosDownload() throws InterruptedException {
        File folder = new File( diretorioPastaDownload );
        if (folder.isDirectory()) {
            File[] sun = folder.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }

        }
    }

    public void moverPdfDownloadParaPastaPdf(Variaveis dados, String cia) throws IOException, InterruptedException {
        // busca primeiro o pdf na pasta dowload e faz o devido tratamento
        buscarDownloadPdf();

        //CAMINHO P/ RENOMEAR E MOVER O ARQUIVO .../caminhopdfZero/0.pdf PARA O ...pdf/(nrlinhapdf).pdf
        File diretorioPdfComNomePdfOrigemArquivo;
        if (cia.contains( "Porto" )) {
            diretorioPdfComNomePdfOrigemArquivo = new File( diretorioPastaPdf +"P"+ dados.getNrlinha()
                    .replace( ".TXT", ".pdf" )
                    .replace( ".txt", ".pdf" ) );
        } else {
            diretorioPdfComNomePdfOrigemArquivo = new File( diretorioPastaPdf +"A"+ dados.getNrlinha()
                    .replace( ".TXT", ".pdf" )
                    .replace( ".txt", ".pdf" ) );
        }
        //RENOMEIA E MOVE O ARQUIVO DA PASTA '.../PDFNRLINHAZERO/' PARA '.../PDF/'
        if (nomePdfDownload.renameTo( diretorioPdfComNomePdfOrigemArquivo )) {

            if (nomePdfDownload.exists() && nomePdfDownload.isFile()) {
                nomePdfDownload.delete();
            }
            Thread.sleep( 1000 );
        }
    }
}


