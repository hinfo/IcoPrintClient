package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author henrique
 */
public class Cliente {

    private static Socket socket;
    private static ObjectInputStream entrada;
    private static ObjectOutputStream output;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {

        String nomeArq = null;
        String host = null;
        Integer port = null;
        String comando1 = "";
        String comando2 = "";

        if (args.length < 3) {
            help();
            System.exit(0);
        } else if (args.length == 1) {
            help();
        } else {
             nomeArq = args[0];
             host = args[1]; // 127.0.0.1
             port = Integer.parseInt(args[2]); // 5100
//        System.out.println("tm args " + args.length);
            if (args.length == 4) {
                comando1 = args[3];
            } else if (args.length == 5) {
                comando1 = args[3];
                comando2 = args[4];
            }
        }
        String nomeImpressora = "Impressora padrão"; // não implementado.

        File arquivoParaImpressao = new File(nomeArq);
        try {
            socket = new Socket(host, port);

            output = new ObjectOutputStream(socket.getOutputStream());
            FileInputStream fis = new FileInputStream(arquivoParaImpressao);
            output.writeUTF(arquivoParaImpressao.getName()
                    + " " + comando1
                    + " " + comando2);

            if (args.length > 3) {
                nomeImpressora = args[3];
            }

//            int size = fis.available();
            byte[] buf = new byte[fis.available()];
            int len;
            System.out.println("Enviando arquivo...");
            while ((len = fis.read(buf)) > 0) {
                output.write(buf, 0, len);
            }
            System.out.println("Arquivo enviado para impressão!");

//            System.out.print("Recebendo resposta");
//            entrada = new ObjectInputStream(socket.getInputStream());
//            String resposta;
//            try {
//                resposta = entrada.readUTF();
//                System.out.println("...OK");
//
//            } catch (IOException e) {
//                resposta = "Falha de retorno do servidor!\n"
//                        + "Erro: " + e;
//                System.out.println("...Erro");
//            }
            fis.close();
            output.flush();
            output.close();
//            entrada.close();
//            socket.close();

        } catch (ConnectException e) {
            System.out.println("Conexão recusada, servidor offline");
            System.exit(1);
        }

    }

    private byte[] serializarArquivo(Arquivo arquivo) {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream ous;
            ous = new ObjectOutputStream(bao);
            ous.writeObject(arquivo);
            return bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void help() {
        String msg
                = "Parâmetros Obrigatórios:\n"
                + "\tComando: java -jar IcoPrintClient.jar "
                + "+ <nome_do_arquivo> + <ip_servidor> + "
                + "<porta> \n"
                + "Parâmetros opcionais:\n"
                + "\t1-EXECUTA=<executável do windows> \n"
                + "\t2-<arquivo>\n"
                + "Exemplo: \n"
                + "\tjava -jar IcoPrintClient.jar "
                + "teste.txt 192.168.0.250 5001 EXECUTA=xxxxx.exe teste.txt";

        System.out.println(msg);
    }
}
