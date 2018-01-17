
import client.Cliente;
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
    Cliente test = new Cliente();
//        String arq = "/home/icone/teste_registro_de_saidas.pdf";
        String arq = "/ico/bellart/cgi-bin/sac/validade.txt";
//        String arq = "/home/icone/teste.txt";
//        String[] arguments = {arq,"192.168.250.181","5100"};
        String[] arguments = {arq,"192.168.250.21","5100"};
        test.main(arguments);
    }
    
}

