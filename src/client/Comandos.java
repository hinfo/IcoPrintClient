/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.Serializable;

/**
 *
 * @author icone
 */
public class Comandos implements Serializable {

    private String nomeArquivo;
    private String executa;
    private String cmdExtra;

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getExecuta() {
        return executa;
    }

    public void setExecuta(String executa) {
        this.executa = executa;
    }

    public String getCmdExtra() {
        return cmdExtra;
    }

    public void setCmdExtra(String cmdExtra) {
        this.cmdExtra = cmdExtra;
    }
    
    
}
