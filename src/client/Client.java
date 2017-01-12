package client;

import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) {
        String host = "192.168.0.102";
        String comando = "imprimir";
        Integer port = 5544;
        
        try {
            Socket s = new Socket(host, port);
            DataOutputStream saida = new DataOutputStream(s.getOutputStream());
            saida.writeUTF(comando);
            
            DataInputStream entrada = new DataInputStream(s.getInputStream());
            System.out.println("Mensagem recebida de IcoPrint: \n" +
                    entrada.readUTF());
            
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
}
