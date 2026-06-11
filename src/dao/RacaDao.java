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
}
