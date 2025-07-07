package br.edu.ufam.controller;

import br.edu.ufam.model.ClienteModel;
import br.edu.ufam.model.ComandaModel;
import br.edu.ufam.service.ComandaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListaComandaController {
    private final ComandaService comandaService = new ComandaService();

    private String filtroStatus;

    @FXML
    private TableView<ComandaModel> tableView;
    @FXML
    private TableColumn<ComandaModel, Integer> colId;
    @FXML
    private TableColumn<ComandaModel, String> colData;
    @FXML
    private TableColumn<ComandaModel, String> colCliente;
    @FXML
    private TableColumn<ComandaModel, String> colUsuario;
    @FXML
    private TableColumn<ComandaModel, Float> colValor;
    @FXML
    private TableColumn<ComandaModel, String> colStatus;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colCliente.setCellValueFactory(cellData -> {
            ClienteModel cliente = cellData.getValue().getCliente();
            return cliente != null ? new javafx.beans.property.SimpleStringProperty(cliente.getNome()) : null;
        });
        colUsuario.setCellValueFactory(cellData -> {
            String usuarioNome = cellData.getValue().getUsuario() != null ? cellData.getValue().getUsuario().getNome()
                    : "";
            return new javafx.beans.property.SimpleStringProperty(usuarioNome);
        });
        colValor.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        carregarComanda();

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableView.getSelectionModel().getSelectedItem() != null) {
                ComandaModel comandaSelecionado = tableView.getSelectionModel().getSelectedItem();
                System.out.println("Comanda selecionada: " + comandaSelecionado.getId());
            }
        });
    }

    private void carregarComanda() {
        ObservableList<ComandaModel> clientes = FXCollections
                .observableArrayList(comandaService.listarComandas(filtroStatus));
        tableView.setItems(clientes);
    }

    public void setFiltroStatus(String filtroStatus) {
        this.filtroStatus = filtroStatus;
        carregarComanda();
    }
}
