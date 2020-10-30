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
public class Pedido {
    
    private long codPedido;
    private Lanche codLanche;
    private Double precoTotal;
    private Double desconto;
    private Boolean fgAtivo;
    private java.sql.Date dataEntrada;
    private Usuario cpf_cnpj;

    public Usuario getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(Usuario cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }
    
    
    
    public long getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(long codPedido) {
        this.codPedido = codPedido;
    }

    public Lanche getCodLanche() {
        return codLanche;
    }

    public void setCodLanche(Lanche codLanche) {
        this.codLanche = codLanche;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Boolean getFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(Boolean fgAtivo) {
        this.fgAtivo = fgAtivo;
    }

    public java.sql.Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(java.sql.Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
    
    
    
    
}
