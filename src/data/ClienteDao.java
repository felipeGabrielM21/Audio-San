package data;

import data.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    public Connection conn;

    public ClienteDao() {
        try {
            conn = Conexao.conectar();
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public ClienteDao(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //-------------------------------------cadastar--------------------------------------------------
    public int cadastrar(Cliente cliente) throws ParseException {
        int idInserido = -1;
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO cadastros (nomeCliente, Carro, AnoCarro, Endereco, Telefone) VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, cliente.getNomeCliente());
            st.setString(2, cliente.getCarro());
            st.setInt(3, cliente.getAnoCarro());
            st.setString(4, cliente.getEndereco());
            st.setInt(5, cliente.getTelefone());

            st.executeUpdate();

            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                idInserido = generatedKeys.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar cliente: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar o statement: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
        return idInserido;
    }

    //----------------------------------ConsultaTodos---------------------------------------------------
    public List<Cliente> consultarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM cadastros");
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomeCliente = rs.getString("nomeCliente");
                String carro = rs.getString("Carro");
                int anoCarro = rs.getInt("AnoCarro");
                String endereco = rs.getString("Endereco");
                int telefone = rs.getInt("Telefone");

                Cliente cliente = new Cliente(id, nomeCliente, carro, anoCarro, endereco, telefone);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar clientes: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar o ResultSet: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar o Statement: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }

        return clientes;
    }

    //----------------------------------Atualizar Cliente---------------------------------------------------
    public boolean Atualizar(Cliente cliente) {
        String sql = "UPDATE cadastros SET nomeCliente = ?, Carro = ?, AnoCarro = ?, Endereco = ?, Telefone = ? WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, cliente.getNomeCliente());
            st.setString(2, cliente.getCarro());
            st.setInt(3, cliente.getAnoCarro());
            st.setString(4, cliente.getEndereco());
            st.setInt(5, cliente.getTelefone());
            st.setInt(6, cliente.getId());

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar cliente: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    //----------------------------------Pesquisar Cliente pelo Nome---------------------------------------------------
    public List<Cliente> pesquisarPorNome(String nome) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cadastros WHERE nomeCliente LIKE ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "%" + nome + "%");
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nomeCliente = rs.getString("nomeCliente");
                    String carro = rs.getString("Carro");
                    int anoCarro = rs.getInt("AnoCarro");
                    String endereco = rs.getString("Endereco");
                    int telefone = rs.getInt("Telefone");

                    Cliente cliente = new Cliente(id, nomeCliente, carro, anoCarro, endereco, telefone);
                    clientes.add(cliente);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar cliente: " + ex.getMessage());
            ex.printStackTrace();
        }

        return clientes;
    }

    //----------------------------------Excluir Cliente pelo Id---------------------------------------------------
    public int excluirPorId(int id) {
        int status = 0;
        try (PreparedStatement st = conn.prepareStatement("DELETE FROM cadastros WHERE id = ?")) {
            st.setInt(1, id);
            status = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir usuário: " + ex.getMessage());
            ex.printStackTrace();
        }
        return status;

    }

}
