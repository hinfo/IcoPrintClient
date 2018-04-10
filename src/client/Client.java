package client;

import bean.Comandos;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import utils.ClientUtils;

/**
 *
 * @author henrique
 */
public class Client {

    private static Socket socket;
    private static ObjectInputStream entrada;
    private static ObjectOutputStream output;

    /**
     * @param args the command line arguments args[0] nome do arquivo args[1]
     * host args[2] porta args[3] comando1 args[4] comando extra "EXECUTA="
     * args[5] nome impressora
     */
    public static void main(String[] args) throws UnknownHostException, IOException {

        Comandos cmds = new Comandos();
        ClientUtils util = new ClientUtils();

        String nomeArq = null;
        String host = null;
        Integer port = null;
        String comando1 = "";
        String comando2 = "";
        int qtdeParams = args.length;

        if (qtdeParams <= 2) {
            util.help();
            System.exit(0);

        } else {
//                cmds.setNomeArquivo(args[0]);
                cmds.setNomeArquivo(util.removeStashNomeArquivo(args[0]));
                host = args[1]; // 127.0.0.1
                port = Integer.parseInt(args[2]); // 5100
            
            for (String arg : args) {
                if (arg.toUpperCase().contains("EXECUTA")) {
                    cmds.setCmdExecuta(arg.split("=")[1]);
                } else {
                    cmds.setCmdExecuta("");
                }
                if (arg.contains("nomeImpressora")) {
                    cmds.setNomeImpressora(arg.split("=")[1]);
                } else {
                    cmds.setNomeImpressora("");
                }
            }
        }
//        switch (qtdeParams) {
//            case 1: // just the command
//                util.help();
//                System.exit(0);
//            case 2:
//                util.help();
//                System.exit(0);
//            case 3:
//                comando1 = "";
//                cmds.setNomeImpressora(args[5]);
//            case 4:
//                nomeArq = args[0];
//                cmds.setNomeArquivo(util.removeStashNomeArquivo(args[0]));
//                host = args[1]; // 127.0.0.1
//                port = Integer.parseInt(args[2]); // 5100
//                comando1 = args[3];
//                cmds.setCmdExecuta(util.serviceArgs(args[3]));
//                break;
//            case 5:
//                nomeArq = args[0];
//                cmds.setNomeArquivo(util.removeStashNomeArquivo(args[0]));
//                host = args[1]; // 127.0.0.1
//                port = Integer.parseInt(args[2]); // 5100
//                comando1 = args[3];
//                comando2 = args[4];
//                cmds.setCmdExecuta(util.serviceArgs(args[3]));
//                cmds.setCmdExtra(args[4]);
//                break;
//            case 6:
//                nomeArq = args[0];
//                cmds.setNomeArquivo(util.removeStashNomeArquivo(args[0]));
//                host = args[1]; // 127.0.0.1
//                port = Integer.parseInt(args[2]); // 5100
//                comando1 = args[3];
////                comando2 = args[4];
//                cmds.setCmdExecuta(util.serviceArgs(args[3]));
//                cmds.setCmdExtra(args[4]);
//                cmds.setNomeImpressora(args[5]);
//                break;
//            default:
//                nomeArq = args[0];
//                cmds.setNomeArquivo(util.removeStashNomeArquivo(args[0]));
//                host = args[1]; // 127.0.0.1
//                port = Integer.parseInt(args[2]); // 5100
//                cmds.setCmdExecuta("");
//                cmds.setCmdExtra("");
//                cmds.setNomeImpressora("hp");
//
//        }

        File arquivoParaImpressao = new File(args[0]);
        try {
            socket = new Socket(host, port);

            output = new ObjectOutputStream(socket.getOutputStream());
            FileInputStream fis = new FileInputStream(arquivoParaImpressao);
//            output.writeUTF(arquivoParaImpressao.getName()
//                    + " " + comando1
//                    + " " + comando2);

            output.writeObject(cmds);

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

}
