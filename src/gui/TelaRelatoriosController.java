package src.gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.dao.AnimalDao;
import src.dao.ClienteDao;
import src.modelo.AnimalModelo;
import src.modelo.ClienteModelo;

public class TelaRelatoriosController {
    @FXML
    private TextField txtMes, txtAno, anoTxt, mesTxt;

    @FXML
    public void relatorioGeral(ActionEvent event) {

        AnimalDao dao = new AnimalDao();
        List<AnimalModelo> animais = dao.relatorioClientesAnimais();

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("relatorio_clientes_animais.txt"))) {

            writer.write("RELATÓRIO DE CLIENTES E ANIMAIS");
            writer.newLine();
            writer.write("=====================================");
            writer.newLine();
            writer.newLine();

            for (AnimalModelo animal : animais) {

                writer.write("Cliente: " + animal.getCliente().getNome());
                writer.newLine();

                writer.write("CPF: " + animal.getCliente().getCpf());
                writer.newLine();

                writer.write("Animal: " + animal.getNome_animal());
                writer.newLine();

                writer.write("Raça: " + animal.getRaca().getNome_raca());
                writer.newLine();

                writer.write("Data de nascimento: " + animal.getData_nascimento());
                writer.newLine();

                writer.write("-------------------------------------");
                writer.newLine();
            }

            System.out.println("Relatório gerado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void fecharTela(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/src/view/telaCadastro.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void relatorioAniAni(ActionEvent event) {

        AnimalDao dao = new AnimalDao();

        int mes = Integer.parseInt(txtMes.getText());
        int ano = Integer.parseInt(txtAno.getText());

        List<AnimalModelo> animais = dao.listarAniversariantesMes(mes, ano);

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("relatorio_aniversariantes.txt"))) {

            writer.write("RELATÓRIO DE ANIMAIS ANIVERSARIANTES");
            writer.newLine();
            writer.write("Mês: " + mes + " | Ano: " + ano);
            writer.newLine();
            writer.write("========================================");
            writer.newLine();

            for (AnimalModelo animal : animais) {

                writer.write("Animal: " + animal.getNome_animal());
                writer.newLine();

                writer.write("Cliente: " + animal.getCliente().getNome());
                writer.newLine();

                writer.write("Telefone: " + animal.getCliente().getTelefone());
                writer.newLine();

                writer.write("Data Nascimento: " + animal.getData_nascimento());
                writer.newLine();

                writer.write("----------------------------------------");
                writer.newLine();
            }

            System.out.println("Relatório gerado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void relatorioAniCliente(ActionEvent event) {
        ClienteDao dao = new ClienteDao();

        int mes = Integer.parseInt(mesTxt.getText());
        int ano = Integer.parseInt(anoTxt.getText());

        List<ClienteModelo> clientes = dao.listarAniversariantesMes(mes, ano);

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("relatorio_clientes_aniversariantes.txt"))) {

            writer.write("RELATÓRIO DE CLIENTES ANIVERSARIANTES");
            writer.newLine();
            writer.write("Mês: " + mes + " | Ano: " + ano);
            writer.newLine();
            writer.write("========================================");
            writer.newLine();
            writer.newLine();

            for (ClienteModelo cliente : clientes) {

                writer.write("Nome: " + cliente.getNome());
                writer.newLine();

                writer.write("CPF: " + cliente.getCpf());
                writer.newLine();

                writer.write("Data de Nascimento: " + cliente.getDataNascimento());
                writer.newLine();

                writer.write("Telefone: " + cliente.getTelefone());
                writer.newLine();

                writer.write("----------------------------------------");
                writer.newLine();
            }

            System.out.println("Relatório gerado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
