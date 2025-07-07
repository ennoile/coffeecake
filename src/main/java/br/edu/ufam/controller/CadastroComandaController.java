package br.edu.ufam.controller;

import java.io.IOException;

import br.edu.ufam.Main;
import br.edu.ufam.model.ClienteModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CadastroComandaController {
    private ClienteModel clienteComanda;

    @FXML
    private Label lblUsuarioLogado;
    @FXML
    private Label lblNomeCliente;

    @FXML
    private Label lblTitulo;

    @FXML
    public void initialize() {
        lblTitulo.setText("Comanda");
        lblUsuarioLogado.setText("Usuário Logado: " + Main.usuarioLogado.getNome());
    }

    @FXML
    public void adicionarCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("controller/dialogo_buscar_cliente.fxml"));
            VBox layout = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initOwner(Main.getScene().getWindow());
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.setTitle("Buscar Cliente");
            dialogStage.setScene(new Scene(layout));

            DialogoBuscarClienteController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            ClienteModel cliente = controller.getClienteSelecionado();
            if (cliente != null) {
                clienteComanda = cliente;
                setClienteComanda(clienteComanda);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setClienteComanda(ClienteModel cliente) {
        lblNomeCliente.setText("Cliente: " + cliente.getNome());
    }

    @FXML
    public void finalizarComanda() {
        if (clienteComanda != null) {
            System.out.println("Comanda finalizada para o cliente: " + clienteComanda.getNome());
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha ao criar comanda");
            alert.setContentText(
                    "Nenhum cliente selecionado. Por favor, adicione um cliente antes de finalizar a comanda.");
            alert.showAndWait();
        }
    }
}
