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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import src.dao.RacaDao;
import src.modelo.RacaModelo;

public class TelaConsultaRacaController {
    @FXML
    private TableView<RacaModelo> tableAnimal;
    @FXML
    private TableColumn<RacaModelo,String> colNome;
    @FXML
    private TableColumn<RacaModelo, String> colTipo;
    @FXML
    private TableColumn<RacaModelo, Boolean> colStatus;
    @FXML
    private TableColumn<RacaModelo, Void> colExcluir;
    @FXML
    private TableColumn<RacaModelo, Void> colAlterar;

    @FXML
    public void initialize(){
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome_raca"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo_animal"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status_raca"));

        colAlterar.setCellFactory(param -> new TableCell<>() {//função anonima que pega a celula da tabela e cria o botão
            private final Button btnAlterar = new Button("Alterar");
            {
                btnAlterar.setOnAction(event -> {//Evento acionado quando o botão for criado
                    RacaModelo r = getTableView().getItems().get(getIndex());

                    telaConsultaRaca(r);//Metodo sem o objeto que leva a tela de alteração
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {//Caso a celula esteja varia o botão saira, caso tenha algo dentro o botão é gerado
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
                    RacaModelo r = getTableView().getItems().get(getIndex());// verifica onde cada botão deve

                    RacaDao dao = new RacaDao();// cria um objeto para usar o metodo de exclusão
                    dao.excluirRaca(r.getId_raca());

                    getTableView().getItems().remove(r);// verifica onde está a linha e exclui a linha toda
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

        RacaDao dao = new RacaDao();
        List<RacaModelo> lista = dao.listarTudoRaca();
        tableAnimal.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML
    public void voltar(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaCadastroRaca.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    public void telaConsultaRaca(RacaModelo r){//Metodo que abre a tela de alterar
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/view/telaAlterarRaca.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
    
            TelaAlterarRacaController contoller = loader.getController();//Pega o controler da tela de alteração
    
            contoller.enviarDadosCadastro(r);//Chama o metodo de enviar dados da tela de consulta
            
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}