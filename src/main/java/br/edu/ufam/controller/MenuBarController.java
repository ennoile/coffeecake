package br.edu.ufam.controller;

import java.io.IOException;

import br.edu.ufam.Main;
import javafx.fxml.FXML;

public class MenuBarController {
    @FXML
    public void clickCadastrarUsuario() throws IOException {
        System.out.println("Cadastro de usuário clicado.");
        Main.setRoot("cadastro_usuario");
    }

    @FXML
    public void clickCadastrarIngrediente() throws IOException {
        System.out.println("Cadastro de Ingrediente clicado.");
        Main.setRoot("cadastro_ingrediente");
    }

    @FXML
    public void clickCadastrarProduto() throws IOException {
        System.out.println("Cadastro de Produto clicado.");
        Main.setRoot("cadastro_produto");
    }

    @FXML
    public void clickListarClientes() throws IOException {
        System.out.println("Listar Clientes clicado.");
        Main.setRoot("lista_cliente");
    }

    @FXML
    public void clickCadastrarCliente() throws IOException {
        System.out.println("Cadastro de Cliente clicado.");
        Main.setRoot("cadastro_cliente");
    }

    @FXML
    public void clickLogout() throws IOException {
        System.out.println("Logout clicado.");
        Main.usuarioLogado = null;
        Main.setRoot("login");
    }
}
