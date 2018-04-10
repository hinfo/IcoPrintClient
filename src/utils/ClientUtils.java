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
        StringBuilder msg =  new StringBuilder();
        msg.append("Parâmetros Obrigatórios:\n");
        msg.append("\tComando: java -jar IcoPrintClient.jar ");
        msg.append("+ <nome_do_arquivo> + <ip_servidor> + ");
        msg.append("<porta> \n");
        msg.append("Parâmetros opcionais:\n");
        msg.append("\t1-EXECUTA=<executável do windows> \n");
        msg.append("\t2-<arquivo>\n");
        msg.append("Exemplo: \n");
        msg.append("\tjava -jar IcoPrintClient.jar ");
        msg.append("teste.txt 192.168.0.250 5001 EXECUTA=xxxxx.exe teste.txt");
        System.out.println(msg.toString());
    }
    
    public static String serviceArgs(String arg){
        if (arg.toUpperCase().contains("EXECUTA")){
            String[] comandos = arg.split("=");
            return comandos[1];
        } else {
            return arg;
        }
    }
    public static String removeStashNomeArquivo(String arg){
        String[] slices = arg.split("/");
        String nome = slices[slices.length-1];
        return nome;
    }
}
