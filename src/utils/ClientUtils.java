/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author icone
 */
public class ClientUtils {
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
    
    public static String serviceArgs(String arg){
        if (arg.toUpperCase().contains("EXECUTA")){
            String[] comandos = arg.split("=");
            return comandos[1];
        } else {
            return arg;
        }
    }
}
