
import client.Client;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author henrique
 */
public class Test {
    
    public static void main(String[] args) throws IOException {
    Client test = new Client();
//        String arq = "/home/icone/teste_registro_de_saidas.pdf";
        String arq = "/home/icone/teste.txt";
//        String arq = "/home/icone/teste.ps";
//        String arq = "/home/icone/teste2Danfe.pdf";
//        String[] arguments = {arq,"192.168.250.181","5100"};
//        String[] arguments = {arq,"192.168.250.21","5100","EXECUTA=sumatrapdf.exe -print-settings noscale -print-to-default " + arq};
//        String[] arguments = {arq,"192.168.250.21","5100"};
//        String[] arguments = {arq,"192.168.250.74","5100","EXECUTA=kwrite " + arq};
//        String[] arguments = {arq,"192.168.250.74","5100","EXECUTA=kwrite " + arq + "teste"};
//        String[] arguments = {arq,"192.168.250.21","5100", "nomeImpressora=3050"};
        String[] arguments = {arq,"192.168.250.74","5100", "nomeImpressora=hp01"};
//        String[] arguments = {arq,"192.168.250.74","5100", "nomeImpressora=hplaserjet"};
//        String[] arguments = {arq,"192.168.250.74","5100"};
        test.main(arguments);
    }
    
}

