package br.edu.ufam.controller;

import java.io.IOException;

import br.edu.ufam.Main;
import br.edu.ufam.model.IngredienteModel;
import br.edu.ufam.service.IngredienteService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroIngrediente {
    @FXML
    private TextField txtNome;
    @FXML
    private TextArea txtDescricao;
    @FXML
    private TextField txtQuantidade;

    @FXML
    private void clickCadastrar() throws IOException{
        IngredienteModel ingrediente = new IngredienteModel(0, txtNome.getText(), txtDescricao.getText(),Integer.parseInt(txtQuantidade.getText()));
        IngredienteService service = new IngredienteService();
        service.cadastrarIngrediente(ingrediente);
        Main.setRoot("home");
    }
}
