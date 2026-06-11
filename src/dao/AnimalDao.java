package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.factory.ConnectionFactory;
import src.modelo.AnimalModelo;

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
}
