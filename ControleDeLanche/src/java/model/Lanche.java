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
public class Lanche {

    private long codLanche;
    private Usuario cpfCnpj;
    private Produto codProduto;
    private long quantidade;
    private Boolean fgAtivo;

    public long getCodLanche() {
        return codLanche;
    }

    public void setCodLanche(long codLanche) {
        this.codLanche = codLanche;
    }

    public Usuario getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(Usuario cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Produto getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Produto codProduto) {
        this.codProduto = codProduto;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(Boolean fgAtivo) {
        this.fgAtivo = fgAtivo;
    }
    
    
    
}
