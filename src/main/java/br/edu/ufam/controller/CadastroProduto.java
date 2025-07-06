package br.edu.ufam.controller;

import java.io.IOException;

import br.edu.ufam.Main;
import br.edu.ufam.model.ProdutoModel;
import br.edu.ufam.service.ProdutoService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroProduto {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private TextField textPreco;
    @FXML
    private TextArea txtDescricao;

    @FXML
    void clickCadastrar() throws IOException {
        ProdutoModel produto = new ProdutoModel(0, txtNome.getText(), Float.parseFloat(textPreco.getText()),
                txtDescricao.getText(), Integer.parseInt(txtQuantidade.getText()));
        ProdutoService service = new ProdutoService();
        service.cadastrarProduto(produto);
        Main.setRoot("home");
    }
}
