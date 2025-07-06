package br.edu.ufam.controller;

import java.io.IOException;

import br.edu.ufam.Main;
import br.edu.ufam.model.ClienteModel;
import br.edu.ufam.service.ClienteService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroClienteController {
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;

    @FXML
    public void clickCadastrar() throws IOException {
        String cpf = txtCpf.getText();
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();

        ClienteModel cliente = new ClienteModel(0, cpf, nome, email, telefone);
        ClienteService clienteService = new ClienteService();
        clienteService.cadastrarCliente(cliente);
        Main.setRoot("home");
    }
}
