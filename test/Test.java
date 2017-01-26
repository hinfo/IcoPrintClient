
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
        String[] arguments = {"/home/henrique/teste.txt","192.168.0.106","5100"};
        test.main(arguments);
    }
    
}

