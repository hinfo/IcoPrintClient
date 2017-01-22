/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

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
        
        String nomeArq = args[0];
        String host = args[1]; // 127.0.0.1
        Integer port = Integer.parseInt(args[2]); // 5100
        
        Socket cliente = new Socket(host, port);

        ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
        
        FileInputStream fis = new FileInputStream(nomeArq);

        //int size = fis.available();

        byte[] buf = new byte[4096];
        System.out.println("Enviando arquivo...");
        while (true) {
            int len = fis.read(buf);
            if (len == -1) {
                break;
            }
            out.write(buf, 0, len);
        }
        System.out.println("Arquivo enviado!");

    }
}
