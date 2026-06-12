package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import src.factory.ConnectionFactory;
import src.modelo.AnimalModelo;
import src.modelo.ClienteModelo;
import src.modelo.RacaModelo;

public class AnimalDao {
    Connection conexo = ConnectionFactory.getConnection();

    public void cadastrarAnimal(AnimalModelo aM){
        String sql = "insert into animal(nome_animal,data_nascimento,sexo,cor,observacoes,cod_cliente,cod_raca,status_animal) values (?,?,?,?,?,?,?,?);";
        try(PreparedStatement comando = conexo.prepareStatement(sql)){
            comando.setString(1, aM.getNome_animal());
            comando.setString(2, aM.getData_nascimento());
            comando.setString(3, aM.getSexo());
            comando.setString(4, aM.getCor());
            comando.setString(5, aM.getObservacoes());
            comando.setInt(6, aM.getCod_cliente());
            comando.setInt(7, aM.getCod_raca());
            comando.setBoolean(8, aM.isStatus_animal());

            comando.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AnimalModelo> listarAnimal(String nome, String cpf){
        List<AnimalModelo> animais = new ArrayList<>();
        String sql = "select a.id_animal, a.nome_animal, a.data_nascimento, a.sexo, a.cor, a.observacoes, c.nome, r.nome_raca, a.status_animal from animal a inner join cliente c on a.cod_cliente = c.id inner join raca r on a.cod_raca = r.id_raca where c.nome like ? and c.cpf like ?  and a.status_animal = 1;";
        try(PreparedStatement comando = conexo.prepareStatement(sql)) {
            comando.setString(1, "%" + nome + "%");
            comando.setString(2, "%" + cpf + "%");
            ResultSet res = comando.executeQuery();
            if (!res.next()) {
                return animais;
            }
            else{
                do{
                    AnimalModelo animal = new AnimalModelo();
                    ClienteModelo cliente = new ClienteModelo();
                    RacaModelo raca = new RacaModelo();

                    animal.setId_animal(res.getInt(1));
                    animal.setNome_animal(res.getString(2));
                    animal.setData_nascimento(res.getString(3));
                    animal.setSexo(res.getString(4));
                    animal.setCor(res.getString(5));
                    animal.setObservacoes(res.getString(6));

                    cliente.setNome(res.getString(7));
                    animal.setCliente(cliente);

                    raca.setNome_raca(res.getString(8));
                    animal.setRaca(raca);
                    
                    animais.add(animal);
                }while(res.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animais;
    }

    public void excluirAnimal(int id){
        String sql = "delete from animal where id_animal = ?";

        try(PreparedStatement comando = conexo.prepareStatement(sql)) {
            comando.setInt(1, id);

            comando.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarAnimal(String nome, LocalDate dataNascimento, String sexo, String cor, String observacoes, int cod_cliente, int cod_raca, boolean status_animal, int id) {
        String sql = "update animal set nome_animal= ?, data_nascimento= ?, sexo= ?, cor= ? , observacoes= ? , cod_cliente= ? , cod_raca= ? , status_animal= ? where id_animal = ?";

        try (PreparedStatement comando = conexo.prepareStatement(sql)) {
            comando.setString(1, nome);
            comando.setDate(2, java.sql.Date.valueOf(dataNascimento));
            comando.setString(3, sexo);
            comando.setString(4, cor);
            comando.setString(5, observacoes);
            comando.setInt(6, cod_cliente);
            comando.setInt(7, cod_raca);
            comando.setBoolean(8, status_animal);
            comando.setInt(9, id);

            comando.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AnimalModelo> relatorioClientesAnimais() {
        List<AnimalModelo> animais = new ArrayList<>();

        String sql = "SELECT c.nome, c.cpf, a.nome_animal, a.data_nascimento, r.nome_raca FROM cliente c INNER JOIN animal a ON c.id = a.cod_cliente INNER JOIN raca r ON a.cod_raca = r.id_raca ORDER BY c.nome, a.nome_animal";

        try (PreparedStatement comando = conexo.prepareStatement(sql)) {

            ResultSet res = comando.executeQuery();

            while (res.next()) {

                AnimalModelo animal = new AnimalModelo();
                ClienteModelo cliente = new ClienteModelo();
                RacaModelo raca = new RacaModelo();

                cliente.setNome(res.getString(1));
                cliente.setCpf(res.getString(2));

                animal.setNome_animal(res.getString(3));
                animal.setData_nascimento(res.getString(4));

                raca.setNome_raca(res.getString(5));

                animal.setCliente(cliente);
                animal.setRaca(raca);

                animais.add(animal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animais;
    }

    public List<AnimalModelo> listarAniversariantesMes(int mes, int ano){
        List<AnimalModelo> animais = new ArrayList<>();

        String sql =
            "SELECT a.nome_animal, a.data_nascimento, c.nome, c.telefone FROM animal a INNER JOIN cliente c ON a.cod_cliente = c.id WHERE MONTH(a.data_nascimento) = ? AND YEAR(a.data_nascimento) = ?";

        try (PreparedStatement comando = conexo.prepareStatement(sql)) {

            comando.setInt(1, mes);
            comando.setInt(2, ano);

            ResultSet res = comando.executeQuery();

            while (res.next()) {

                AnimalModelo animal = new AnimalModelo();
                ClienteModelo cliente = new ClienteModelo();

                animal.setNome_animal(res.getString(1));
                animal.setData_nascimento(res.getString(2));

                cliente.setNome(res.getString(3));
                cliente.setTelefone(res.getString(4));

                animal.setCliente(cliente);

                animais.add(animal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animais;
    }
}