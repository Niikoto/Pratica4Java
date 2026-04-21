package src.gui;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.dao.ClienteDao;
import src.modelo.ClienteModelo;

public class TelaConsultaController {
    @FXML
    private Button buttonVoltar;

    @FXML
    private TextField textNome;
    @FXML
    private TextField textCpf;

    @FXML
    private TableView<ClienteModelo> tableCliente;

    @FXML
    private TableColumn<ClienteModelo, String> colNome;
    @FXML
    private TableColumn<ClienteModelo, String> colCpf;
    @FXML
    private TableColumn<ClienteModelo, String> colData;
    @FXML
    private TableColumn<ClienteModelo, String> colTelefone;
    @FXML
    private TableColumn<ClienteModelo, String> colEndereco;
    @FXML
    private TableColumn<ClienteModelo, String> colBairro;
    @FXML
    private TableColumn<ClienteModelo, String> colCidade;
    @FXML
    private TableColumn<ClienteModelo, String> colEstado;
    @FXML
    private TableColumn<ClienteModelo, String> colCep;

    @FXML
    private TableColumn<ClienteModelo, Void> colExcluir;
    @FXML
    private TableColumn<ClienteModelo, Void> colAlterar;

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaCadastro.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void alterar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaAlterar.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void enviarConsulta(ActionEvent event) throws IOException {
        ClienteModelo consulte = new ClienteModelo();
        consulte.setNome(textNome.getText());
        consulte.setCpf(textCpf.getText());

        // Setar cada coluna
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colCep.setCellValueFactory(new PropertyValueFactory<>("cep"));

        colAlterar.setCellFactory(param -> new TableCell<>() {
            private final Button btnAlterar = new Button("Alterar");
            {
                btnAlterar.setOnAction(event -> {
                    ClienteModelo cliente = getTableView().getItems().get(getIndex());

                    telaConsulta(cliente);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnAlterar);
                }
            }
        });
        // seta onde o botão vai ficar
        colExcluir.setCellFactory(param -> new TableCell<>() {
            private final Button btnExcluir = new Button("Excluir");// Cria um botão na linha
            {
                btnExcluir.setOnAction(event -> {
                    ClienteModelo cliente = getTableView().getItems().get(getIndex());// verifica onde cada botão deve

                    ClienteDao dao = new ClienteDao();// cria um objeto para usar o metodo de exclusão
                    dao.excluirCliente(cliente.getId());

                    getTableView().getItems().remove(cliente);// verifica onde está a linha e exclui a linha toda
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {// isso daqui vai setar onde o botão deve ficar na
                                                                 // tabela corretamente
                super.updateItem(item, empty);

                if (empty) {// verifica se tem ou não informação na linha para adcionar o botão visualmente
                    setGraphic(null);
                } else {
                    setGraphic(btnExcluir);
                }
            }
        });

        ClienteDao dao = new ClienteDao();
        List<ClienteModelo> lista = dao.consultarCliente(consulte);
        tableCliente.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML
    public void limpar() {
        textNome.clear();
        textCpf.clear();
    }

    public void telaConsulta(ClienteModelo cliente){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/view/telaAlterar.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
    
            TelaAlterarContoller contoller = loader.getController();
    
            contoller.enviarDadosCadastro(cliente);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
