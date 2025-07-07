package br.edu.ufam.controller;

import java.io.IOException;

import br.edu.ufam.Main;
import br.edu.ufam.model.ProdutoModel;
import br.edu.ufam.service.ProdutoService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroProdutoController {
    private final ProdutoService service = new ProdutoService();

    private int idProduto = -1;

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private TextField textPreco;
    @FXML
    private TextArea txtDescricao;

    @FXML
    private Label lblTitulo;
    @FXML
    private Button btnSalvar;

    @FXML
    public void initialize() {
        lblTitulo.setText("Cadastrar Produto");
        btnSalvar.setText("Salvar");
    }

    @FXML
    void clickSalvar() throws IOException {
        String nome = txtNome.getText();
        String descricao = txtDescricao.getText();
        int quantidade = Integer.parseInt(txtQuantidade.getText());
        float preco = Float.parseFloat(textPreco.getText());

        if (idProduto == -1) {
            ProdutoModel produto = new ProdutoModel(0, nome, preco, descricao, quantidade);
            service.cadastrarProduto(produto);
        } else {
            ProdutoModel produto = new ProdutoModel(idProduto, nome, preco, descricao, quantidade);
            service.alterarProduto(produto);
        }

        Main.setRoot("lista_produto");
    }

    public void setProduto(ProdutoModel produto) {
        idProduto = produto.getId();
        txtNome.setText(produto.getNome());
        txtDescricao.setText(produto.getDescricao());
        txtQuantidade.setText(String.valueOf(produto.getQuantidade()));
        textPreco.setText(String.valueOf(produto.getPreco()));

        lblTitulo.setText("Alterar Produto");
        btnSalvar.setText("Alterar");
    }
}
