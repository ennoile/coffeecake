package br.edu.ufam.controller;

import java.io.IOException;

import br.edu.ufam.Main;
import br.edu.ufam.service.AutenticacaoService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtSenha;

    @FXML
    private void testeClick2() throws IOException {
        AutenticacaoService autenticacaoService = new AutenticacaoService();
        String login = txtLogin.getText();
        String senha = txtSenha.getText();
        autenticacaoService.login(login, senha);

        if (autenticacaoService.isAutenticado()) {
            System.out.println("Usuário autenticado com sucesso: " + autenticacaoService.getUsuarioLogado().getNome());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            Stage homeStage = new Stage();
            homeStage.setTitle("Home");
            homeStage.setScene(new Scene(root, 800, 600));
            homeStage.show();

            Main.closeScene();
        } else {
            System.out.println("Falha na autenticação. Verifique suas credenciais.");
        }
    }
}
