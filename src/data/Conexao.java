
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author felip
 */
public class Conexao {

    private static Connection conn;
    private static PreparedStatement st;
    private static ResultSet rs;
//-------------------------------------Conectando--------------------------------------------------
    private static final String URL = "jdbc:mysql://localhost:3306/AudioSan";
    private static final String USUARIO = "root";
    private static final String SENHA = "fefe21@";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection conectar() throws SQLException {
        Connection conexao = null;
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            conn = conexao; // Atribuindo a conexão à variável estática
        } catch (ClassNotFoundException e) {
            System.out.println("Falha ao registrar o Driver: " + e.getMessage());
        }
        return conexao;
    }
 
}
