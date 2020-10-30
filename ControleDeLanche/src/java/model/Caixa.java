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
public class Caixa {
    private long codCaixa;
    private Pedido codPedido;
    private Double valor;
    private Boolean fgAtivo;
    private java.sql.Date dataEntrada;

    public long getCodCaixa() {
        return codCaixa;
    }

    public void setCodCaixa(long codCaixa) {
        this.codCaixa = codCaixa;
    }

    public Pedido getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(Pedido codPedido) {
        this.codPedido = codPedido;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(Boolean fgAtivo) {
        this.fgAtivo = fgAtivo;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
    
    
    
}
