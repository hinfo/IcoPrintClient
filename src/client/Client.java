package client;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
public class Client {
    public static void main(String[] args) {
        String host = "192.168.0.106";
        //String nome = "imprimir";
        Integer port = 5100;
        
        try {
            String nome = JOptionPane.showInputDialog(null,"Nome do arquivo");
            File arquivo = new File(nome);
            FileInputStream in = new FileInputStream(arquivo);
            
            Socket s = new Socket(host, port);
            OutputStream out = s.getOutputStream();
            OutputStreamWriter saida = new OutputStreamWriter(out);
            BufferedWriter writer = new BufferedWriter(saida);
            writer.write(arquivo.getName()+"\n");
            writer.flush();
            int tamanho = 4096;
            byte[] buffer = new byte[tamanho];  
            int lidos = -1;
            while ((lidos = in.read(buffer,0,tamanho)) != -1){
                out.write(buffer,0,lidos);
            }
            
            
            DataInputStream entrada = new DataInputStream(s.getInputStream());
            System.out.println("Mensagem recebida de IcoPrint: \n" +
                    entrada.readUTF());
            
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
}
