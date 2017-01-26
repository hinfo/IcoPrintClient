    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author henrique
 */
public class Cliente {
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        if (args.length < 3){
            System.out.println("Faltam parametros!\n"
                    + "Comando: java -jar IcoPrintClient.jar + nome_do_arquivo + ip_servidor + "
                    + "porta ");
            System.exit(0);
        }
        String nomeArq = args[0];
        String host = args[1]; // 127.0.0.1
        Integer port = Integer.parseInt(args[2]); // 5100
        String nomeImpressora = "Impressora padrão";
        
        
        File f = new File(nomeArq);
        try {
            Socket cliente = new Socket(host, port);

            ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
            out.writeUTF(f.getName());
            
            if (args.length > 3) {
                nomeImpressora = args[3];
            }
            
            FileInputStream fis = new FileInputStream(f);

            int size = fis.available();

            byte[] buf = new byte[4096];
            System.out.println("Enviando arquivo...");
            while (true) {
                int len = fis.read(buf);
                if (len == -1) {
                    break;
                }

                out.write(buf, 0, len);
            }
            fis.close();
            out.flush();
            System.out.println("Arquivo enviado para impressão!\nVerifique se foi impresso.");
            
            
        } catch(ConnectException e){
            System.out.println("Conexão recusada, servidor offline");
            System.exit(1);
        }
        
        


//        System.out.println("nome Arq: " + f.getName());
//        System.out.println("nome host: " + host);
//        System.out.println("nome port: " + port);
//        System.out.println("nome impressora: " + nomeImpressora);


    }
}
