package br.com.verycheap.verycheap;

import br.com.verycheap.verycheap.DiasEntreDataAtual;

public class Itens {
    private String nomRazaoSocial;
    private String valUltimaVenda;
    private String nomBairro;
    private String dscProduto;
    private String dthEmissaoUltimaVenda;

    public Itens() {
    }

    public Itens(String nomRazaoSocial, String valUltimaVenda, String nomBairro, String dscProduto, String dthEmissaoUltimaVenda) {
        this.nomRazaoSocial = nomRazaoSocial;
        this.valUltimaVenda = valUltimaVenda;
        this.nomBairro = nomBairro;
        this.dscProduto = dscProduto;
        this.dthEmissaoUltimaVenda = dthEmissaoUltimaVenda;

    }

    public String getNomRazaoSocial() {
        return nomRazaoSocial;
    }

    public void setNomRazaoSocial(String nomRazaoSocial) {
        this.nomRazaoSocial = nomRazaoSocial;
    }

    public String getValUltimaVenda() {
        return valUltimaVenda;
    }

    public void setValUltimaVenda(String valUltimaVenda) {
        this.valUltimaVenda = valUltimaVenda;
    }

    public String getNomBairro() {
        return nomBairro;
    }

    public void setNomBairro(String nomBairro) {
        this.nomBairro = nomBairro;
    }

    public String getDscProduto() {
        return dscProduto;
    }

    public void setDscProduto(String dscProduto) {
        this.dscProduto = dscProduto;
    }

    public String getDthEmissaoUltimaVenda() {
        return dthEmissaoUltimaVenda;
    }

    public void setDthEmissaoUltimaVenda(String dthEmissaoUltimaVenda) {
        this.dthEmissaoUltimaVenda = dthEmissaoUltimaVenda;
    }
}
