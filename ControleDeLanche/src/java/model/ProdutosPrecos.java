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
public class ProdutosPrecos {

    private long codProdutosPrecos;
    private Produto codProduto;
    private Double precoProduto;
    private Boolean fgAtivo;
    private Double peso;
    private String unMedida;

    public long getCodProdutosPrecos() {
        return codProdutosPrecos;
    }

    public void setCodProdutosPrecos(long codProdutosPrecos) {
        this.codProdutosPrecos = codProdutosPrecos;
    }

    public Produto getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Produto codProduto) {
        this.codProduto = codProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public Boolean getFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(Boolean fgAtivo) {
        this.fgAtivo = fgAtivo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getUnMedida() {
        return unMedida;
    }

    public void setUnMedida(String unMedida) {
        this.unMedida = unMedida;
    }
    
    
    
}
