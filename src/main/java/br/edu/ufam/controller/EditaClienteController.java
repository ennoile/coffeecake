package br.edu.ufam.controller;

import br.edu.ufam.model.ClienteModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditaClienteController {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;

    public void preencherFormulario(ClienteModel cliente) {
        txtId.setText(String.valueOf(cliente.getId()));
        txtCpf.setText(cliente.getCpf());
        txtNome.setText(cliente.getNome());
        txtEmail.setText(cliente.getEmail());
        txtTelefone.setText(cliente.getTelefone());
    }
}
