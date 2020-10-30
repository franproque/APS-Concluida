/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author makefake
 */
public class Enderecos {
    private long codEndereco;
    private Usuario cpfCnpj;
    private String cidade;
    private Boolean fgAtivo;
    private String estado;
    private String endereco;
    private String numero;
    private String bairro;
    private String complemento;

    public long getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(long codEndereco) {
        this.codEndereco = codEndereco;
    }

    public Usuario getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(Usuario cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Boolean getFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(Boolean fgAtivo) {
        this.fgAtivo = fgAtivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    
    
}
