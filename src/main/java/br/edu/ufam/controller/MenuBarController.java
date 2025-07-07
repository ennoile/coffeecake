package br.edu.ufam.controller;

import java.io.IOException;

import br.edu.ufam.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

public class MenuBarController {
    @FXML
    private Label lblUsuarioLogado;
    @FXML
    private Label lblTitulo;
    @FXML
    private Menu menuUsuario;

    @FXML
    public void initialize() {
        if (Main.usuarioLogado.getFuncao().equals("gerente")) {
            menuUsuario.setVisible(true);
        } else {
            menuUsuario.setVisible(false);
        }
    }

    @FXML
    public void clickHome() throws IOException {
        System.out.println("Home clicado.");
        Main.setRoot("home");
    }

    @FXML
    public void clickCadastrarUsuario() throws IOException {
        System.out.println("Cadastro de usuário clicado.");
        Main.setRoot("cadastro_usuario");
    }

    @FXML
    public void clickListarUsuarios() throws IOException {
        System.out.println("Listar Usuários clicado.");
        Main.setRoot("lista_usuario");
    }

    @FXML
    public void clickCadastrarIngrediente() throws IOException {
        System.out.println("Cadastro de Ingrediente clicado.");
        Main.setRoot("cadastro_ingrediente");
    }

    @FXML
    public void clickListarIngredientes() throws IOException {
        System.out.println("Listar Ingredientes clicado.");
        Main.setRoot("lista_ingrediente");
    }

    @FXML
    public void clickCadastrarProduto() throws IOException {
        System.out.println("Cadastro de Produto clicado.");
        Main.setRoot("cadastro_produto");
    }

    @FXML
    public void clickListarProduto() throws IOException {
        System.out.println("Listar Produtos clicado.");
        Main.setRoot("lista_produto");
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
