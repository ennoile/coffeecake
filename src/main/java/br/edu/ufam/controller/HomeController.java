package br.edu.ufam.controller;

import br.edu.ufam.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    private Label lblUsuarioLogado;

    @FXML
    private void initialize() {
        Main.setResizable(true);
        if (lblUsuarioLogado != null) {
            lblUsuarioLogado.setText("Usuário Logado: " + br.edu.ufam.Main.usuarioLogado.getNome());
        }
    }

    @FXML
    private void clickSair() {
        try {
            Main.setRoot("login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
