/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista8.pkg3_livroautor5agoravai;

/**
 *
 * @author alexandre
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbTeste", "alexandre", "3227");

    }
}
