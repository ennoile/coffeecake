package br.edu.ufam.model;

public class ComandaProdutoModel {
    private int id;
    private int quantidade;
    private int idComanda;
    private int idProduto;

    public ComandaProdutoModel(int id, int quantidade, int idComanda, int idProduto) {
        this.id = id;
        this.quantidade = quantidade;
        this.idComanda = idComanda;
        this.idProduto = idProduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
}
