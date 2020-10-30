/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author makefake
 */
public class Usuario {
    private String cpf_cnpj;
    private String email;
    private String nomeFantasiaNomeCompleto;
    private String telefoneFixo;
    private String telefone;
    private Date dataNascimento;
    private Boolean fgAtivo;

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeFantasiaNomeCompleto() {
        return nomeFantasiaNomeCompleto;
    }

    public void setNomeFantasiaNomeCompleto(String nomeFantasiaNomeCompleto) {
        this.nomeFantasiaNomeCompleto = nomeFantasiaNomeCompleto;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(Boolean fgAtivo) {
        this.fgAtivo = fgAtivo;
    }
    
    
    
}
