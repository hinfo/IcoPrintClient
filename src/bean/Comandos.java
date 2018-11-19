/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;

/**
 *
 * @author icone
 */
public class Comandos implements Serializable {

    private String nomeArquivo;
    private String cmdExecuta;
    private String cmdExtra;
    private String nomeImpressora;
    private String version;
    public static Comandos instance;

    private Comandos() {
    }

    public static Comandos getInstance() {
        if (instance == null) {
            instance = new Comandos();
        }
        return instance;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getCmdExecuta() {
        return cmdExecuta;
    }

    public void setCmdExecuta(String cmdExecuta) {
        this.cmdExecuta = cmdExecuta;
    }

    public String getCmdExtra() {
        return cmdExtra;
    }

    public void setCmdExtra(String cmdExtra) {
        this.cmdExtra = cmdExtra;
    }

    public String getNomeImpressora() {
        return nomeImpressora;
    }

    public void setNomeImpressora(String nomeImpressora) {
        this.nomeImpressora = nomeImpressora;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
