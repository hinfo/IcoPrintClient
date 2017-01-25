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
import java.io.ObjectOutputStream;
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
                    + "porta + (opcional) nome_impressora");
            System.exit(0);
        }
        String nomeArq = args[0];
        String host = args[1]; // 127.0.0.1
        Integer port = Integer.parseInt(args[2]); // 5100
        String nomeImpressora = "Impressora padrão";
        
        
        
        File f = new File(nomeArq);
        Socket cliente = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
        out.writeUTF(f.getName());
        if (args.length > 3) { 
            nomeImpressora = args[3];
            ObjectOutputStream nomeImp = new ObjectOutputStream(cliente.getOutputStream());
            nomeImp.writeUTF(nomeImpressora);
        }
        FileInputStream fis = new FileInputStream(f);
        
        


//        System.out.println("nome Arq: " + f.getName());
//        System.out.println("nome host: " + host);
//        System.out.println("nome port: " + port);
//        System.out.println("nome impressora: " + nomeImpressora);

        int size = fis.available();

        byte[] buf = new byte[4096];
        System.out.println("Enviando arquivo...");
        while (true) {
            int len = fis.read(buf);
            if (len == -1) break;
            
            out.write(buf, 0, len);
        }
        fis.close();
        out.flush();
        out.close();
        
        System.out.println("Arquivo enviado!");

    }
}
