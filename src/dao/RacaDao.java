package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.factory.ConnectionFactory;
import src.modelo.RacaModelo;

public class RacaDao {
    Connection connection = ConnectionFactory.getConnection();

    public List<RacaModelo> listarRaca(){
        List<RacaModelo> racas = new ArrayList<>();
        String sql = "select id_raca, nome_raca from raca;";

        try(PreparedStatement comando = connection.prepareStatement(sql)) {
            ResultSet res = comando.executeQuery();
            if (!res.next()) {
                return racas;
            }
            else{
                do{
                    RacaModelo raca = new RacaModelo();
                    raca.setId_raca(res.getInt(1));
                    raca.setNome_raca(res.getString(2));

                    racas.add(raca);
                }while(res.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return racas;
    }

    public void cadastrarRaca(RacaModelo r){
        String sql = "insert into raca(nome_raca, tipo_animal, status_raca) values(?, ?, ?)";
        try(PreparedStatement comando = connection.prepareStatement(sql)) {
            comando.setString(1, r.getNome_raca());
            comando.setString(2, r.getTipo_animal());
            comando.setBoolean(3, r.isStatus_raca());

            comando.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RacaModelo> listarTudoRaca(){
        List<RacaModelo> racas = new ArrayList<>();
        String sql = "select id_raca, nome_raca, tipo_animal, status_raca from raca where status_raca = 1;";

        try(PreparedStatement comando = connection.prepareStatement(sql)) {
            ResultSet res = comando.executeQuery();
            if (!res.next()) {
                return racas;
            }
            else{
                do{
                    RacaModelo raca = new RacaModelo();
                    raca.setId_raca(res.getInt(1));
                    raca.setNome_raca(res.getString(2));
                    raca.setTipo_animal(res.getString(3));
                    raca.setStatus_raca(res.getBoolean(4));

                    racas.add(raca);
                }while(res.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return racas;
    }

    public void excluirRaca(int id){
        String sql = "delete from raca where id_raca = ?;";
        try(PreparedStatement comando = connection.prepareStatement(sql)) {
            comando.setInt(1, id);

            comando.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarRaca(String nome, String tipo_animal, boolean status_raca, int id) {
        String sql = "update raca set nome_raca= ? , tipo_animal= ? , status_raca= ? where id_raca = ?";

        try (PreparedStatement comando = connection.prepareStatement(sql)) {
            comando.setString(1, nome);
            comando.setString(2, tipo_animal);
            comando.setBoolean(3, status_raca);
            comando.setInt(4, id);

            comando.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}