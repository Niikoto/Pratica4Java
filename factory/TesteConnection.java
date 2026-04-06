package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConnection {
    public static void main(String[] args)throws SQLException {
        Connection conection = ConnectionFactory.getConnection();
        System.out.println("Banco aberto");
        conection.close();
        System.out.println("Conecxão fechada");
    }
}
