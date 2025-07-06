package br.edu.ufam.controller;

import java.io.IOException;

import br.edu.ufam.Main;
import br.edu.ufam.model.UsuarioModel;
import br.edu.ufam.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroUsuario {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtFuncao;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtSenha;

    @FXML
    private void clickCadastrar() throws IOException {
        UsuarioModel usuario = new UsuarioModel(0, txtNome.getText(), txtTelefone.getText(), txtEmail.getText(),
                txtLogin.getText(), txtSenha.getText(), txtFuncao.getText());
        UsuarioService service = new UsuarioService();
        service.cadastrarUsuario(usuario);
        Main.setRoot("home");
    }
}
